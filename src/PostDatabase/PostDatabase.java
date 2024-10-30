package src.PostDatabase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(post.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Post getPost(int id) {
        lock.readLock().lock();
        try {
            // Get post from file
            return this.post.get(id);
        } finally {
            lock.readLock().unlock();
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
