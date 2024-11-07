package src.PostDatabase;

import src.UserDatabase.User;

public class Comment {
    private String content;
    private String user;

    public Comment(String content, String user) {
        this.content = content;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user + ": " + content;
    }
}
