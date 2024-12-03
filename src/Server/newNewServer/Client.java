package src.Server.newNewServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private  static final int SERVER_PORT = 4242;

    public static void main(String[] args) {
        try(Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server on port " + SERVER_PORT);

            boolean check = true;
            String username = "";

            while (check) {
                // Display main menu to the user
                System.out.println("Please choose an option:");
                System.out.println("1. Sign In");
                System.out.println("2. Sign Up");
                System.out.println("3. Exit");

                int choice = Integer.parseInt(userInput.readLine());  // Get user choice
                writer.println("MAIN_MENU:" + choice);  // Send the choice to the server

                switch (choice) {
                    case 1: //sign in
                        boolean isLoggedIn = false;
                        while (!isLoggedIn) {  // Inner loop for re-entering credentials
                            System.out.println("Enter username:");
                            username = userInput.readLine(); //Reads user input for username

                            System.out.println("Enter password:");
                            String password = userInput.readLine(); //Reads user input for password

                            // Send the SIGN_IN message to the server with a separator
                            String signInMessage = "SIGN_IN:" + username + ":" + password;
                            writer.println(signInMessage);

                            // Wait for the server's response
                            String response = reader.readLine();
                            if (response.equals("Login successful!")) {
                                System.out.println("Login successful! Redirecting to homepage...");
                                isLoggedIn = true; // Exits inner loop
                                check = false;    // Exits outer loop
                            } else {
                                System.out.println("Login failed: Incorrect username or password.");
                                System.out.println("Please try again.");
                            }
                        }
                        break; // Break out of switch case
                    case 2: // Sign Up
                        boolean isSignedUp = false;
                        while (!isSignedUp) {  // Loop for re-entering details if user creation fails
                            System.out.println("Enter new username:");
                            String newUsername = userInput.readLine(); //Reads user input for new username

                            System.out.println("Enter new password:");
                            String newPassword = userInput.readLine(); //Reads user input for new password

                            // Send the SIGN_UP message to the server with a separator
                            String signUpMessage = "SIGN_UP:" + newUsername + ":" + newPassword;
                            writer.println(signUpMessage);

                            // Wait for the server's response
                            String response = reader.readLine();
                            if (response.equals("Sign Up successful! Please sign in now.")) {
                                System.out.println(response);
                                isSignedUp = true; // exits from inner while loop
                            } else {
                                System.out.println("Sign Up failed: Username already taken.");
                                System.out.println("Please try again.");
                            }
                        }
                        break;
                    case 3: // Exit
                        System.out.println("Goodbye!");
                        return;  // Exit the application
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }

            // HOMEPAGE - MAIN INTERFACE

            boolean check2 = true;
            System.out.println("Welcome to our Platform! " + username);
            while (check2) {
                System.out.println("Please choose an option: " + username);
                System.out.println("1. Search a User");
                System.out.println("2. User Viewer");
                System.out.println("3. Message a User");
                System.out.println("4. Settings");
                System.out.println("5. Log Out");

                int choice = Integer.parseInt(userInput.readLine());

                switch (choice) {
                    case 1:
//                        searchUser(userInput, writer, reader);
                        break;
                    case 2:
//                        viewUser(userInput, writer, reader);
                        break;
                    case 3:
//                        messageUser(userInput, writer, reader);
                        break;
                    case 4:
//                        settingsUser(userInput, writer, reader);
                        break;
                    case 5:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
