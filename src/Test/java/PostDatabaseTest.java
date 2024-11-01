package src.Test.java;

import org.junit.Test;
import src.PostDatabase.Post;
import src.PostDatabase.PostDatabase;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PostDatabaseTest {

    private final String testFileName = "test_postdatabase.txt";

    @Test
    public void testAddPost() {
        PostDatabase postDatabase = new PostDatabase(testFileName);
        Post post = new Post("Test Title", "Test Content", "Author");

        assertTrue(postDatabase.addPost(post)); // Test if adding a post returns true
    }

    @Test
    public void testSavePostFile() throws Exception {
        PostDatabase postDatabase = new PostDatabase(testFileName);
        Post post = new Post("Test Title", "Test Content", "Author");
        postDatabase.addPost(post); // Save the post

        // Verify that the file exists after adding a post
        File file = new File(testFileName);
        assertTrue(file.exists());

        // Cleanup the test file
        file.delete();
    }

    @Test
    public void testReadPostDatabase() throws Exception {
        PostDatabase postDatabase = new PostDatabase(testFileName);
        Post post = new Post("Test Title", "Test Content", "Author");
        postDatabase.addPost(post); // Add a post

        ArrayList<Post> postArray = postDatabase.readPostDatabase(testFileName);
        assertFalse(postArray.isEmpty()); // Ensure the read array is not empty
    }

    @Test
    public void testGetPosts() {
        PostDatabase postDatabase = new PostDatabase(testFileName);
        Post post = new Post("Test Title", "Test Content", "Author");
        postDatabase.addPost(post); // Add a post

        ArrayList<Post> posts = postDatabase.getPosts();
        assertEquals(1, posts.size()); // Verify the size of posts
        assertEquals(post.getID(), posts.get(0).getID()); // Ensure the added post matches
    }

//    @Test
//    public void testGetPostID() {
//        PostDatabase postDatabase = new PostDatabase(testFileName);
//        Post post = new Post("Test Title", "Test Content", "Author", new Timestamp(System.currentTimeMillis()).toString());
//        postDatabase.addPost(post); // Add a post
//
//        boolean exists = postDatabase.getPostID(post.getID().hashCode()); // Check for post ID
//        assertTrue(exists); // Ensure the ID exists
//    }

    @Test
    public void testGetPostIDReturnsFalseForNonexistentID() {
        PostDatabase postDatabase = new PostDatabase(testFileName);
        boolean exists = postDatabase.getPostID(-1); // Check for a nonexistent ID
        assertFalse(exists); // Ensure it returns false
    }

    @Test
    public void testAddPostHandlesNull() {
        PostDatabase postDatabase = new PostDatabase(testFileName);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> postDatabase.addPost(null)); // Expect RuntimeException
    }
}
