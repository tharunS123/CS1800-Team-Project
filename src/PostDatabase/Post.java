package src.PostDatabase;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

public class Post {
    private UUID id;
    private String title;
    private String content;
    private String author;
    private Timestamp date;
    private int upVote;
    private int downVote;
    private ArrayList<String> comments;

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

    private boolean checkIdUsed(int id) {
        // check if id is used
        PostDatabase db = new PostDatabase("postdatabase.txt");
        return db.getPostID(id);
    }

    public UUID getID() {
        return id;
    }

    public void setID(UUID id) {
        this.id = id;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public long getTime() {
        return date.getTime();
    }

    public String getDataAndTime() {
        return date.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setComments(String comments) {
        this.comments.add(comments);
    }

    public int getUpVote() {
        return upVote;
    }

    public void upVote() {
        upVote++;
    }

    public int getDownVote() {
        return downVote;
    }

    public void downVote() {
        downVote++;
    }

    @Override
    public String toString() {
        return "ID: " + getID() + "\nTitle: " + getTitle() + "\nContent: " +
                getContent() + "\nAuthor: " + getAuthor() + "\nDate: " +
                getDataAndTime() + "\n Upvotes: " + getUpVote() + "\n Downvotes: " + getDownVote() + ", \n"
                + "Comments: " + getComments();
    }
}
