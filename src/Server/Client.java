package src.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * The Client class is responsible for establishing communication with the server and
 * providing an interactive interface for users. It sends user commands and data to the server
 * and displays server responses.
 *
 * @author Tharun Kumar Senthilkumar & Eashen
 **/
public class Client {
    /**
     * The entry point for the Client application. Establishes a connection with the server
     * and handles user interactions.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String choice;
            boolean running = true;
            System.out.println(input.readLine());
            String userChoice = scanner.nextLine().trim().toLowerCase();
            output.println(userChoice);
            while (running) {
                String serverResponse = input.readLine();
                System.out.println(serverResponse);
                switch (serverResponse) {
                    case "Enter your username and password":
                        System.out.println("Please enter your username:");
                        String username = scanner.nextLine().trim().toLowerCase();
                        System.out.println("Please enter your password:");
                        String password = scanner.nextLine().trim().toLowerCase();
                        choice = username+":"+password;
                        break;
                    case "Enter your post content":
                        System.out.println("Please enter your post title: ");
                        String title = scanner.nextLine().trim().toLowerCase();
                        System.out.println("Please enter your post content: ");
                        String content = scanner.nextLine().trim().toLowerCase();
                        choice = title + ":" + content;
                    default:
                        choice = scanner.nextLine().trim().toLowerCase();
                        if (choice.equals("exit")) {
                            System.out.println("Exiting...");
                            running = false;
                            break;
                        }
                        break;
                }
                output.println(choice);
                String serverOut = input.readLine();
                System.out.println(serverOut);
                if(serverOut.equals("Choose an option: 'login', 'signup', or 'exit'")){
                    System.out.println(input.readLine());
                }
            }

            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
