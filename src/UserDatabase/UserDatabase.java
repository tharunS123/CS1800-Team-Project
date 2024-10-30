import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.UUID;

public class UserDatabase {
    private final File dbFile;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public UserDatabase(String fileName) {
        this.dbFile = new File(fileName);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
                saveUsers(new HashMap<>()); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean addUser(User user) {
        lock.writeLock().lock();
        try {
            Map<String, User> users = loadUsers();
            if (users.containsKey(user.getUsername())) return false; 
            users.put(user.getUsername(), user);
            saveUsers(users);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public User getUser(String username) {
        lock.readLock().lock();
        try {
            Map<String, User> users = loadUsers();
            return users.get(username);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public boolean updateUser(User user) {
        lock.writeLock().lock();
        try {
            Map<String, User> users = loadUsers();
            if (!users.containsKey(user.getUsername())) return false;
            users.put(user.getUsername(), user);
            saveUsers(users);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, User> loadUsers() {
        if (dbFile.length() == 0) return new HashMap<>(); 
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dbFile))) {
            return (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    
    private void saveUsers(Map<String, User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

