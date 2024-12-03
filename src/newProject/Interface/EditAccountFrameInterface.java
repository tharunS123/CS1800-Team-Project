package src.newProject.Interface;

public interface EditAccountFrameInterface {
    /**
     * Checks inserted information to make sure it doesn't contain forbidden characters.
     *
     * @param password the password to be checked
     * @param realName the name to be checked
     * @param email the email to be checked
     * @return true if above input passes checks, false otherwise.
     */
    boolean contentCheck(String password, String realName, String email);
}
