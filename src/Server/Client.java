package src.Server;

import src.Frame.LoginFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

/**
 * A class representing the client-side of our application.
 * This class includes the main method and invokes the Login frame of our application when begin.
 * The process should be loginFrame -> (<-Register) userFrame -> accountProfileFrame OR addFriendFrame
 * A socket is created and pass as parameters in all other frames to maintain connection with the server
 *
 * @author Tharun Kumar Senthilkumar & Eashan & Abdullah Haris
 * @version Dec 8, 2024
 */
public class Client {

    /**
     * The main method of our program.
     * Initializes the hostname with "localhost" and port number with 1112.
     * Starts by invoking a Frame.LoginFrame in the Event Dispatch Thread.
     */
    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("localhost", 1112);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Invoke Frame.LoginFrame in the Event Dispatch Thread
        SwingUtilities.invokeLater(new LoginFrame(socket));
    }
}