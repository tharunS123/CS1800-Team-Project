package src.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import src.PostDatabase.Post;
import src.PostDatabase.PostDatabase;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PostRunLocalTest {
    private static final String TEST_DB_FILE = "test_post_database.txt";
    private static PostDatabase postDatabase;

    @BeforeClass
    public static void setup() {
        // Initialize the PostDatabase with a test file
        postDatabase = new PostDatabase(TEST_DB_FILE);
    }

    @AfterClass
    public static void cleanup() {
        // Delete the test file after all tests have been executed
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

    @Test
    public void testAddPost() {
        Post post = new Post("Test Title", "Test Content", "Author");

        boolean isAdded = postDatabase.addPost(post);
        assertTrue("Post should be added successfully", isAdded);

//        Post retrievedPost = postDatabase.getPost("Test Title");
//        assertNotNull("Post should be retrievable from the database", retrievedPost);
//        assertTrue("Retrieved post should match the added post", retrievedPost.equals(post));
    }

    @Test
    public void testDeletePost() {
        Post post = postDatabase.getPost("Test Title");

        boolean isDeleted = postDatabase.deletePost(post);
        assertTrue("Post should be deleted successfully", isDeleted);

        Post deletedPost = postDatabase.getPost("Test Title");
        assertNull("Post should not be retrievable from the database", deletedPost);
    }
}
