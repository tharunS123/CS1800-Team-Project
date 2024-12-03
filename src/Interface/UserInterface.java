package src.Interface;

import src.Profile;
import src.User;

import java.util.ArrayList;

public interface UserInterface {
    /**
     * gets request list
     *
     * @return requestList
     */
    ArrayList<User> getRequestList();

    /**
     * sets new request list
     *
     * @param requestList new request list to set as
     */
    void setRequestList(ArrayList<User> requestList);

    /**
     * gets pending list
     *
     * @return pendingList
     */
    ArrayList<User> getPendingList();

    /**
     * sets new pending list
     *
     * @param pendingList new pendingList to set
     */
    void setPendingList(ArrayList<User> pendingList);

    /**
     * gets User ID
     *
     * @return userId
     */
    String getUserId();

    /**
     * gets password
     *
     * @return password
     */
    String getPassword();

    /**
     * gets name
     *
     * @return name
     */
    String getName();

    /**
     * gets email
     *
     * @return email
     */
    String getEmail();

    /**
     * gets friend list
     *
     * @return friendList
     */
    ArrayList<User> getFriendList();

    /**
     * gets user profile
     *
     * @return userProfile
     */
    Profile getUserProfile();

    /**
     * sets new userId
     *
     * @param userId new UserId to set
     */
    void setUserId(String userId);

    /**
     * sets new password
     *
     * @param password new password to set
     */
    void setPassword(String password);

    /**
     * sets new name
     *
     * @param name new name to set
     */
    void setName(String name);

    /**
     * sets new email
     *
     * @param email new email to set
     */
    void setEmail(String email);

    /**
     * sets new friend list
     *
     * @param friendList new friend list to set
     */
    void setFriendList(ArrayList<User> friendList);

    /**
     * sets new user profile
     *
     * @param userProfile new user profile to set
     */
    void setUserProfile(Profile userProfile);
}
