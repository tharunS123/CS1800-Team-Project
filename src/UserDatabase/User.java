import java.util.*;
import java.io.*;
class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String username;
    private final String password;
    private final String uuid;
    private Boolean loggedIn;
    private List<String> friends;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.uuid = UUID.randomUUID().toString();
        this.friends = new ArrayList<>();
        this.loggedIn = false;
    }
    
    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuid;

    }
    public boolean getLoggedIn() {
        return loggedIn;
    }

    public List<String> getFriends() {
        return new ArrayList<>(friends);
    }

    public boolean logIn(String password){
      if(password.equals(this.password)){
        loggedIn = true;
        return true;
      }else {
        return false;
      }
    }

    public void logOut(){
      loggedIn = false;
    }

    public void addFriend(String friendUsername) {
        friends.add(friendUsername);
    }

    public void removeFriend(String friendUsername) {
        friends.remove(friendUsername);
    }
}

