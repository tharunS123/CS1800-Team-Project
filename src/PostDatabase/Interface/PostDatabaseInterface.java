package src.PostDatabase.Interface;

import src.PostDatabase.Post;

import java.util.Map;

/**
 * The PostDatabaseInterface defines the essential functionality for managing a collection of posts.
 * It includes methods for adding, retrieving, updating, deleting, and looking up posts in the database.
 *
 * @version Nov 2, 2024
 * @author Tharun Kumar Senthilkumar
 */
public interface PostDatabaseInterface {

    /**
     * Adds a new post to the database.
     *
     * @param post the post to be added
     * @return true if the post was successfully added, false otherwise
     */
    boolean addPost(Post post);

    /**
     * Retrieves a post by its title.
     *
     * @param title the title of the post
     * @return the post with the given title, or null if no such post exists
     */
    Post getPost(String title);

    /**
     * Retrieves all posts stored in the database.
     *
     * @return a map of all posts, with the title as the key and the post as the value
     */
    Map<String, Post> loadPosts();

    void savePosts(Map<String, Post> posts);

    /**
     * Deletes a post from the database.
     *
     * @param post the post to be deleted
     * @return true if the post was successfully deleted, false otherwise
     */
    boolean deletePost(Post post);

    /**
     * Updates an existing post in the database.
     *
     * @param post the post to be updated
     * @return true if the post was successfully updated, false otherwise
     */
    boolean updatePost(Post post);

    /**
     * Looks up a post by its title in the database.
     *
     * @param title the title of the post to look up
     * @return the post with the given title, or null if no such post exists
     */
    Post lookUpPost(String title);
}
