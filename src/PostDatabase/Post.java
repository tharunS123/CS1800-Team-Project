package src.PostDatabase;

import java.util.ArrayList;

public class Post {
    private int id;
    private String title;
    private String content;
    private String author;
    private String date;
    private int upVote;
    private int downVote;
    private ArrayList<String> comments;

    public Post(String title, String content, String author, String date) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.comments = new ArrayList<>();
        this.upVote = 0;
        this.downVote = 0;
        this.id = 0;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
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

    public String getDate() {
        return date;
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
        return "Title: " + title + "\nContent: " +
                content + "\nAuthor: " + author + "\nDate: " +
                date + "\n Upvotes: " + upVote + "\n Downvotes: " + downVote;
    }
}
