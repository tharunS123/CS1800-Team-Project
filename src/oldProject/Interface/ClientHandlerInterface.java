package src.oldProject.Interface;

import java.io.IOException;

/**
 * The ClientHandlerInterface defines the essential functionality for managing a client handler,
 * such as handling login, signup, and creating posts.
 *
 * @author Tharun Kumar Senthilkumar
 */
public interface ClientHandlerInterface {
    /**
     * The main entry point for handling the client. This method is responsible for managing
     * the overall lifecycle of the client connection, including communication and processing
     * of requests.
     */
    void run();

    /**
     * Handles the communication and logic for interacting with the client.
     * This method should implement the specific actions for different client commands such as login,
     * register, create post, etc.
     */
    void handleClient();

    /**
     * Handles the login process for the client. It should prompt for a username and password,
     * validate the credentials, and return a response indicating whether the login was successful.
     */
    void login();

    /**
     * Handles the registration process for the client. It should prompt for a username and password,
     * create a new user if the username is available, and return an appropriate success or failure response.
     */
    void register();

    /**
     * Allows the logged-in user to create a new post. It should prompt for post details such as title
     * and content, validate the post, and store it in the appropriate database or collection.
     */
    void createPost();

    /**
     * Displays all posts available in the system. It should fetch the posts from the database or collection
     * and send them to the client.
     */
    void viewPosts();

    /**
     * Logs the current user out of the system. This should clear any session-related data for the user
     * and send a response to the client indicating successful logout.
     */
    void logout();

    /**
     * Reads a line of input from the client. This method abstracts reading input, ensuring that the client
     * can provide input during interactions such as login, registration, and post creation.
     *
     * @return the line of text entered by the client
     * @throws IOException if an input error occurs
     */
    String readLine();
}
