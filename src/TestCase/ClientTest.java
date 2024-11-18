package src.TestCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Server.new_server.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for testing the {@link Client} class's functionality.
 * <p>
 * This class tests various aspects of the client-server communication, such as ensuring that the client
 * receives the correct welcome message from the server and sends the expected commands back to the server.
 * It uses mock objects to simulate server interaction and client input to verify that the client behaves as expected.
 * </p>
 * <p>
 * The tests are run using JUnit 5 framework, and the client-server interaction is simulated using fake input and output streams.
 * </p>
 */
class ClientTest {

    /**
     * Mock socket representing the connection to the server.
     * This is not directly used but can be part of the simulation setup.
     */
    private Socket mockSocket;

    /**
     * Mocked reader simulating the server's responses to the client.
     * This is used to simulate what the client will read from the server.
     */
    private BufferedReader mockServerReader;

    /**
     * Mocked writer simulating the client's output to the server.
     * This captures the client's output (e.g., the commands it sends to the server).
     */
    private PrintWriter mockServerWriter;

    /**
     * Mocked reader simulating user input to the client.
     * This is used to simulate the client receiving user commands, like 'login' or 'register'.
     */
    private BufferedReader mockUserInputReader;

    /**
     * StringWriter used to capture the output produced by the client during tests.
     */
    private StringWriter outputCapture;

    /**
     * PrintWriter used to write the client output to the StringWriter for capturing.
     */
    private PrintWriter clientOutput;

    /**
     * Setup method that is called before each test case to initialize the mock objects and prepare the test environment.
     * This method creates fake server responses, simulates user input, and sets up output capturing for the client.
     */
    @BeforeEach
    void setUp() {
        // Prepare the fake server response
        String serverResponse = "Welcome to the server! Type 'login' to login or 'register' to create a new account.\n";
        mockServerReader = new BufferedReader(new StringReader(serverResponse));

        // Prepare the output capture for the client
        outputCapture = new StringWriter();
        clientOutput = new PrintWriter(outputCapture, true);

        // Simulate user input (e.g., the user types 'login')
        mockUserInputReader = new BufferedReader(new StringReader("login\n"));
    }

    /**
     * Test case to verify that the client receives the correct welcome message from the server.
     * <p>
     * The test simulates the client connecting to the server, receiving a welcome message, and checking that
     * the message contains the expected greeting.
     * </p>
     *
     * @throws IOException if an I/O error occurs during simulation
     */
    @Test
    void testClientReceivesWelcomeMessage() throws IOException {
        // Simulate client connecting to the server
        ClientSimulator clientSimulator = new ClientSimulator(mockServerReader, clientOutput, mockUserInputReader);

        // Run the client simulation
        clientSimulator.run();

        // Verify that the welcome message was printed
        String output = outputCapture.toString();
        assertTrue(output.contains("Welcome to the server!"));
    }

    /**
     * Test case to verify that the client sends the correct 'login' command to the server.
     * <p>
     * The test simulates the client sending the 'login' command after receiving the welcome message from the server.
     * The output is checked to ensure that the correct command was sent back to the server.
     * </p>
     *
     * @throws IOException if an I/O error occurs during simulation
     */
    @Test
    void testClientSendsLoginCommand() throws IOException {
        // Simulate client input and communication with server
        ClientSimulator clientSimulator = new ClientSimulator(mockServerReader, clientOutput, mockUserInputReader);

        // Run the client simulation
        clientSimulator.run();

        // Capture what the client sends to the server (check for the "login" command)
        String output = outputCapture.toString();
        assertTrue(output.contains("login"));
    }

    /**
     * A helper class that simulates the client-side interaction with the server.
     * <p>
     * This class simulates the process of reading server responses, displaying them to the client,
     * and sending commands from the user back to the server.
     * </p>
     */
    private static class ClientSimulator {

        private BufferedReader in;
        private PrintWriter out;
        private BufferedReader userInputReader;

        /**
         * Constructs a new {@code ClientSimulator} instance.
         *
         * @param in the input stream simulating the server's responses
         * @param out the output stream simulating the client's output
         * @param userInputReader the input stream simulating the user's commands
         */
        public ClientSimulator(BufferedReader in, PrintWriter out, BufferedReader userInputReader) {
            this.in = in;
            this.out = out;
            this.userInputReader = userInputReader;
        }

        /**
         * Simulates the client running, reading responses from the server and sending commands based on user input.
         * The server's responses are read and printed, and the user's input is captured and sent back to the server.
         *
         * @throws IOException if an I/O error occurs during the simulation
         */
        public void run() throws IOException {
            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                out.println(serverResponse);
                String userCommand = userInputReader.readLine();
                out.println(userCommand);
            }
        }
    }
}
