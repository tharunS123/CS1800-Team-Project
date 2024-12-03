package src.newProject.Interface;

public interface RegisterFrameInterface {
    /**
     * The content check method
     * Checks the format of text in JTextField and JPasswordField for user id, password, real name, and email
     * The functionality is implemented using String.matches() method which requires a regex as its parameter.
     * The content has to fully match the regex in order to set boolean correct to true.
     *
     * @param userId The text inside userIdTextField
     * @param password The text inside passwordField
     * @param realName The text inside realNameTextField
     * @param email The text inside emailTextField
     * @return A boolean in which 1 indicates passed the test while 0 indicates failed the test
     */
    boolean contentCheck(String userId, String password, String realName, String email);
}
