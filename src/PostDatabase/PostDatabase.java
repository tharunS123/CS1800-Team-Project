package src.PostDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PostDatabase {
    private final File dbFile;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

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

    private void savePosts(Map<String, Post> posts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            oos.writeObject(posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
