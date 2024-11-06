package src.PostDatabase.OldCode;

import src.PostDatabase.Post;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The PostDatabase class manages a collection of Post objects. It provides methods to add, read, and retrieve posts from a file.
 * It uses a ReentrantReadWriteLock to ensure thread-safe operations.
 *
 * @version Nov 2, 2024
 */
public class PostDatabaseOld {
    private String file;
    private ArrayList<Post> post;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Constructs a new PostDatabase with the specified filename.
     *
     * @param filename the name of the file to store posts
     */
    public PostDatabaseOld(String filename) {
        this.file = filename;
        try {
            this.post = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a post to the database and saves it to the file.
     *
     * @param post the post to add
     * @return true if the post was added successfully, false otherwise
     */
    public boolean addPost(Post post) {
        lock.writeLock().lock();
        try {
            // Add post to file
            this.post.add(post);
            savePostFile(post);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Saves a post to the file.
     *
     * @param post the post to save
     */
    private void savePostFile(Post post) {
        // Save post to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append(post.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads posts from the specified file and returns them as an ArrayList.
     *
     * @param filename the name of the file to read posts from
     * @return an ArrayList of posts
     * @throws Exception if an error occurs while reading the file
     */
    public ArrayList<String> readPostDatabase(String filename) throws Exception {
        lock.readLock().lock();
        // Read post from file

        ArrayList<String> postArray = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))  {
            String line;
            // TODO: still needed to work on on the logic.
            while ((line = br.readLine()) != null) {
                postArray.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

        return postArray;
    }

    /**
     * Returns all posts in the database.
     *
     * @return an ArrayList of posts
     */
    public ArrayList<Post> getPosts() {
        lock.readLock().lock();
        try {
            // Get all posts from file
            return this.post;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Checks if a post with the specified ID exists in the database.
     *
     * @param id the ID to check
     * @return true if a post with the specified ID exists, false otherwise
     */
    public boolean getPostID(int id) {
        lock.readLock().lock();
        try {

            //TODO: still need to work on it.
            ArrayList<String> postArray = readPostDatabase(file);
            System.out.println(postArray);
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }
}
