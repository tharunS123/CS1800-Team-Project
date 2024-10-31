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

    public String getPassword(){
      return this.password;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public List<String> getFriends() {
        return new ArrayList<>(friends);
    }

    public Tuple<Boolean, User> logIn(String password){
      if(password.equals(this.password)){
        loggedIn = true;
        return new Tuple(true, this);
      }else {
        return new Tuple(false, null);
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

