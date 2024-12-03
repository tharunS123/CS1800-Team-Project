package src.Server.newNewServer;

import src.PostDatabase.PostDatabase;
import src.UserDatabase.User;
import src.UserDatabase.UserDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private static final int SERVER_PORT = 4242;
    public static UserDatabase userDatabase;
    public static PostDatabase postDatabase;

    public static void main(String[] args) {
        try {
            userDatabase = new UserDatabase("userDb.txt");
            postDatabase = new PostDatabase("postDb.txt");

            try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
                System.out.println("Server started on port " + SERVER_PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Connection established with client " + clientSocket.getInetAddress());
                    new ClientHandler(clientSocket).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ClientHandler extends Thread {
        private Socket clientSocket;
        private Map<Socket, User> loggedInUsers;
        private String currentUsername;
        private User currentUser;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            this.loggedInUsers = new HashMap<>();
        }

        public synchronized void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true))
            {
                String line;
                boolean isRunning = true;

                while (isRunning && (line = in.readLine()) != null) {
                    if (line.startsWith("SIGN_IN:")) {
                        handleSignIn(line, out);
                    } else if (line.startsWith("SIGN_UP:")) {
                        handleSignUp(line, out);
                    } else if (line.startsWith("VIEW_PROFILE:")) {
                        handleViewUser(line, out);
                    } else if (line.startsWith("POST:")) {
//                        handlePost(line, out);
                    } else if (line.startsWith("VIEW_POSTS:")) {
//                        handleViewPosts(line, out);
                    } else if (line.startsWith("LOGOUT:")) {
//                        handleLogout(line, out);
                    } else if (line.startsWith("EXIT")) {
                        isRunning = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private synchronized void handleSignIn(String line, PrintWriter out) {
            String[] parts = line.split(":");
            if (parts.length == 3) {
                String username = parts[1];
                String password = parts[2];

                try {
                    User user = userDatabase.getUser(username);
                    if (user != null && user.getPassword().equals(password)) {
                        out.println("Login successful!");
                        out.println("END");
                        loggedInUsers.put(clientSocket, user);
                        currentUsername = user.getUsername();
                        currentUser = user;
//                        for (User users : userDatabase.loadUsers()) {
//                            if (users.getUsername().equals(username)) {
//                                loggedInUsers.put(clientSocket, users);
//                            }
//                        }
                    } else {
                        out.println("Login failed: Incorrect username or password.");
                        out.println("END");
                    }
                } catch (Exception e) {
                    out.println("An error occurred while signing in: " + e.getMessage());
                    out.println("END");
                }
            } else {
                out.println("Invalid SIGN_IN format.");
                out.println("END");
            }
        }

        private synchronized void handleSignUp(String line, PrintWriter out) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String username = parts[1];
                String password = parts[2];
                try {
                    if (userDatabase.addUser(new User(username, password))) {
                        out.println("Account created successfully!");
                        out.println("END");
                    } else {
                        out.println("Username already taken.");
                        out.println("END");
                    }
                } catch (Exception e) {
                    out.println("An error occurred while signing up: " + e.getMessage());
                    out.println("END");
                }
            }
        }

        private synchronized void handleViewUser(String line, PrintWriter out) {
            String[] parts = line.split(":");
            try {
                if (parts.length == 2) {
                    String username = parts[1];
                    Map<String, User> results = userDatabase.loadUsers();

                    if (results == null) {
                        out.println(username + " not found.");
                        out.println("END");
                    } else {
                        out.println("Username: " + username);
                        out.println("Name: " + results.get(0));
                        out.println("Birthday: " + results.get(1));
                        out.println("END");
                    }
                } else {
                    out.println("Invalid VIEW_PROFILE format.");
                    out.println("END");
                }
            } catch (Exception e) {
                out.println("An error occurred while searching: " + e.getMessage());
                out.println("END");
            }
        }
    }
}
