package src.PostDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
            savePostFile(post);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void savePostFile(Post post) {
        // Save post to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append(post.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Post> readPostDatabase(String filename) throws Exception {
        lock.readLock().lock();
        // Read post from file

        ArrayList<Post> postArray = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))  {
            String line;
            while ((line = br.readLine()) != null) {
                // TODO: Parse the line and create a Post object and store in the postArray
                String[] postInfo = line.split("]");
////                String[] postInfo = line.split("]");
////                Post post = new Post(postInfo[0], postInfo[1], postInfo[2], postInfo[3]);
////                postArray.add(post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

        for (Post post : postArray) {
            System.out.println(post.getAuthor());
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
            ArrayList<Post> postArray = readPostDatabase(file);

            for (Post post : postArray) {
//                if (post.contains("ID: " + id)) {
//                    return true;
//                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }
}
