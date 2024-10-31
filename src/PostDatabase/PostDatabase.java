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
            this.post.add(post);
            savePostFile(this.post);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    public ArrayList<String> readPostDatabase(String filename) throws Exception {
        lock.readLock().lock();
        // Read post from file
        ArrayList<String> postArray = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                postArray.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

        return postArray;
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

    public boolean getPostID(int id) {
        lock.readLock().lock();
        try {
            ArrayList<String> postArray = readPostDatabase(file);

            for (String post : postArray) {
                if (post.contains("ID: " + id)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }
}
