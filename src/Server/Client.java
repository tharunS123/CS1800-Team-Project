package src.Server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server at localhost on port 12345
            Socket socket = new Socket("localhost", 12345);

            // Set up input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Create a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);

            // Read the initial prompt from the server
            System.out.println(input.readLine());  // This is the server's message prompting for username/password

            // Prompt the user for a username
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            // Prompt the user for a password
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            // Send the username and password to the server in the format username:password
            output.println(username + ":" + password);

            // Read and display the server's response (e.g., "Login successful" or "Invalid username or password")
            String serverResponse = input.readLine();
            System.out.println("Server: " + serverResponse);

            // Close the socket connection
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}