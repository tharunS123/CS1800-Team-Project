package src.Server;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server at localhost on port 12345
            Socket socket = new Socket("localhost", 12345);

            // Set up input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Send a message to the server
            output.println("Hello, Server!");

            // Read the server's response
            String serverMessage = input.readLine();
            System.out.println("Received from server: " + serverMessage);

            // Close the socket connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}