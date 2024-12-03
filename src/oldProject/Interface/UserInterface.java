package src.oldProject.Interface;

import src.oldProject.UserDatabase.Tuple;
import src.oldProject.UserDatabase.User;

import java.util.List;

/**
 * The UserInterface defines the essential functionality for managing user information
 * such as login, logout, adding/removing friends, and retrieving user details.
 *
 * @version Nov 2, 2024
 * @author Tharun Kumar Senthilkumar
 */
public interface UserInterface {
    /**
     * Retrieves the username of the user.
     *
     * @return the username of the user
     */
    String getUsername();

    /**
     * Retrieves the unique UUID associated with the user.
     *
     * @return the UUID of the user
     */
    String getUuid();

    /**
     * Retrieves the password of the user.
     *
     * @return the password of the user
     */
    String getPassword();

    /**
     * Checks if the user is currently logged in.
     *
     * @return true if the user is logged in, false otherwise
     */
    boolean getLoggedIn();

    /**
     * Retrieves a list of friends for the user.
     *
     * @return a list of friends' usernames
     */
    List<String> getFriends();

    /**
     * Attempts to log the user in by checking the provided password.
     *
     * @param password the password to check
     * @return a Tuple containing a boolean indicating the success of the login attempt and the user
     */
    Tuple<Boolean, User> logIn(String password);

    /**
     * Logs the user out.
     */
    void logOut();

    /**
     * Adds a new friend to the user's friend list.
     *
     * @param friendUsername the username of the friend to add
     */
    void addFriend(String friendUsername);

    /**
     * Removes a friend from the user's friend list.
     *
     * @param friendUsername the username of the friend to remove
     */
    void removeFriend(String friendUsername);
}
