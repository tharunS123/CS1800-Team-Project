package src.PostDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PostDatabase {
    private String file;
    private ArrayList<Post> post;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public PostDatabase(String filename) {
        this.file = filename;
        try {
            this.post = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addPost(Post post) {
        lock.writeLock().lock();
        try {
            // Add post to file
            if (this.post.contains(post.getTitle())) {
                return false;
            }
            this.post.add(post);
            savePostFile(this.post);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void savePostFile(ArrayList<Post> post) {
        // Save post to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append(post.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readPostDatabase(String filename) throws Exception {
        // Read post from file
        ArrayList<String> postArray = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                postArray.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Display all post in the database for test case purpose
        for (String post : postArray) {
            System.out.println(post);
        }
    }

    public ArrayList<Post> getPosts() {
        lock.readLock().lock();
        try {
            // Get all posts from file
            return this.post;
        } finally {
            lock.readLock().unlock();
        }
    }
}
