package src.newProject.Interface;

import javax.swing.table.DefaultTableModel;

public interface AddFriendFrameInterface {
    /**
     * updateAllUserModel method
     * Communicate with the server to get most update user list.
     * @return A DefaultTableModel with the updated data as its column.
     */
    DefaultTableModel updateAllUserModel();

    /**
     * updatePendingModel method
     * Communicate with the server to get the updated user info in the pending list
     * @return A DefaultTableModel with the updated data as its column.
     */
    DefaultTableModel updatePendingModel();

    /**
     * updateRequestModel method
     * Communicate with the server to get the updated user info in the request list
     * @return A DefaultTableModel with the updated data as its column.
     */
    DefaultTableModel updateRequestModel();

    /**
     * updateAll method
     * Update the date by changing the model of all three tables
     * reset the rowSorter to make sure the search bar keep working
     * Then repaint the JFrame addFriendFrame
     */
    void updateAll();
}
