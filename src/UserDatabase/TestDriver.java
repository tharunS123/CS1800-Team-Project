import java.util.Map;
import java.util.Scanner;
import java.io.Console;

public class TestDriver {
  public static Map<String, User> users;
    public static void main(String[] args) {
        // Initialize the UserDatabase with a file name
        UserDatabase userDatabase = new UserDatabase("user_database.txt");
        users = userDatabase.loadUsers();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Display options to the user
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Display all users");
            System.out.println("2. Add a new user");
            System.out.println("3. Delete a user");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
              int choice = scanner.nextInt();
              scanner.nextLine(); // Consume newline left-over

              switch (choice) {
                  case 1:
                      loadAndDisplayUsers(userDatabase);
                      break;
                  case 2:
                      createUserPrompt(userDatabase, scanner);
                      break;
                  case 3:
                      deleteUserPrompt(userDatabase, scanner);
                      break;
                  case 4:
                      running = false;
                      System.out.println("Exiting the program.");
                      scanner.close();
                      break;
                  default:
                      System.out.println("Invalid choice. Please try again.");
              }
          } else {
              System.out.println("Please enter a valid integer.");
              if (scanner.hasNextLine()) { // Check if there’s a next line to consume
                  scanner.nextLine(); // Consume the invalid input
              } else {
                  running = false; // End the program if no input is available
              }
          }
        }
    }

    // Method to load and display users from the database
    private static void loadAndDisplayUsers(UserDatabase userDatabase) {
        users = userDatabase.loadUsers();

        if (users.isEmpty()) {
            System.out.println("No users found in the database.");
        } else {
            System.out.println("Loaded users from the database:");
            for (User user : users.values()) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("UUID: " + user.getUuid());
                System.out.println("Friends: " + user.getFriends());
                System.out.println("------------");
            }
        }
    }

    // Method to prompt the user to create a new user
    private static void createUserPrompt(UserDatabase userDatabase, Scanner scanner) {
        Console console = System.console();

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        boolean userExists = false;

        if(users.containsKey(username)){
          System.out.println("This username exists! Logging you in");
          userExists = true;

        }

        char[] tmppassword = console.readPassword("enter your password");

        String password = new String(tmppassword);

        if(userExists){
          while(!users.get(username).logIn(password)){
            System.out.println("Wrong password! Try again");
            tmppassword = console.readPassword("enter your password");
            password = new String(tmppassword);
          }
        }
        User newUser = new User(username, password);

        // Optionally add friends
        System.out.print("Would you like to add friends? (yes/no): ");
        String addFriends = scanner.nextLine();

        if (addFriends.equalsIgnoreCase("yes")) {
            boolean addingFriends = true;
            while (addingFriends) {
                System.out.print("Enter a friend's username to add (or type 'done' to finish): ");
                String friendUsername = scanner.nextLine();
                if (friendUsername.equalsIgnoreCase("done")) {
                    addingFriends = false;
                } else {
                    newUser.addFriend(friendUsername);
                }
            }
        }

        // Add the user to the database
        if (userDatabase.addUser(newUser)) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Username already exists. User was not added.");
        }
    }

    private static void deleteUserPrompt(UserDatabase userDatabase, Scanner scanner) {
        Console console = System.console();
        System.out.print("Enter the username of the user to delete: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
          char[] tmppassword = console.readPassword("enter your password");
          String password = new String(tmppassword);

          if(userDatabase.deleteUser(users.get(username), password)){
            System.out.println("Successfully deleted the user");
          }else{
            System.out.println("Incorrect password. No user was deleted.");
          }
        } else {
            System.out.println("User not found. No user was deleted.");
        }
    }
}

