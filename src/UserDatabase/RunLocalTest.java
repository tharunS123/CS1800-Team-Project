package src.UserDatabase;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.util.List;

/**
 * The TestCase class is a JUnit test class for testing the functionality of the UserDatabase
 * and User classes. It includes setup and cleanup methods to manage a test database file
 * The test cases are designed to ensure that UserDatabase methods work as expected
 * @author Abdullah and Eashan
 * @version Oct 31 2024
 */
public class RunLocalTest {
	/**
	 * the name given to the test database file(.txt file),
	 * so that the working database is not disturbed
	 */
    private static final String TEST_DB_FILE = "test_user_database.txt";
    private static UserDatabase userDatabase;

    @BeforeClass
    public static void setup() {
        // initializing UserDatabase with a test file
        userDatabase = new UserDatabase(TEST_DB_FILE);
    }
    /**
     * Cleans up the test environment by deleting the test database file after all tests have been
     * executed. This ensures no leftover files remain from testing.
     */
    @AfterClass
    public static void cleanup() {
        // Delete test file after all tests to ensure isolation
        File dbFile = new File(TEST_DB_FILE);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    @Test
    public void testDatabaseFileCreation() {
        // Check if the database file was created
        File dbFile = new File(TEST_DB_FILE);
        assertTrue("Database file should be created if it doesn't exist", dbFile.exists());
    }
    /**
     * Tests adding a user to the database and then logging in with the correct password.
     * It verifies that the user can be retrieved from the database and that login is successful.
     */
    @Test
    public void testAddUserAndLogin() {
        User user = new User("testUser", "testPassword");

        // Add user and verify it was added
        boolean isAdded = userDatabase.addUser(user);
        assertTrue("User should be added successfully", isAdded);

        // Attempt login with the correct password
        User retrievedUser = userDatabase.getUser("testUser");
        assertNotNull("User should be retrievable from the database", retrievedUser);
        assertTrue("User should be able to log in with the correct password", retrievedUser.logIn("testPassword").x);
    }
    /**
     * Tests the ability to add a friend to a user's friend list. It verifies that the friend
     * is added and saved in the database correctly.
     */
    @Test
    public void testAddFriend() {
        User user = userDatabase.getUser("testUser");

        // Add a friend to the user
        user.addFriend("friendUser");
        userDatabase.updateUser(user);

        // Retrieve user and check if friend was added
        User updatedUser = userDatabase.getUser("testUser");
        List<String> friends = updatedUser.getFriends();
        assertTrue("Friend should be added to the user's friend list", friends.contains("friendUser"));
    }
    /**
     * Tests the ability to log out a user. It logs in a user, verifies they are logged in,
     * then logs them out and verifies the user is no longer logged in.
     */
    @Test
    public void testLogout() {
        // Check if user exists; if not, add it to the database
        User user = userDatabase.getUser("testUser");
        if (user == null) {
            user = new User("testUser", "testPassword");
            userDatabase.addUser(user);
        }

        // Log in the user to ensure they are logged in before logging out
        Tuple<Boolean, User> loginResult = user.logIn("testPassword");
        assertTrue("User should be able to log in with the correct password", loginResult.x);
        assertTrue("User should be logged in", user.getLoggedIn());

        // Log out the user
        user.logOut();
        assertFalse("User should be logged out", user.getLoggedIn());
    }

    /**
     * Tests deleting a user from the database. It verifies that the user is removed and no
     * longer exists in the database after deletion.
     */
    @Test
    public void testDeleteUser() {
        User user = userDatabase.getUser("testUser");

        // Delete user and check that it no longer exists in the database
        boolean isDeleted = userDatabase.deleteUser(user, "testPassword");
        assertTrue("User should be deleted successfully", isDeleted);

        User deletedUser = userDatabase.getUser("testUser");
        assertNull("Deleted user should no longer be in the database", deletedUser);
    }
}
