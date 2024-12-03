package src.Interface;

public interface EditProfileFrameInterface {
    /**
     * Checks inserted information to make sure it doesn't contain
     * forbidden characters and it isn't empty.
     *
     * @param userPhoneNo the phone number the user enters
     * @param currentOccupation the job that the user enters
     * @param aboutMe the aboutMe page that user enters
     * @param interest the interests page that user enters
     * @param gender the gender that user selects
     * @param relationship the relationship status that user selects
     * @return true if checks passes, false otherwise.
     */
    boolean contentCheck(String userPhoneNo, String currentOccupation,
                         String aboutMe, String interest,
                         String gender, String relationship);
}
