package src.Interface;

import src.PostDatabase.Comment;

import java.util.List;
import java.util.UUID;

/**
 * The PostInterface defines the core functionality expected from any Post-related class.
 * It includes methods for managing the post's details, such as the title, content, author, votes, and comments.
 *
 * @version Nov 2, 2024
 * @author Tharun Kumar and Mateo Toro Felipe
 */
public interface PostInterface {

    /**
     * Gets the unique ID of the post.
     *
     * @return the ID of the post
     */
    UUID getID();

    /**
     * Sets the unique ID of the post.
     *
     * @param id the new ID to set
     */
    void setID(UUID id);

    /**
     * Gets the title of the post.
     *
     * @return the title of the post
     */
    String getTitle();

    /**
     * Sets the title of the post.
     *
     * @param title the new title of the post
     */
    void setTitle(String title);

    /**
     * Gets the content of the post.
     *
     * @return the content of the post
     */
    String getContent();

    /**
     * Sets the content of the post.
     *
     * @param content the new content of the post
     */
    void setContent(String content);

    /**
     * Gets the author of the post.
     *
     * @return the author of the post
     */
    String getAuthor();

    /**
     * Sets the author of the post.
     *
     * @param author the new author of the post
     */
    void setAuthor(String author);

    /**
     * Gets the timestamp of the post.
     *
     * @return the timestamp of the post
     */
    long getTime();

    /**
     * Gets the date and time as a string representation.
     *
     * @return the date and time of the post
     */
    String getDataAndTime();

    /**
     * Adds a comment to the post.
     *
     * @param comment the comment to add
     * @return true if the comment was successfully added
     */
    boolean addComment(Comment comment);

    /**
     * Gets the list of comments on the post.
     *
     * @return the list of comments
     */
    List<Comment> getComments();

    void addComments(Comment comments);

    /**
     * Gets the number of upvotes the post has received.
     *
     * @return the number of upvotes
     */
    int getUpVote();

    /**
     * Increments the number of upvotes by one.
     */
    void upVote();

    /**
     * Gets the number of downvotes the post has received.
     *
     * @return the number of downvotes
     */
    int getDownVote();

    /**
     * Increments the number of downvotes by one.
     */
    void downVote();

    /**
     * Returns a string representation of the post.
     *
     * @return a string representation of the post
     */
    String toString();
}
