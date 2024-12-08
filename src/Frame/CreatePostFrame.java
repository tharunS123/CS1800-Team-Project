package src.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CreatePostFrame extends JOptionPane implements Runnable {
    Socket socket;
    String userID;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    JFrame createPostFrame;
    JLabel postTitleLabel;
    JTextField postTitleTextField;
    JLabel postContentLabel;
    JTextArea postContentTextArea;
    JButton backButton;
    JButton createPostButton;

    final int fieldWidth = 150;

    ActionListener actionListener = new ActionListener() {
        /**
         *@param e Invoked when any of the button in the frame is selected.
         *         There are two button choices: Back and Edit Account.
         *         Back button will lead to the Frame.AccountMenuFrame while Edit Account button
         *         would perform the functionality by sending the userID, password, user name, and
         *         user email to the server.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
          if (e.getSource() == backButton) {
              System.out.println("Back button clicked"); // Add this for debugging
          }
          if (e.getSource() == createPostButton) {
              System.out.println("Create post button clicked"); // Add this for debugging
          }
            if (e.getSource() == backButton) {
                SwingUtilities.invokeLater(new UserFrame(socket, userID));
                createPostFrame.dispose();
            }
            if (e.getSource() == createPostButton) {
                String postTitle = postTitleTextField.getText();
                String postContent = postContentTextArea.getText();
                if (postTitle.isEmpty() || postContent.isEmpty()) {
                    JOptionPane.showMessageDialog(createPostFrame, "Please fill in all fields.");
                    return;
                }
               printWriter.println("CreatePost");
               // printWriter.println("%s/ %s/", postTitle, postContent);
               printWriter.println(postTitle);
               printWriter.println(postContent);
               printWriter.println(userID);
               printWriter.flush();
               String success = "";
               try {
                   success = bufferedReader.readLine();
               } catch (IOException ioException) {
                   ioException.printStackTrace();
               }
               if (success.equals("Success")) {
                   JOptionPane.showMessageDialog(null, "Congratulations!\n" +
                                   "You have successfully created your post!",
                           "Create Post Successful", JOptionPane.INFORMATION_MESSAGE);
                   SwingUtilities.invokeLater(new UserFrame(socket, userID));
                   createPostFrame.dispose();
               } else {
                   JOptionPane.showMessageDialog(null, "Oops!" +
                                   "Unsuccessful creation.\nPlease retry.",
                           "Create Post Error", JOptionPane.ERROR_MESSAGE);
               }
            }
        }
    };

    /**
     * The constructor of Frame.AccountMenuFrame which uses two parameters : socket and userId
     *
     * @param socket The socket that connects this local machine with the server
     * @param userID The userId of the login user
     */
    public CreatePostFrame(Socket socket, String userID) {
        this.socket = socket;
        this.userID = userID;
    }

    /**
     * Sets up the appearance of the Account Profile Frame by initializing GUIs.
     * BufferedReader and PrintWriter is created with the socket that is being transferred from other frame.
     */
    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to initialize in the frame", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        createPostFrame = new JFrame("Create Post Frame");
        Container createPostFrameContentPane = createPostFrame.getContentPane();
        createPostFrameContentPane.setLayout(null);

        // initialize components
        postTitleLabel = new JLabel("Post Title:");
        postTitleTextField = new JTextField();
        postContentLabel = new JLabel("Post Content:");
        postContentTextArea = new JTextArea();
        backButton = new JButton("Back to Menu");
        createPostButton = new JButton("Create Post");

        // set component location
        postTitleLabel.setBounds(110, 20, fieldWidth, 30);
        postTitleTextField.setBounds(200, 20, 200, 30);
        postContentLabel.setBounds(110, 60, fieldWidth, 30);
        postContentTextArea.setBounds(200, 60, 220, 200);
        backButton.setBounds(200, 280, 150, 30);
        createPostButton.setBounds(200, 320, 150, 30);

        // add actionListener
        backButton.addActionListener(actionListener);
        createPostButton.addActionListener(actionListener);

        // add all components into the Frame;
        createPostFrameContentPane.add(postTitleLabel);
        createPostFrameContentPane.add(postTitleTextField);
        createPostFrameContentPane.add(postContentLabel);
        createPostFrameContentPane.add(postContentTextArea);
        createPostFrameContentPane.add(backButton);
        createPostFrameContentPane.add(createPostButton);

        // finalize the frame
        createPostFrame.setSize(500, 500);
        createPostFrame.setLocationRelativeTo(null);
        createPostFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        createPostFrame.addWindowListener(new WindowAdapter() {
            /**
             * @param e Invoked when a window is in the process of being closed.
             */
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    bufferedReader.close();
                    printWriter.close();
                    socket.close();
                    createPostFrame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        createPostFrame.setVisible(true);
    }
}
