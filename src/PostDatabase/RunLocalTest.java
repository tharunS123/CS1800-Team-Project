package src.PostDatabase;

import org.junit.Test;
import src.PostDatabase.OldCode.PostDatabaseOld;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * The RunLocalTest class contains unit tests for the PostDatabase class.
 * It tests the functionality of adding, saving, reading, and retrieving posts from the database.
 *
 * @version Nov 2 2024
 * @author Tharun Kumar and Mateo Toro Felipe
 */
public class RunLocalTest {
    private final String testFileName = "test_postdatabase.txt";

    /**
     * Tests if adding a post to the database returns true.
     */
    @Test
    public void testAddPost() {
        PostDatabaseOld postDatabase = new PostDatabaseOld(testFileName);
        Post post = new Post("Test Title", "Test Content", "Author");

        assertTrue(postDatabase.addPost(post)); // Test if adding a post returns true
    }

    /**
     * Tests if a post is saved to the file and verifies the file's existence.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testSavePostFile() throws Exception {
        PostDatabaseOld postDatabase = new PostDatabaseOld(testFileName);
        Post post = new Post("Test Title", "Test Content", "Author");
        postDatabase.addPost(post); // Save the post

        // Verify that the file exists after adding a post
        File file = new File(testFileName);
        assertTrue(file.exists());

        // Cleanup the test file
        file.delete();
    }

    /**
     * Tests if posts can be read from the database file.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testReadPostDatabase() throws Exception {
        PostDatabaseOld postDatabase = new PostDatabaseOld(testFileName);
        Post post = new Post("Test Title", "Test Content", "Author");
        postDatabase.addPost(post); // Add a post

        ArrayList<String> postArray = postDatabase.readPostDatabase(testFileName);
        assertFalse(postArray.isEmpty()); // Ensure the read array is not empty
    }

    /**
     * Tests if all posts in the database can be retrieved.
     */
    @Test
    public void testGetPosts() {
        PostDatabaseOld postDatabase = new PostDatabaseOld(testFileName);
        Post post = new Post("Test Title", "Test Content", "Author");
        postDatabase.addPost(post); // Add a post

        ArrayList<Post> posts = postDatabase.getPosts();
        assertEquals(1, posts.size()); // Verify the size of posts
        assertEquals(post.getID(), posts.get(0).getID()); // Ensure the added post matches
    }

    /**
     * Tests if a post with a specific ID exists in the database.
     */
    @Test
    public void testGetPostID() {
        PostDatabaseOld postDatabase = new PostDatabaseOld(testFileName);
        Post post = new Post("Test Title", "Test Content", "Author");
        postDatabase.addPost(post); // Add a post

        boolean exists = postDatabase.getPostID(post.getID().hashCode()); // Check for post ID
        assertTrue(exists); // Ensure the ID exists
    }

    /**
     * Tests if checking for a nonexistent post ID returns false.
     */
    @Test
    public void testGetPostIDReturnsFalseForNonexistentID() {
        PostDatabaseOld postDatabase = new PostDatabaseOld(testFileName);
        boolean exists = postDatabase.getPostID(-1); // Check for a nonexistent ID
        assertFalse(exists); // Ensure it returns false
    }

    /**
     * Tests if adding a null post throws a RuntimeException.
     */
    @Test
    public void testAddPostHandlesNull() {
        PostDatabaseOld postDatabase = new PostDatabaseOld(testFileName);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> postDatabase.addPost(null)); // Expect RuntimeException
    }
}
