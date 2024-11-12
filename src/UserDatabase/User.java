package src.UserDatabase;
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
public class User implements Serializable {
    
	/**
	 * serialVersionUID is a unique indentifier for each class version compatibility during serialization
	 * Related final(so data remain constant and unchanged) instance variable declared (UserName, password and UUID)
	 */
	private static final long serialVersionUID = 1L;
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
        this.friends = new ArrayList<>();
        this.loggedIn = false;
    }

    /**
     * getter methods
     * Method to return UserName
     * Method to return UUID
     * Method to return password
     */
    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPassword(){
      return this.password;
    }

    /**
     * Method getLoggedIn()
     * @return true if user is logged in
     * @return fale is user if user is not able to login
     */
    public boolean getLoggedIn() {
        return loggedIn;
    }
    
    /**
     * Method to return getFriends()
     * 
     * @return  Friends list
     */
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
    public void logOut(){
      loggedIn = false;
    }

    /**
     * Method to add freinds
     * @param friendUsername
     */
    public void addFriend(String friendUsername) {
        friends.add(friendUsername);
    }

    /**
     * Method to remove friends
     * @param friendUsername
     */
    public void removeFriend(String friendUsername) {
        friends.remove(friendUsername);
    }

    /**
     * Method to Block a user
     * @param friendUsername
     */
    public void blockUser(User userName){
      blockedUsers.add(userName);
    }

    /**
     * Method to Block a user
     * @param friendUsername
     * @return Wether or not userwas removed
     */
    public boolean unblockUser(User username){
      return blockedUsers.remove(username);
    }
}
