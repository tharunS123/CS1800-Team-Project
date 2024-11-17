package src.PostDatabase;

import src.Interface.CommentInterface;

/**
 * The CommentInterface defines the essential functionality for managing a comment,
 * including getting the content and the user associated with the comment.
 *
 * @version Nov 2, 2024
 * @author Tharun Kumar Senthilkumar
 */
public class Comment implements CommentInterface {
    private String content;
    private String user;

    public Comment(String content, String user) {
        this.content = content;
        this.user = user;
    }

    /**
     * Gets the content of the comment.
     *
     * @return the content of the comment
     */
    @Override
    public String getContent() {
        return content;
    }

    /**
     * Gets the user who made the comment.
     *
     * @return the user who made the comment
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * Returns a string representation of the comment.
     *
     * @return the string representation of the comment (user + content)
     */
    @Override
    public String toString() {
        return user + ": " + content;
    }
}
