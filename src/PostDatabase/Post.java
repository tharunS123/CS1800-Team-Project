package src.PostDatabase;

import src.PostDatabase.OldCode.PostDatabaseOld;

import java.io.IOException;
import java.io.Serializable;
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
public class Post implements Serializable {
    private static final long serialVersionUID = 8796768980835010220L; //1L;
    private UUID id;
    private String title;
    private String content;
    private String author;
    private Timestamp date;
    private int upVote;
    private int downVote;
    private ArrayList<String> comments;

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
     * Constructs a new Post from a string of data.
     *
     * @param data the string containing post data
     * @throws IOException if the data is invalid
     */
    public Post(String data) throws IOException {
        try {
            String[] info = data.split("]\n");
            this.id = UUID.fromString(info[0]);
            this.title = info[1];
            this.content = info[2];
            this.author = info[3];
            this.date = Timestamp.valueOf(info[4]);
            this.upVote = Integer.parseInt(info[5]);
            this.downVote = Integer.parseInt(info[6]);
        } catch (Exception e) {
            throw new IOException("Bad Post Data");
        }
    }

    /**
     * Constructs a new Post with default values when an IOException occurs.
     *
     * @param e the IOException that occurred
     */
    public Post(IOException e) {
        String str = e.getMessage();

        this.title = "Bad Post Data";
        this.content = "Bad Post Data";
        this.author = "Bad Post Data";
        this.date = new Timestamp(System.currentTimeMillis());
        this.comments = new ArrayList<>();
        this.upVote = 0;
        this.downVote = 0;
        this.id = UUID.randomUUID();
    }

    /**
     * Checks if the given ID is used in the PostDatabase.
     *
     * @param id the ID to check
     * @return true if the ID is used, false otherwise
     */
    private boolean checkIdUsed(int id) {
        // check if id is used
        PostDatabaseOld db = new PostDatabaseOld("postdatabase.txt");
        return db.getPostID(id);
    }

    /**
     * Returns the ID of the post.
     *
     * @return the ID of the post
     */
    public UUID getID() {
        return id;
    }

    /**
     * Sets the ID of the post.
     *
     * @param id the new ID of the post
     */
    public void setID(UUID id) {
        this.id = id;
    }

    /**
     * Adds a comment to the post.
     *
     * @param comment the comment to add
     */
    public void addComment(String comment) {
        comments.add(comment);
    }

    /**
     * Returns the list of comments on the post.
     *
     * @return the list of comments
     */
    public ArrayList<String> getComments() {
        return comments;
    }

    /**
     * Returns the title of the post.
     *
     * @return the title of the post
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the content of the post.
     *
     * @return the content of the post
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the author of the post.
     *
     * @return the author of the post
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the timestamp of the post.
     *
     * @return the timestamp of the post
     */
    public long getTime() {
        return date.getTime();
    }

    /**
     * Returns the date and time of the post as a string.
     *
     * @return the date and time of the post
     */
    public String getDataAndTime() {
        return date.toString();
    }

    /**
     * Sets the title of the post.
     *
     * @param title the new title of the post
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the content of the post.
     *
     * @param content the new content of the post
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets the author of the post.
     *
     * @param author the new author of the post
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Adds a comment to the post.
     *
     * @param comments the comment to add
     */
    public void setComments(String comments) {
        this.comments.add(comments);
    }

    /**
     * Returns the number of upvotes the post has received.
     *
     * @return the number of upvotes
     */
    public int getUpVote() {
        return upVote;
    }

    /**
     * Increments the number of upvotes by one.
     */
    public void upVote() {
        upVote++;
    }

    /**
     * Returns the number of downvotes the post has received.
     *
     * @return the number of downvotes
     */
    public int getDownVote() {
        return downVote;
    }

    /**
     * Increments the number of downvotes by one.
     */
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
