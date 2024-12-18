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
 * A class representing the frame that serves as an intermediary for creation, edition, and deletion of
 * both user account and profile.
 * When the user wants to edit or delete their account, they can click "Account" button
 * and vice versa for their profile.
 *
 * @author Tharun Kumar Senthilkumar & Eashan & Abdullah Haris
 * @version Dec 8, 2024
 */
public class AccountProfileFrame extends JOptionPane implements Runnable {
    Socket socket;
    String userId;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    JFrame accountProfileFrame;
    JButton profileButton;
    JButton accountButton;
    JButton backButton;

    ActionListener actionListener = new ActionListener() {
        /**
         * @param e Invoked when any of the button in the frame is selected.
         *          There are three button choices: Back, Profile, and Account.
         *          All the buttons here will lead to the respective frames to process
         *          the required functionality.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                SwingUtilities.invokeLater(new UserFrame(socket, userId));
                accountProfileFrame.dispose();
            }
            if (e.getSource() == profileButton) {
                SwingUtilities.invokeLater(new ProfileMenuFrame(socket, userId));
                accountProfileFrame.dispose();
            }
            if (e.getSource() == accountButton) {
                SwingUtilities.invokeLater(new AccountMenuFrame(socket, userId));
                accountProfileFrame.dispose();
            }
        }
    };

    /**
     *  The constructor of Frame.AccountMenuFrame which uses two parameters : socket and userId
     *
     * @param socket The socket that connects this local machine with the server
     * @param userId The userId of the login user
     */
    public AccountProfileFrame(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    /**
     *  Sets up the appearance of the Account Profile Frame by initializing GUIs.
     *  BufferedReader and PrintWriter is created with the socket that is being transferred from other frame.
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
            return;
        }
        accountProfileFrame = new JFrame("Menu Frame");
        Container accountProfileContentPane = accountProfileFrame.getContentPane();
        accountProfileContentPane.setLayout(null);
        accountButton = new JButton("Account");
        profileButton = new JButton("Profile");
        backButton = new JButton("Back to User Frame");

        //Set component location
        accountButton.setBounds(120, 50, 150, 30);
        profileButton.setBounds(120, 100, 150, 30);
        backButton.setBounds(120, 150, 150, 30);

        //Add actionLister
        accountButton.addActionListener(actionListener);
        profileButton.addActionListener(actionListener);
        backButton.addActionListener(actionListener);

        //Add all components into the Frame;
        accountProfileContentPane.add(accountButton);
        accountProfileContentPane.add(profileButton);
        accountProfileContentPane.add(backButton);

        //Finalize the Frame
        accountProfileFrame.setSize(400, 300);
        accountProfileFrame.setLocationRelativeTo(null);
        accountProfileFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        accountProfileFrame.addWindowListener(new WindowAdapter() {
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
                    accountProfileFrame.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        accountProfileFrame.setVisible(true);
    }
}