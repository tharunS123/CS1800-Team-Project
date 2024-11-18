package src.Server.new_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The {@code Client} class represents a simple client that connects to a server over TCP.
 * It facilitates communication between the client and server, allowing the user to input commands
 * that are sent to the server. It also displays the server's responses to the user.
 *
 * @author Tharun Kumar & Eashen
 **/
public class Client {

    /**
     * The address of the server to connect to. Default is "localhost".
     */
    private static final String SERVER_ADDRESS = "localhost";

    /**
     * The port on which the server is listening. Default is 12345.
     */
    private static final int SERVER_PORT = 12345;

    /**
     * The main method that runs the client.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println(serverResponse);
                String command = userInput.readLine();
                out.println(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
