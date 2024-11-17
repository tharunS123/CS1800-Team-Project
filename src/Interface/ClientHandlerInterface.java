package src.Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The ClientHandlerInterface defines the essential functionality for managing a client handler,
 * such as handling login, signup, and creating posts.
 *
 * @author Tharun Kumar Senthilkumar
 */
public interface ClientHandlerInterface {
    /**
     * this run method is used to run the client handler.
     */
    void run();

    /**
     * This method is used to handle the login of the user.
     * @param input
     * @param output
     * @throws IOException
     */
    void handleLogin(BufferedReader input, PrintWriter output) throws IOException;

    /**
     * This method is used to handle the signup of the user.
     * @param input
     * @param output
     * @throws IOException
     */
    void handleSignup(BufferedReader input, PrintWriter output) throws IOException;

    /**
     * This method is used to handle the create post of the user.
     * @param input
     * @param output
     * @throws IOException
     */
    void handleCreatePost(BufferedReader input, PrintWriter output) throws IOException;

    /**
     * This method is used to handle the view posts of the user.
     * @param input
     * @param output
     * @throws IOException
     */
    void handleViewPosts(BufferedReader input, PrintWriter output) throws IOException;
}
