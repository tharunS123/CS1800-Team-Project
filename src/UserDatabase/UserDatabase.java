package src.UserDatabase;

/**import necessary packages (for read write operations and managing concurrent file handling
 * imported javax.swing.text.PasswordView used to hide characters while entering passwords
 */
import src.Interface.UserDatabaseInterface;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The class UserDatabase handles the storage and retrieval of user data (User Database)
 * Stores in a .txt file
 * It uses read and write lock operations for thread safe operations
 *
 * @author Eashan and Abdullah
 * @version 31st October 2024
 */
public class UserDatabase implements UserDatabaseInterface {
    private final File dbFile;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
    * A constructor
    * @param fileName is the name of the file where users are stored
    * Tests whether file exists in order to prevent duplicate files
    * Handles IO related exceptions
    */
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
    
    /**
     * Method to add a user (on if unique user given)
     * Loads all the users
     * @param user, takes the user as an argument
     * @return false , if user already exists (user not added)
     * @return true, if new user created
     */
    @Override
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
    
    /**
     * Method to search for a User object in the database
     * @param username
     * @return Loads the user data from the file and returns the User, 
     * associated with the username
     */
    @Override
    public User getUser(String username) {
        lock.readLock().lock();
        try {
            Map<String, User> users = loadUsers();
            return users.get(username);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * 
     * Method to update a user
     * Loads all user and compares the user to be checked in database
     * @param user
     * @return false if user not found
     * @return true is user found
     */
    @Override
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

    /**
     * loadUsers reads and deserializes user data from the file
     * @return , returns  a Map<String, User>, i.e. usernames to User objects.
     */
    @Override
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
    
    /**
     * Method to make sure Map<String, User> serializes and written into the database(.txt file)
     * Handles IO realated exceptions
     * @param users
     */
    private void saveUsers(Map<String, User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * removes a user from database if 
     * @param username, takes user as argument 
     * @param password, takes passwords as argument (security reasons)
     * @return false if password doesn't match
     * @return true is password matches (user deletion confirmed)
     */
    @Override
    public boolean deleteUser(User username, String password) {
      lock.writeLock().lock();
      if(!(password.equals(username.getPassword())))return false;
      try {
          Map<String, User> users = loadUsers();
          users.remove(username.getUsername());
          saveUsers(users);
          return true;
      } finally {
          lock.writeLock().unlock();
      }
    }
}
