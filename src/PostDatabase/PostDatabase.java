package src.PostDatabase;

import src.PostDatabase.Interface.PostDatabaseInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PostDatabase implements PostDatabaseInterface {
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

    @Override
    public void savePosts(Map<String, Post> posts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            oos.writeObject(posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
