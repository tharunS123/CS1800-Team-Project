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
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private boolean loggedIn = false;
        private User currentUser;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            ) {
                while (true) {
                    if (loggedIn) {
                        output.println("You are logged in. Choose an option: 'create post', 'view posts', 'logout & exit'");
                        String choice = input.readLine();
                        if (choice == null || choice.trim().isEmpty()) continue;

                        switch (choice.toLowerCase()) {
                            case "create post":
                                output.println("Post creation functionality not implemented.");
                                break;
                            case "view posts":
                                output.println("View posts functionality not implemented.");
                                break;
                            case "logout & exit":
                                loggedIn = false;
                                currentUser = null;
                                output.println("Logged out. Returning to main menu.");
                                break;
                            default:
                                output.println("Invalid option. Try again.");
                                break;
                        }
                    } else {
                        output.println("Choose an option: 'login', 'signup', or 'exit'");
                        String choice = input.readLine();
                        if (choice == null || choice.trim().isEmpty()) continue;

                        switch (choice.toLowerCase()) {
                            case "login":
                                handleLogin(input, output);
                                break;
                            case "signup":
                                handleSignup(input, output);
                                break;
                            case "exit":
                                output.println("Goodbye!");
                                return;
                            default:
                                output.println("Invalid option. Try again.");
                                break;
                        }
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

        private void handleLogin(BufferedReader input, PrintWriter output) throws IOException {
            output.println("Enter your username and password");
            String credentials = input.readLine();
            if (credentials == null) {
                output.println("Invalid input format.");
                return;
            }

            String[] parts = credentials.split(":");
            if (parts.length == 2) {
                String username = parts[0].trim();
                String password = parts[1].trim();
                User user = userDatabase.getUser(username);
                if (user != null && user.getPassword().equals(password)) {
                    loggedIn = true;
                    currentUser = user;
                    output.println("Login successful.");
                } else {
                    output.println("Invalid username or password.");
                }
            } else {
                output.println("Invalid input format.");
            }
        }

        private void handleSignup(BufferedReader input, PrintWriter output) throws IOException {
            output.println("Enter your username and password");
            String credentials = input.readLine();
            if (credentials == null) {
                output.println("Invalid input format.");
                return;
            }

            String[] parts = credentials.split(":");
            if (parts.length == 2) {
                String username = parts[0].trim();
                String password = parts[1].trim();
                User newUser = new User(username, password);
                if (userDatabase.addUser(newUser)) {
                    output.println("Account created. You can now log in.");
                } else {
                    output.println("Username already exists. Try a different one.");
                }
            } else {
                output.println("Invalid input format.");
            }
        }
    }
}

