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
                    Console console = System.console();
                    console.printf("Please enter your password: ");
                    char[] passwordChars = console.readPassword();
                    String password = new String(passwordChars);
                    choice = username+":"+password;
                    break;
                  default:
                    choice = scanner.nextLine().trim().toLowerCase();
                    if (choice.equals("exit")) {
                      System.out.println("Exiting...");
                      socket.close();
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

