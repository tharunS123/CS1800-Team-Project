package src.Server;

import src.Interface.ServerInterface;
import src.Profile;
import src.User;
import src.oldProject.PostDatabase.Post;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class representing the backend server-side of our application.
 * All the processing of data, connecting to the client, and including the file I/O happens here.
 *
 * @author Tharun Kumar Senthilkumar & Eashan & Abdullah Haris
 * @version Dec 8, 2024
 */
public class Server implements Runnable, ServerInterface {
    Socket socket;
    public static HashMap<String, User> userList;
    public static HashMap<String, Post> postList;
    public static File fileName;
    public static File postFilename;

    /**
     * The constructor of ProfileServer which uses one parameter : socket
     *
     * @param socket The socket that connect this computer connect with the server
     */
    public Server(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        //Initialize an arraylist to store all user data.
        userList = new HashMap<String, User>();
        postList = new HashMap<String, Post>();
        fileName = new File("database.dat");
        postFilename = new File("postDatabase.dat");
        /*
         * If there is no such date file, a file would be created
         * else, would lead user objectInputStream to read user object inside the file.
         */
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
                Object readObject = objectInputStream.readObject();
                while (readObject != null) {
                    User u = (User) readObject;
                    userList.put(u.getName(), u);
                    readObject = objectInputStream.readObject();
                }
                /* Catch EOF exception which indicates that the objectInputStream has reached an end.
                 * This is for special case when there is no object inside the data file but the file is created.
                 */
            } catch (EOFException eofException) {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try (ServerSocket serverSocket = new ServerSocket(1112)) {
            /*
             * Use a while loop to accept all socket receives with multithreading
             * A thread would be start whenever the a socket is accepted
             * The socket would be pass to a thread to interacting with the client.
             */
            while (true) {
                Socket socket = serverSocket.accept();
                Server server = new Server(socket);
                new Thread(server).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * login method
     * Check the username and password to see if the user logs in.
     *
     * @param username the username of the login user
     * @param password the password of the login user
     * @return true if the username and password are correct
     *         false if otherwise
     */
    @Override
    public synchronized boolean login(String username, String password) {
        if (!userList.isEmpty()) {
          if(userList.containsKey(username)){
            User user = userList.get(username);
            if(user.getPassword().equals(password)){
              System.out.println(username + " logged in @ address: " + socket.getInetAddress());
              return true;
            }
          }
        }
        return false;
    }

    /**
     * getProfile method
     * Gets the Profile that matches with the given userId parameter
     *
     * @param userId the user ID to search for
     * @return Profile if found, null if not found
     */
    @Override
    public synchronized Profile getProfile(String userId) {
        return userList.get(userId).getUserProfile();
    }

    /**
     * setUserProfile method
     * Sets the userProfile up with the inserted userId
     *
     * @param userProfile the profile to set up
     * @param userId the Id to connect the profile to
     * @return true if success, false if userId is not found
     */
    @Override
    public synchronized boolean setUserProfile(Profile userProfile, String userId) {
        User user = userList.get(userId);
        if(user == null) return false;
        user.setUserProfile(userProfile);
        return true;
    }

    /**
     * requestFriend method
     * Sends out a friend request to the receiver using userId as a guide
     *
     * @param self id of the requester
     * @param other id of the user that the requester want to request
     * @return "RequestSuccess" if the success;
     *         "Already friend!" if in each other 's friendList;
     *         "Already requested!" if requested user in requester 's requested list;
     *         "Already being requested!" if the requested user has already sent a request to requester;
     */
    @Override
    public synchronized String requestFriend(String self, String other) {
        User own = userList.get(self);
        User friend = userList.get(other);
        if (own != null && friend != null) {
            if (own.getFriendList().contains(friend) && friend.getFriendList().contains(own)) {
                return "Already friend!";
            } else if (own.getRequestList().contains(friend) && friend.getPendingList().contains(own)) {
                return "Already requested!";
            } else if (own.getPendingList().contains(friend) && friend.getRequestList().contains(own)) {
                return "Already being requested!";
            } else {
                own.getRequestList().add(friend);
                friend.getPendingList().add(own);
                return "RequestSuccess";
            }
        } else {
            return "UserNotFound";
        }
    }

    /**
     * deleteFriend method
     * Delete the friend in user's friendList and vice versa for the friend who got deleted.
     *
     * @param self the id of the deleter
     * @param other the id of the friend that deleter want to delete
     * @return True if the deletion is success; False if no existence
     */
    @Override
    public synchronized boolean deleteFriend(String self, String other) {
        User ownUser = userList.get(self);
        User friend = userList.get(other);
        if (ownUser == null || friend == null) return false;
        boolean existInOwn = ownUser.getFriendList().removeIf(user -> user.getUserId().equals(friend));
        boolean existInFriend = friend.getFriendList().removeIf(user -> user.getUserId().equals(self));
        return (existInOwn && existInFriend);
    }

    /**
     * uniquePhoneNoCheck method
     * Check to make sure every phone number registered is unique.
     *
     * @param phoneNumber  Phone Number of the user
     * @return True if the phone number is unique; False if it has been used.
     */
    @Override
    public synchronized boolean uniquePhoneNoCheck(String phoneNumber) {
        if (userList.isEmpty()) {
            return true;
        }
        boolean unique = true;
        for (User user : userList.values()) {
            if (user.getUserProfile().getPhoneNumber().equals(phoneNumber)) {
                unique = false;
                break;
            }
        }
        return unique;
    }

    /**
     * uniqueIdCheck method
     * checks if the given parameter userId is unique in the database
     *
     * @param userId the userId to check
     * @return true if userId is unique, false otherwise
     */
    @Override
    public synchronized boolean uniqueIdCheck(String userId) {
        if (userList.isEmpty()) {
            return true;
        }
        boolean unique = true;
        for (User user : userList.values()) {
            if (user.getUserId().equals(userId)) {
                unique = false;
                break;
            }
        }
        return unique;
    }

    /**
     * acceptFriend method
     * accept the request in pending list
     * add to each other's friendList
     * delete history in pending and requested list
     *
     * @param self the id of the user who accept the request
     * @param other the id of the user who sent the request
     * @return "AcceptSuccess" if there is an request and are accepted successfully
     *         "No request found" if there are no request
     *         "No such user found" if can not find the user of either self or friend
     */
    @Override
    public synchronized String acceptFriend(String self, String other) {
        User own = userList.get(self);
        User friend = userList.get(other);
        if (own != null && friend != null) {
            if (own.getPendingList().contains(friend) && friend.getRequestList().contains(own)) {
                own.getFriendList().add(friend);
                friend.getFriendList().add(own);
                own.getPendingList().remove(friend);
                friend.getRequestList().remove(own);
                return "AcceptSuccess";
            } else {
                return "No request found.";
            }
        } else {
            return "No such user found.";
        }
    }

    /**
     * denyFriend method
     * deny the request in pending list
     * delete history in pending and requested list
     *
     * @param self the id of the user who deny the request
     * @param other the id of the user who sent the request
     * @return "DenySuccess" if there is an request and deny successfully
     *         "No request found" if there are no request
     *         "No such user found" if can not find the user of either self or friend
     */
    @Override
    public synchronized String denyFriend(String self, String other) {
        User own = userList.get(self);
        User friend = userList.get(other);
        if (own != null && friend != null) {
            if (own.getPendingList().contains(friend) && friend.getRequestList().contains(own)) {
                own.getPendingList().remove(friend);
                friend.getRequestList().remove(own);
                return "DenySuccess";
            } else {
                return "No request found";
            }
        } else {
            return "No such user found.";
        }
    }

    /**
     * resendRequest method
     * check if the request has been sent
     * if not resend request, if sent, ask the user to be more patient
     *
     * @param self the login user
     * @param other the user who have been requested
     * @return "RequestExisted" if the request is in the user's pending list
     *         "ResendSuccess" if there is no request and the request is resend
     *         "No such user found" if can not find user of either self or friend
     */
    @Override
    public synchronized String resendRequest(String self, String other) {
        User own = userList.get(self);
        User friend = userList.get(other);
        if (own != null && friend != null) {
            if (friend.getPendingList().contains(own) && own.getRequestList().contains(friend)) {
                return "RequestExisted";
            } else {
                if (friend.getPendingList().contains(own)) {
                    own.getRequestList().add(friend);
                } else if (own.getRequestList().contains(friend)) {
                    friend.getPendingList().add(own);
                }
                return "ResendSuccess";
            }
        } else {
            return "No such user found.";
        }
    }

    /**
     * Loads all posts from the database.
     *
     * @return A map of posts where the key is the string representation of the post,
     * and the value is the post object.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Post> loadPosts() {
        if (fileName.length() == 0) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<String, Post>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * run method
     * Start whenever a new socket is accepted
     * create a printWriter and a bufferedReader
     * Use a switch to perform different tasks required by the clients
     */
    @Override
    public void run() {
        try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            while (true) {
                String command = bufferedReader.readLine();
                //To stop this thread when the user close the software.
                if (command == null) {
                    printWriter.close();
                    bufferedReader.close();
                    socket.close();
                    // Save the data into the data file if userList is not empty
                    if (!userList.isEmpty()) {
                        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                            for (User user : userList.values()) {
                                objectOutputStream.writeObject(user);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
                switch (command) {
                    case "Login" -> {
                        String loginUser = bufferedReader.readLine();
                        String password = bufferedReader.readLine();
                        boolean hasAccount = login(loginUser, password);
                        if (hasAccount) {
                            printWriter.println("Success");
                        } else {
                            printWriter.println("Invalid");
                        }
                        printWriter.flush();
                    }
                    case "Register" -> {
                        //The User would send the user account info in a string
                        String newUser = bufferedReader.readLine();
                        String[] splitNewUser = newUser.split(", ");
                        User tmpNewUser = new User(splitNewUser[0], splitNewUser[1], splitNewUser[2], splitNewUser[3] );
                        userList.put(splitNewUser[0], tmpNewUser);
                        printWriter.println("Success");
                        printWriter.flush();
                    }
                    case "AcceptFriend" -> {
                        String self = bufferedReader.readLine();
                        String friend = bufferedReader.readLine();
                        String result = acceptFriend(self, friend);
                        printWriter.println(result);
                        printWriter.flush();
                    }
                    case "DenyFriend" -> {
                        String self = bufferedReader.readLine();
                        String friend = bufferedReader.readLine();
                        String result = denyFriend(self, friend);
                        printWriter.println(result);
                        printWriter.flush();
                    }
                    case "RequestFriend" -> {
                        String self = bufferedReader.readLine();
                        String friend = bufferedReader.readLine();
                        String result = requestFriend(self, friend);
                        printWriter.println(result);
                        printWriter.flush();
                    }
                    case "ResendRequest" -> {
                        String self = bufferedReader.readLine();
                        String friend = bufferedReader.readLine();
                        String result = resendRequest(self, friend);
                        printWriter.println(result);
                        printWriter.flush();
                    }
                    case "DeleteFriend" -> {
                        String self = bufferedReader.readLine();
                        String friend = bufferedReader.readLine();
                        boolean success = deleteFriend(self, friend);
                        String successString = success ? "Success" : "Failure";
                        printWriter.println(successString);
                        printWriter.flush();
                    }
                    case "GetFriendList" -> {
                        String userId = bufferedReader.readLine();
                        ArrayList<User> currentFriendList = null;
                        boolean found = false;
                        User u = userList.get(userId);
                        if (u != null) {
                          currentFriendList = u.getFriendList();
                            if (!currentFriendList.isEmpty()) {
                                printWriter.println(currentFriendList.size());
                                for (User user : currentFriendList) {
                                    String print = user.getName() + '\n' + user.getUserId() + '\n' + user.getUserProfile().getAboutMe();
                                    printWriter.println(print);
                                }
                            } else {
                                printWriter.println("Empty");
                            }
                        } else {
                            printWriter.println("NotFound");
                        }
                        printWriter.flush();
                    }
                    case "EditOwnAccount" -> {
                        String userEdit = bufferedReader.readLine();
                        String[] splitUserEdit = userEdit.split(", ");
                        User targetUser = userList.get(splitUserEdit);
                        if (targetUser != null) {
                            targetUser.setPassword(splitUserEdit[1]);
                            targetUser.setName(splitUserEdit[2]);
                            targetUser.setEmail(splitUserEdit[3]);
                            printWriter.println("Success");
                        } else {
                            printWriter.println("User not found");
                        }
                        printWriter.flush();
                    }
                    case "DeleteOwnAccount" -> {
                        String userId = bufferedReader.readLine();
                        User deletedUser = userList.get(userId);
                        ArrayList<User> deletedUserFriendList = deletedUser.getFriendList();
                        if (deletedUser == null) {
                            printWriter.println("Failure");
                        } else {
                          userList.remove(deletedUser.getName());
                            for (User user : deletedUserFriendList) {
                                user.getRequestList().remove(deletedUser);
                                user.getPendingList().remove(deletedUser);
                                user.getFriendList().remove(deletedUser);
                            }
                            printWriter.println("Success");
                        }
                        printWriter.flush();
                    }
                    case "GetUserList" -> {
                        String userId = bufferedReader.readLine();
                        if (userList.size() > 1) {
                            printWriter.println(userList.size() - 1);
                            for (User user : userList.values()) {
                                if (!user.getUserId().equals(userId)) {
                                    printWriter.println(user.getName());
                                    printWriter.println(user.getUserId());
                                    printWriter.println(user.getUserProfile().getAboutMe());
                                }
                            }
                        } else {
                            printWriter.println("Empty");
                        }
                        printWriter.flush();
                    }
                    case "GetPendingList" -> {
                        String userId = bufferedReader.readLine();
                        User ownUser = userList.get(userId);
                        if (ownUser != null) {
                            if (!ownUser.getPendingList().isEmpty()) {
                                printWriter.println(ownUser.getPendingList().size());
                                for (User user : ownUser.getPendingList()) {
                                    printWriter.println(user.getName());
                                    printWriter.println(user.getUserId());
                                    printWriter.println(user.getUserProfile().getAboutMe());
                                }
                            } else {
                                printWriter.println("Empty");
                            }
                        } else {
                            printWriter.println("NotFound");
                        }
                        printWriter.flush();
                    }
                    case "GetRequestList" -> {
                        String userId = bufferedReader.readLine();
                        User ownUser = userList.get(userId);
                        if (ownUser != null) {
                            if (!ownUser.getRequestList().isEmpty()) {
                                printWriter.println(ownUser.getRequestList().size());
                                for (User user : ownUser.getRequestList()) {
                                    printWriter.println(user.getName());
                                    printWriter.println(user.getUserId());
                                    printWriter.println(user.getUserProfile().getAboutMe());
                                }
                            } else {
                                printWriter.println("Empty");
                            }
                        } else {
                            printWriter.println("NotFound");
                        }
                        printWriter.flush();
                    }
                    case "EditOwnProfile" -> {
                        String userId = bufferedReader.readLine();
                        Profile userProfile = getProfile(userId);
                        String[] splitProfile = bufferedReader.readLine().split("/ ");
                        userProfile.setPhoneNumber(splitProfile[0]);
                        userProfile.setRelationship(splitProfile[1]);
                        userProfile.setGender(splitProfile[2]);
                        userProfile.setCurrentOccupation(splitProfile[3]);
                        userProfile.setInterest(splitProfile[4]);
                        userProfile.setAboutMe(splitProfile[5]);
                        boolean success = setUserProfile(userProfile, userId);
                        if (success) {
                            printWriter.println("Success");
                        } else {
                            printWriter.println("Failure");
                        }
                        printWriter.flush();
                    }
                    case "DeleteOwnProfile" -> {
                        String userId = bufferedReader.readLine();
                        if (getProfile(userId).getPhoneNumber().equals("") && getProfile(userId).getAboutMe().equals("")
                                && getProfile(userId).getCurrentOccupation().equals("") &&
                                getProfile(userId).getInterest().equals("")) {
                            printWriter.println("No Profile");
                            printWriter.flush();
                            break;
                        }
                        boolean success = setUserProfile(
                                new Profile("", "", "", "", "",
                                        ""), userId);
                        if (success) {
                            printWriter.println("Success");
                        } else {
                            printWriter.println("Failure");
                        }
                        printWriter.flush();
                    }
                    case "UniquePhoneNoCheck" -> {
                        String phoneNo = bufferedReader.readLine();
                        boolean unique = uniquePhoneNoCheck(phoneNo);
                        if (unique) {
                            printWriter.println("Unique");
                        } else {
                            printWriter.println("Exists");
                        }
                        printWriter.flush();
                    }
                    case "UniqueIdCheck" -> {
                        String userId = bufferedReader.readLine();
                        boolean unique = uniqueIdCheck(userId);
                        if (unique) {
                            printWriter.println("Unique");
                        } else {
                            printWriter.println("Exists");
                        }
                        printWriter.flush();
                    }
                    case "GetProfileContent" -> {
                        String userId = bufferedReader.readLine();
                        Profile profile = getProfile(userId);
                        printWriter.println(profile.getPhoneNumber());
                        printWriter.println(profile.getCurrentOccupation());
                        printWriter.println(profile.getGender());
                        printWriter.println(profile.getAboutMe());
                        printWriter.println(profile.getInterest());
                        printWriter.println(profile.getRelationship());
                        printWriter.flush();
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
