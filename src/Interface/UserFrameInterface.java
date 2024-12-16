package src.Interface;

import javax.swing.table.DefaultTableModel;

public interface UserFrameInterface {
    /**
     * The method that communicates with the server and gets the updated userInfo in the login user's friendList
     * @param loginId The id of the login user
     * @return return the updated model with the content of current friendList
     */
    DefaultTableModel updateModel(String loginId);
}