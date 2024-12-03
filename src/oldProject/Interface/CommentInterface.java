package src.oldProject.Interface;

/**
 * The CommentInterface defines the essential functionality for managing a comment,
 * including getting the content and the user associated with the comment.
 *
 * @version Nov 2, 2024
 * @author Tharun Kumar Senthilkumar
 */
public interface CommentInterface {

    /**
     * Gets the content of the comment.
     *
     * @return the content of the comment
     */
    String getContent();

    /**
     * Gets the user who made the comment.
     *
     * @return the user who made the comment
     */
    String getUser();

    /**
     * Returns a string representation of the comment.
     *
     * @return the string representation of the comment (user + content)
     */
    String toString();
}
