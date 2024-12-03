package src.oldProject.PostDatabase;

import src.oldProject.Interface.PostDatabaseInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The PostDatabase class provides functionality to manage posts in a persistent storage.
 * It implements the {@link src.oldProject.Interface.PostDatabaseInterface} to support operations
 * such as adding, retrieving, updating, deleting, and looking up posts.
 *
 * @author Tharun Kumar Senthilkumar and Mateo Toro Felipe
 **/
public class PostDatabase implements PostDatabaseInterface {
    /**
     * The file representing the database for storing posts.
     */
    private final File dbFile;

    /**
     * A lock mechanism to ensure thread-safe read and write operations on the database.
     */
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Constructs a new PostDatabase object and initializes the database file.
     * If the file does not exist, it creates a new file and initializes it with an empty post map.
     *
     * @param fileName The name of the file to use for storing posts.
     */
    public PostDatabase(String fileName) {
        this.dbFile = new File(fileName);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
                savePosts(new HashMap<>());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds a new post to the database.
     *
     * @param post The post to add.
     * @return {@code true} if the post was successfully added, {@code false} otherwise.
     */
    @Override
    public boolean addPost(Post post) {
        lock.writeLock().lock();
        try {
            Map<String, Post> posts = loadPosts();
            posts.put(post.toString(), post);
            savePosts(posts);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Retrieves a post from the database based on its title.
     *
     * @param title The title of the post to retrieve.
     * @return The post corresponding to the given title, or {@code null} if no such post exists.
     */
    @Override
    public Post getPost(String title) {
        lock.readLock().lock();
        try {
            Map<String, Post> posts = loadPosts();
            return posts.get(title);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Loads all posts from the database.
     *
     * @return A map of posts where the key is the string representation of the post,
     * and the value is the post object.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Post> loadPosts() {
        if (dbFile.length() == 0) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dbFile))) {
            return (Map<String, Post>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * Saves a map of posts to the database.
     *
     * @param posts A map of posts to save.
     */
    @Override
    public void savePosts(Map<String, Post> posts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            oos.writeObject(posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a specified post from the database.
     *
     * @param post The post to delete.
     * @return {@code true} if the post was successfully deleted, {@code false} otherwise.
     */
    @Override
    public boolean deletePost(Post post) {
        lock.writeLock().lock();
        try {
            Map<String, Post> posts = loadPosts();
            posts.remove(post.toString(), post);
            System.out.println(posts);
            savePosts(posts);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Updates an existing post in the database.
     *
     * @param post The post with updated information.
     * @return {@code true} if the post was successfully updated, {@code false} otherwise.
     */
    @Override
    public boolean updatePost(Post post) {
        lock.writeLock().lock();
        try {
            Map<String, Post> posts = loadPosts();
            posts.put(post.toString(), post);
            savePosts(posts);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Looks up a post by its title in the database.
     *
     * @param title The title of the post to look up.
     * @return The post corresponding to the given title, or {@code null} if no such post exists.
     */
    @Override
    public Post lookUpPost(String title) {
        lock.readLock().lock();
        try {
            Map<String, Post> posts = loadPosts();
            for (Post post : posts.values()) {
                if (post.getTitle().equals(title)) {
                    return post;
                }
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }
}
