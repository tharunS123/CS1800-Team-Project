package src.oldProject.Server.new_server;

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
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The {@code Server} class represents a multi-threaded server that listens for client connections
 * on a specific port and handles communication with multiple clients concurrently.
 *
 * @author Tharun Kumar & Eashen
 * */
public class Server implements ServerInterface {
    private static final int PORT = 12345;
    /**
     * The thread pool used to handle client requests concurrently. It can handle up to 10 clients.
     */
    private static final ExecutorService clientHandlers = Executors.newFixedThreadPool(10);  // Thread pool for handling clients
    private static UserDatabase userDatabase;
    private static PostDatabase postDatabase;

    /**
     * Main method to start the server.
     *
     * @param args Command line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        userDatabase = new UserDatabase("userdb.txt");
        postDatabase = new PostDatabase("postdb.txt");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running...");
            while (true) {
                Socket clientSocket = serverSocket.accept();  // Accept client connection
                clientHandlers.submit(new ClientHandler(clientSocket));  // Handle client in a separate thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The {@code ClientHandler} class represents a thread that handles communication with a single client.
     * It implements the {@link ClientHandlerInterface} to provide methods for handling client requests.
     * It is responsible for processing client commands and sending appropriate responses.
     *
     * @author Tharun Kumar & Eashen
     */
    // Handles communication with a client
    public static class ClientHandler implements Runnable, ClientHandlerInterface {
        private Socket socket;
        public BufferedReader in;
        public PrintWriter out;
        private User currentUser;

        /**
         * Constructs a new {@code ClientHandler} with the given client socket.
         *
         * @param socket the socket representing the client connection
         */
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Runs the client handler, establishing input/output streams and handling client commands.
         * This method is executed in a separate thread to allow concurrent handling of multiple clients.
         * <p>
         * It continuously reads commands from the client and processes them (e.g., login, register, create post).
         * </p>
         */
        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                handleClient();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Handles the interaction with the client, prompting for commands and executing them.
         * <p>
         * The method listens for commands such as 'login', 'register', 'create_post', 'view_posts', and 'logout'.
         * For each command, the appropriate method is called to process the client request.
         * </p>
         */
        @Override
        public void handleClient() {
            try {
                out.println("Welcome to the server! Type 'login' to login or 'register' to create a new account.");
                String command;
                while ((command = in.readLine()) != null) {
                    if (command.equals("login")) {
                        login();
                    } else if (command.equals("register")) {
                        register();
                    } else if (command.equals("create_post")) {
                        createPost();
                    } else if (command.equals("view_posts")) {
                        viewPosts();
                    } else if (command.equals("logout")) {
                        logout();
                    } else {
                        out.println("Unknown command: " + command);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Handles user login. Prompts the client for a username and password, and verifies the credentials.
         * If the login is successful, the user is set as the current user.
         */
        @Override
        public void login() {
            out.println("Enter username:");
            String username = readLine();
            out.println("Enter password:");
            String password = readLine();

            User user = userDatabase.getUser(username);
            if (user != null) {
                currentUser = user;
                out.println("Login successful!");
            } else {
                out.println("Invalid credentials.");
            }
        }

        /**
         * Handles user registration. Prompts the client for a username and password and creates a new user.
         * If the username is already taken, the registration fails.
         */
        @Override
        public void register() {
            out.println("Enter a username:");
            String username = readLine();
            out.println("Enter a password:");
            String password = readLine();

            User newUser = new User(username, password);
            if (userDatabase.addUser(newUser)) {
                out.println("Account created successfully!");
            } else {
                out.println("Username already taken.");
            }
        }

        /**
         * Handles creating a new post. The client must be logged in to create a post.
         * Prompts the user for the title and content of the post, and adds it to the post database.
         */
        @Override
        public void createPost() {
            if (currentUser == null) {
                out.println("You must be logged in to create a post.");
                return;
            }

            out.println("Enter the post title:");
            String title = readLine();
            out.println("Enter the post content:");
            String content = readLine();

            Post newPost = new Post(title, content, currentUser.getUsername());
            if (postDatabase.addPost(newPost)) {
                out.println("Post created successfully!");
            } else {
                out.println("Failed to create post.");
            }
        }

        /**
         * Displays all posts. The client must be logged in to view posts.
         * Each post is retrieved from the post database and displayed to the client.
         */
        @Override
        public void viewPosts() {
            if (currentUser == null) {
                out.println("You must be logged in to view posts.");
                return;
            }

            Map<String, Post> posts = postDatabase.loadPosts();
            for (Post post : posts.values()) {
                out.println(post);
            }
        }

        /**
         * Logs the current user out. If no user is logged in, it informs the client.
         */
        @Override
        public void logout() {
            if (currentUser != null) {
                currentUser.logOut();
                out.println("Logged out successfully.");
                currentUser = null;
            } else {
                out.println("You are not logged in.");
            }
        }

        /**
         * Helper method to read a line of input from the client.
         *
         * @return the line of input read from the client
         */
        @Override
        public String readLine() {
            try {
                return in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
    }
}
