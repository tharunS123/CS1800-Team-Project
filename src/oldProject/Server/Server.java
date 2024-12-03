package src.oldProject.Server;

import src.oldProject.Interface.ClientHandlerInterface;
import src.oldProject.Interface.ServerInterface;
import src.oldProject.PostDatabase.Post;
import src.oldProject.PostDatabase.PostDatabase;
import src.oldProject.UserDatabase.User;
import src.oldProject.UserDatabase.UserDatabase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server class handles client-server communication for a multi-client application.
 * It supports user authentication, post creation, and post viewing through an interactive console interface.
 *
 * @author Tharun Kumar Senthilkumar & Eashan
 **/
public class Server implements ServerInterface {

    /**
     * The user database used for managing user credentials.
     */
    private static final UserDatabase userDatabase = new UserDatabase("user_database.txt");

    /**
     * The post database used for managing posts created by users.
     */
    private static final PostDatabase postDatabase = new PostDatabase("post_database.txt");

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running and waiting for clients...");
            userDatabase.loadUsers();
            postDatabase.loadPosts();
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The ClientHandler class handles the interaction with an individual client.
     * Each client connection is processed in a separate thread.
     *
     * @author Tharun Kumar Senthilkumar & Eashan
     */
    private static class ClientHandler extends Thread implements ClientHandlerInterface {
        private Socket clientSocket;
        private boolean loggedIn = false;
        private User currentUser;

        /**
         * Constructs a new ClientHandler for a given client socket.
         *
         * @param socket The socket connected to the client.
         */
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        /**
         * The main logic for handling client requests. Processes user authentication, post creation,
         * and other client actions based on the user's login state.
         */
        @Override
        public void run() {
            try (
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            ) {
                while (true) {
                    if (loggedIn) {
                        output.println("You are logged in. Choose an option: 'create post', 'view posts', 'logout'");
                        String choice = input.readLine();
                        if (choice == null || choice.trim().isEmpty()) continue;

                        switch (choice.toLowerCase()) {
                            case "create post":
                                output.println("Post creation functionality not implemented.");
                                handleCreatePost(input, output);
                                break;
                            case "view posts":
                                output.println("View posts functionality not implemented.");
                                handleViewPosts(input, output);
                                break;
                            case "logout":
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

        @Override
        public void handleClient() {

        }

        @Override
        public void login() {

        }

        @Override
        public void register() {

        }

        @Override
        public void createPost() {

        }

        @Override
        public void viewPosts() {

        }

        @Override
        public void logout() {

        }

        @Override
        public String readLine() {
            return "";
        }

        /**
         * Handles user login by verifying credentials against the user database.
         *
         * @param input  The {@link BufferedReader} to read client input.
         * @param output The {@link PrintWriter} to send responses to the client.
         * @throws IOException if an I/O error occurs while reading or writing data.
         */
        public void handleLogin(BufferedReader input, PrintWriter output) throws IOException {
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
                    output.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
                } else {
                    output.println("Invalid username or password.");
                }
            } else {
                output.println("Invalid input format.");
            }
        }

        /**
         * Handles user signup by creating a new account in the user database.
         *
         * @param input  The {@link BufferedReader} to read client input.
         * @param output The {@link PrintWriter} to send responses to the client.
         * @throws IOException if an I/O error occurs while reading or writing data.
         */
        public void handleSignup(BufferedReader input, PrintWriter output) throws IOException {
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

        /**
         * Handles the creation of new posts by logged-in users.
         *
         * @param input  The {@link BufferedReader} to read client input.
         * @param output The {@link PrintWriter} to send responses to the client.
         * @throws IOException if an I/O error occurs while reading or writing data.
         */
        public void handleCreatePost(BufferedReader input, PrintWriter output) throws IOException {
            output.println("Enter your post content");
            String credentials = input.readLine();
            if (credentials == null) {
                output.println("Invalid input format.");
                return;
            }

            String[] parts = credentials.split(":");
            if (parts.length == 2) {
                String title = parts[0].trim();
                String content = parts[1].trim();
                String author = currentUser.getUsername();
                Post newPost = new Post(title, content, author);
                if (postDatabase.addPost(newPost)) {
                    output.println("Post created. You can now view posts.");
                } else {
                    output.println("Post creation failed. Try again.");
                }
            } else {
                output.println("Invalid input format.");
            }
        }

        /**
         * Handles the viewing of posts. Currently not implemented.
         *
         * @param input  The {@link BufferedReader} to read client input.
         * @param output The {@link PrintWriter} to send responses to the client.
         * @throws IOException if an I/O error occurs while reading or writing data.
         */
        public void handleViewPosts(BufferedReader input, PrintWriter output) throws IOException {
            output.println("not yet implemented");
        }
    }
}
