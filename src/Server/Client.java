package src.Server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String choice;
            boolean running = true;

            // Read initial message from server
            System.out.println(input.readLine());
            String userChoice = scanner.nextLine().trim().toLowerCase();
            output.println(userChoice);

            while (running) {
                String serverResponse = input.readLine();
                if (serverResponse == null) {
                    System.out.println("Server closed the connection.");
                    break;
                }
                System.out.println(serverResponse);
                
                switch (serverResponse) {
                    case "Enter your username and password":
                        System.out.print("Please enter your username: ");
                        String username = scanner.nextLine().trim();
                        System.out.print("Please enter your password: ");
                        String password = scanner.nextLine().trim();
                        choice = username + ":" + password;
                        break;
                    case "Login successful.":
                        // You can handle post-login actions here if needed
                        choice = "";
                        break;
                    case "Choose an option: 'login', 'signup', or 'exit'":
                        // No additional action needed; loop will prompt for input
                        choice = "";
                        break;
                    case "Goodbye!":
                        running = false;
                        choice = "";
                        break;
                    default:
                        // For any other server messages, prompt the user for input
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextLine().trim().toLowerCase();
                        if (choice.equals("exit")) {
                            System.out.println("Exiting...");
                            socket.close();
                            running = false;
                            break;
                        }
                        break;
                }

                if (!choice.isEmpty()) {
                    output.println(choice);
                }
            }

            scanner.close();
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

