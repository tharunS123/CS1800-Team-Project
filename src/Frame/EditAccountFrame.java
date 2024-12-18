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

/**
 * A class representing the frame to edit the user account details.
 * Users can change their name, email address, and password except the user ID.
 * All the changed account details must conform to the respective validation rules.
 *
 * @author Tharun Kumar Senthilkumar & Eashan & Abdullah Haris
 * @version Dec 8, 2024
 */
public class EditAccountFrame extends JOptionPane implements Runnable {
    Socket socket;
    String userId;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    JLabel userIdLabel;
    JLabel userIdTextField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JLabel realNameLabel;
    JTextField realNameTextField;
    JLabel emailLabel;
    JTextField emailTextField;
    JButton editAccountButton;
    JButton backButton;
    JFrame editAccountFrame;

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
                SwingUtilities.invokeLater(new AccountMenuFrame(socket, userId));
                editAccountFrame.dispose();
            }
            if (e.getSource() == editAccountButton) {
                StringBuilder rawPassword = new StringBuilder();
                rawPassword.append(passwordField.getPassword());
                String realName = realNameTextField.getText();
                String email = emailTextField.getText();
                if (!contentCheck(rawPassword.toString(), realName, email)) {
                    return;
                }
                //Pass the data to server
                printWriter.println("EditOwnAccount");
                printWriter.printf("%s, %s, %s, %s\n", userId, rawPassword.toString(), realName, email);
                printWriter.flush();
                String success = "";
                try {
                    success = bufferedReader.readLine();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if (success.equals("Success")) {
                    JOptionPane.showMessageDialog(null, "Congratulation!\n" +
                                    "You have successfully edited your account details.",
                            "Edit Account Successful", JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(new LoginFrame(socket));
                    editAccountFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Oops! " +
                                    "Unsuccessful attempt.\nPlease retry.",
                            "Edit Account Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    /**
     *  The constructor of Frame.UserFrame which uses two parameters : socket and userId
     *
     * @param socket The socket that connects this local machine with the server
     * @param userId The userId of the login user
     */
    public EditAccountFrame(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    /**
     *  Sets up the appearance of the Edit Account Frame by initializing GUIs.
     *  BufferedReader and PrintWriter is created with the socket that is being transferred from other frame.
     */
    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to initialize in Edit Account frame", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        editAccountFrame = new JFrame("Edit Account Frame");
        Container editAccountFrameContentPane = editAccountFrame.getContentPane();
        editAccountFrameContentPane.setLayout(null);

        //Initialize components
        userIdLabel = new JLabel("User ID");
        userIdTextField = new JLabel(userId);
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        realNameLabel = new JLabel("Real Name");
        realNameTextField = new JTextField();
        emailLabel = new JLabel("Email");
        emailTextField = new JTextField();
        editAccountButton = new JButton("Edit");
        backButton = new JButton("Back to Menu");

        //Set component location
        userIdLabel.setBounds(110, 20, fieldWidth, 30);
        userIdTextField.setBounds(200, 20, fieldWidth, 30);
        passwordLabel.setBounds(110, 60, fieldWidth, 30);
        passwordField.setBounds(200, 60, fieldWidth, 30);
        realNameLabel.setBounds(110, 100, fieldWidth, 30);
        realNameTextField.setBounds(200, 100, fieldWidth, 30);
        emailLabel.setBounds(110, 140, fieldWidth, 30);
        emailTextField.setBounds(200, 140, fieldWidth, 30);
        editAccountButton.setBounds(140, 190, fieldWidth, 30);
        backButton.setBounds(140, 225, fieldWidth, 30);

        //Add actionLister
        editAccountButton.addActionListener(actionListener);
        backButton.addActionListener(actionListener);

        //Add all components into the Frame;
        editAccountFrameContentPane.add(userIdLabel);
        editAccountFrameContentPane.add(userIdTextField);
        editAccountFrameContentPane.add(passwordLabel);
        editAccountFrameContentPane.add(passwordField);
        editAccountFrameContentPane.add(realNameLabel);
        editAccountFrameContentPane.add(realNameTextField);
        editAccountFrameContentPane.add(emailLabel);
        editAccountFrameContentPane.add(emailTextField);
        editAccountFrameContentPane.add(editAccountButton);
        editAccountFrameContentPane.add(backButton);

        //Finalize the Frame
        editAccountFrame.setSize(400, 300);
        editAccountFrame.setLocationRelativeTo(null);
        editAccountFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        editAccountFrame.addWindowListener(new WindowAdapter() {
            /**
             * @param e Invoked when a window is in the process of being closed.
             *          The close operation can be overridden at this point.
             */
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    bufferedReader.close();
                    printWriter.close();
                    socket.close();
                    editAccountFrame.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        editAccountFrame.setVisible(true);
    }
    
    /**
     * Checks inserted information to make sure it doesn't contain forbidden characters.
     *
     * @param password the password to be checked
     * @param realName the name to be checked
     * @param email the email to be checked
     * @return true if above input passes checks, false otherwise.
     */
    public boolean contentCheck(String password, String realName, String email) {
        boolean correct = true;

        /*
        Regex used for password validation:
        ^                 # start-of-string
        (?=.*[0-9])       # a digit must occur at least once
        (?=.*[a-z])       # a lower case letter must occur at least once
        (?=.*[A-Z])       # an upper case letter must occur at least once
        (?=.*[@#$%^&+=])  # a special character must occur at least once
        (?=\S+$)          # no whitespace allowed in the entire string
        .{8,}             # anything, at least eight places though
        $                 # end-of-string
         */
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$!?%^&+=])(?=\\S+$).{8,}$")) {
            JOptionPane.showMessageDialog(null, "Password must have a length"
                            + " greater than 8 and contain\nat least one uppercase, one lower case, one digit" +
                            " and one special character.",
                    "Password Error", JOptionPane.WARNING_MESSAGE);
            correct = false;
        }
        if (!realName.matches("[A-Za-z]+?[\\-]+?[A-Za-z]+ ?[A-Za-z]+") &&
                !realName.matches("[A-Za-z]+ [A-Za-z]+ ?[A-Za-z]+")) {
            JOptionPane.showMessageDialog(null, "Real Name must have a first"
                            + " name and a last name.\nA space needs to appear between the first name and next"
                            + " name.\nMiddle name can be included.",
                    "Real Name Error", JOptionPane.WARNING_MESSAGE);
            correct = false;
        }
        if (!email.matches("\\w+@\\w+.\\w+")) {
            JOptionPane.showMessageDialog(null, "The email"
                            + " must have one '@' sign and one '.', no other special sign allowed.",
                    "Email", JOptionPane.WARNING_MESSAGE);
            correct = false;
        }
        return correct;
    }
}
