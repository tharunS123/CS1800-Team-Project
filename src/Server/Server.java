package src.Server;

import src.UserDatabase.User;
import src.UserDatabase.UserDatabase;

import java.io.*;
import java.net.*;

public class Server {
    private static UserDatabase userDatabase = new UserDatabase("user_database.txt");

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running and waiting for clients...");
            userDatabase.loadUsers();
            while (true) {
                // Accept a client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Handle the client connection in a new thread
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ClientHandler class to handle communication with each client
    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            ) {
                String clientMessage;
                // Read the client's login information (username:password)
                output.println("Please enter your username and password in the format username:password");
                clientMessage = input.readLine();

                if (clientMessage != null && !clientMessage.trim().isEmpty()) {
                    // Split the username and password
                    String[] credentials = clientMessage.split(":");

                    if (credentials.length == 2) {
                        String username = credentials[0].trim();
                        String password = credentials[1].trim();

                        // Validate the credentials using the UserDatabase
                        if (userDatabase.addUser(new User(username, password))) {
                            output.println("Login successful");
                        } else {
                            output.println("Invalid username or password");
                        }
                    } else {
                        output.println("Invalid input format. Please use username:password");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}