package src.newProject.Interface;

import src.newProject.Profile;

public interface ServerInterface {
    /**
     * login method
     * Check the username and password to see if the user logs in.
     *
     * @param username the username of the login user
     * @param password the password of the login user
     * @return true if the username and password are correct
     *         false if otherwise
     */
    boolean login(String username, String password);

    /**
     * getProfile method
     * Gets the Profile that matches with the given userId parameter
     *
     * @param userId the user ID to search for
     * @return Profile if found, null if not found
     */
    Profile getProfile(String userId);

    /**
     * setUserProfile method
     * Sets the userProfile up with the inserted userId
     *
     * @param userProfile the profile to set up
     * @param userId the Id to connect the profile to
     * @return true if success, false if userId is not found
     */
    boolean setUserProfile(Profile userProfile, String userId);

    /**
     * requestFriend method
     * Sends out a friend request to the receiver using userId as a guide
     *
     * @param ownId id of the requester
     * @param friendId id of the user that the requester want to request
     * @return "RequestSuccess" if the success;
     *         "Already friend!" if in each other 's friendList;
     *         "Already requested!" if requested user in requester 's requested list;
     *         "Already being requested!" if the requested user has already sent a request to requester;
     */
    String requestFriend(String ownId, String friendId);

    /**
     * deleteFriend method
     * Delete the friend in user's friendList and vice versa for the friend who got deleted.
     *
     * @param ownId the id of the deleter
     * @param friendId the id of the friend that deleter want to delete
     * @return True if the deletion is success; False if no existence
     */
    boolean deleteFriend(String ownId, String friendId);

    /**
     * uniquePhoneNoCheck method
     * Check to make sure every phone number registered is unique.
     *
     * @param phoneNumber  Phone Number of the user
     * @return True if the phone number is unique; False if it has been used.
     */
    boolean uniquePhoneNoCheck(String phoneNumber);

    /**
     * uniqueIdCheck method
     * checks if the given parameter userId is unique in the database
     *
     * @param userId the userId to check
     * @return true if userId is unique, false otherwise
     */
    boolean uniqueIdCheck(String userId);

    /**
     * acceptFriend method
     * accept the request in pending list
     * add to each other's friendList
     * delete history in pending and requested list
     *
     * @param ownId the id of the user who accept the request
     * @param friendId the id of the user who sent the request
     * @return "AcceptSuccess" if there is an request and are accepted successfully
     *         "No request found" if there are no request
     *         "No such user found" if can not find the user of either ownId or friendId
     */
    String acceptFriend(String ownId, String friendId);

    /**
     * denyFriend method
     * deny the request in pending list
     * delete history in pending and requested list
     *
     * @param ownId the id of the user who deny the request
     * @param friendId the id of the user who sent the request
     * @return "DenySuccess" if there is an request and deny successfully
     *         "No request found" if there are no request
     *         "No such user found" if can not find the user of either ownId or friendId
     */
    String denyFriend(String ownId, String friendId);

    /**
     * resendRequest method
     * check if the request has been sent
     * if not resend request, if sent, ask the user to be more patient
     *
     * @param ownId the login user
     * @param friendId the user who have been requested
     * @return "RequestExisted" if the request is in the user's pending list
     *         "ResendSuccess" if there is no request and the request is resend
     *         "No such user found" if can not find user of either ownId or friendId
     */
    String resendRequest(String ownId, String friendId);
}
