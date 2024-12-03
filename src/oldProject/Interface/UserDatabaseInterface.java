package src.oldProject.Interface;

import src.oldProject.UserDatabase.User;

import java.util.Map;

/**
 * The UserDatabaseInterface defines the essential functionality for managing a collection of users
 * such as adding, removing, updating, and retrieving users from the database.
 *
 * @version Nov 2, 2024
 * @author Tharun Kumar Senthilkumar
 */
public interface UserDatabaseInterface {
    /**
     * Adds a new user to the database.
     *
     * @param user the user to add
     * @return true if the user was added successfully, false if the user already exists
     */
    boolean addUser(User user);

    /**
     * Retrieves a user from the database by their username.
     *
     * @param username the username of the user to retrieve
     * @return the User object associated with the username, or null if not found
     */
    User getUser(String username);

    /**
     * Updates the details of an existing user in the database.
     *
     * @param user the user object with updated information
     * @return true if the user was updated successfully, false if the user was not found
     */
    boolean updateUser(User user);

    /**
     * Removes a user from the database.
     *
     * @param 'username' the username of the user to remove
     * @param password the password for security verification
     * @return true if the user was removed successfully, false if the password didn't match
     */
    boolean deleteUser(User user, String password);

    /**
     * Loads all users from the database and returns them as a map of usernames to User objects.
     *
     * @return a map containing all users, keyed by their username
     */
    Map<String, User> loadUsers();
}
