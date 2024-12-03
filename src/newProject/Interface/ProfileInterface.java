package src.newProject.Interface;

public interface ProfileInterface {
    /**
     * gets phone number
     *
     * @return phoneNumber
     */
    String getPhoneNumber();

    /**
     * gets relationship status
     *
     * @return relationship
     */
    String getRelationship();

    /**
     * gets gender
     *
     * @return gender
     */
    String getGender();

    /**
     * gets current occupation
     *
     * @return currentOccupation
     */
    String getCurrentOccupation();

    /**
     * gets interest
     *
     * @return interest
     */
    String getInterest();

    /**
     * gets about me page
     *
     * @return aboutMe
     */
    String getAboutMe();

    /**
     * sets phone number
     *
     * @param phoneNumber new phone number to set
     */
    void setPhoneNumber(String phoneNumber);

    /**
     * sets relationship status
     *
     * @param relationship new relationship status
     */
    void setRelationship(String relationship);

    /**
     * sets gender
     *
     * @param gender new gender to set
     */
    void setGender(String gender);

    /**
     * sets new current occupation
     *
     * @param currentOccupation new occupation to set
     */
    void setCurrentOccupation(String currentOccupation);

    /**
     * sets interest
     *
     * @param interest new interests to set
     */
    void setInterest(String interest);

    /**
     * sets about me page
     *
     * @param aboutMe new about me page to set
     */
    void setAboutMe(String aboutMe);
}
