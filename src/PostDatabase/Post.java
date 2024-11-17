package src.PostDatabase;

import src.Interface.PostInterface;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The Post class represents a post in the PostDatabase. It includes details such as title, content, author, date, upvotes, downvotes, and comments.
 * It provides methods to manipulate and retrieve these details.
 *
 * @version Nov 2, 2024
 * @author Tharun Kumar and Mateo Toro Felipe
 */
public class Post implements PostInterface {
//    private static final long serialVersionUID = 8796768980835010220L; //1L;
    private UUID id;
    private String title;
    private String content;
    private String author;
    private Timestamp date;
    private int upVote;
    private int downVote;
    private ArrayList<Comment> comments;

    /**
     * Constructs a new Post with the specified title, content, and author.
     *
     * @param title   the title of the post
     * @param content the content of the post
     * @param author  the author of the post
     */
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = new Timestamp(System.currentTimeMillis());
        this.comments = new ArrayList<>();
        this.upVote = 0;
        this.downVote = 0;
        this.id = UUID.randomUUID();
    }

    /**
     * Returns the ID of the post.
     *
     * @return the ID of the post
     */
    @Override
    public UUID getID() {
        return id;
    }

    /**
     * Sets the ID of the post.
     *
     * @param id the new ID of the post
     */
    @Override
    public void setID(UUID id) {
        this.id = id;
    }

    /**
     * Adds a comment to the post.
     *
     * @param comment the comment to add
     * @return
     */
    @Override
    public boolean addComment(Comment comment) {
        comments.add(comment);
        return true;
    }

    /**
     * Returns the list of comments on the post.
     *
     * @return the list of comments
     */
    @Override
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * Returns the title of the post.
     *
     * @return the title of the post
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Returns the content of the post.
     *
     * @return the content of the post
     */
    @Override
    public String getContent() {
        return content;
    }

    /**
     * Returns the author of the post.
     *
     * @return the author of the post
     */
    @Override
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the timestamp of the post.
     *
     * @return the timestamp of the post
     */
    @Override
    public long getTime() {
        return date.getTime();
    }

    /**
     * Returns the date and time of the post as a string.
     *
     * @return the date and time of the post
     */
    @Override
    public String getDataAndTime() {
        return date.toString();
    }

    /**
     * Sets the title of the post.
     *
     * @param title the new title of the post
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the content of the post.
     *
     * @param content the new content of the post
     */
    @Override
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets the author of the post.
     *
     * @param author the new author of the post
     */
    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Adds a comment to the post.
     *
     * @param comments the comment to add
     */
    @Override
    public void addComments(Comment comments) {
        this.comments.add(comments);
    }

    /**
     * Returns the number of upvotes the post has received.
     *
     * @return the number of upvotes
     */
    @Override
    public int getUpVote() {
        return upVote;
    }

    /**
     * Increments the number of upvotes by one.
     */
    @Override
    public void upVote() {
        upVote++;
    }

    /**
     * Returns the number of downvotes the post has received.
     *
     * @return the number of downvotes
     */
    @Override
    public int getDownVote() {
        return downVote;
    }

    /**
     * Increments the number of downvotes by one.
     */
    @Override
    public void downVote() {
        downVote++;
    }

    /**
     * Returns a string representation of the post.
     *
     * @return a string representation of the post
     */
    @Override
    public String toString() {
        return "ID: " + getID() + ",\nTitle: " + getTitle() + ",\nContent: " +
                getContent() + ",\nAuthor: " + getAuthor() + ",\nDate: " +
                getDataAndTime() + ",\n Upvotes: " + getUpVote() + ",\n Downvotes: " + getDownVote() + ", \n"
                + "Comments: " + getComments() + "\n ";
    }
}
