package src.UserDatabase;
import src.UserDatabase.Interface.UserInterface;

import java.util.*;
import java.io.*;

/**
 * User class, manages attributes like user, UserName, password ,and friends
 * Serializable to convert data to byte stream (allow saving/loading user data to/from a file.)
 */

/**
 * @author Eashan and Abdullah
 * @version 31st October 2024
 * Importing necessary packages
 */
public class User implements UserInterface {
    
	/**
	 * serialVersionUID is a unique indentifier for each class version compatibility during serialization
	 * Related final(so data remain constant and unchanged) instance variable declared (UserName, password and UUID)
	 */
    private final String username;
    private final String password;
    private final String uuid;
    private Boolean loggedIn;
    private Set<String> friends;
    private Set<User> blockedUsers;

    /**
     * Constructor (initializes User with given UserName and password)
     * Random generated UUID
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.uuid = UUID.randomUUID().toString();
        this.friends = new Set<String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        this.loggedIn = false;
    }

    /**
     * getter methods
     * Method to return UserName
     * Method to return UUID
     * Method to return password
     */
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getPassword(){
      return this.password;
    }

    /**
     * Method getLoggedIn()
     * @return true if user is logged in
     * @return fale is user if user is not able to login
     */
    @Override
    public boolean getLoggedIn() {
        return loggedIn;
    }
    
    /**
     * Method to return getFriends()
     * 
     * @return  Friends list
     */
    @Override
    public List<String> getFriends() {
        return new ArrayList<>(friends);
    }
    
    /**
     * LogIn method, attempts to log in the user by checking the provided password.
     * 
     * @param password
     * @return true if password matches
     * @return false if password doesn't matches
     */
    @Override
    public Tuple<Boolean, User> logIn(String password){
      if(password.equals(this.password)){
        loggedIn = true;
        return new Tuple<Boolean,User>(true, this);
      }else {
        return new Tuple<Boolean,User>(false, null);
      }
    }

    /**
     * log out method
     */
    @Override
    public void logOut(){
      loggedIn = false;
    }

    /**
     * Method to add freinds
     * @param friendUsername
     */
    @Override
    public void addFriend(String friendUsername) {
        friends.add(friendUsername);
    }

    /**
     * Method to remove friends
     * @param friendUsername
     */
    @Override
    public void removeFriend(String friendUsername) {
        friends.remove(friendUsername);
    }

    /**
     * Method to Block a user
     * @param 'friendUsername'
     */
    public void blockUser(User userName){
      blockedUsers.add(userName);
    }

    /**
     * Method to Block a user
     * @param 'friendUsername'
     * @return Wether or not userwas removed
     */
    public boolean unblockUser(User username){
      return blockedUsers.remove(username);
    }
}
