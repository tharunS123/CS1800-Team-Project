package src.UserDatabase;

import java.util.Map;
import java.util.Scanner;
import java.io.Console;

/**
 * The TestDriver class provides an interface to interact with a user database.
 * It allows users to create accounts, log in, delete accounts, add friends, and display
 * user information. This class depends on the UserDatabase and User classes for database
 * operations and user management.
 * @author Eashan and Abdullah
 * @version 31st October
 */
public class TestDriver {
	
	/** Map of usernames to User objects representing all users in the system. */
    public static Map<String, User> users;
	public static User currentUser = null;
	/**
	 * Main method. Initializes the user database, loads users, and provides an
     * interface for user interaction.
	 * @param args
	 */
	
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
            System.out.println("4. Add a friend"); 
            System.out.println("5. Log In");
            System.out.println("6. Log Out");
            System.out.println("7. Exit");

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
                      addFriendPrompt(userDatabase, scanner);
                      break;
                  case 5:
                      logIn(userDatabase, scanner);
                      break;
                  case 6:
                      logOut();
                      break;
                  case 7:
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
    
    /**
     * Loads and displays all users in the user database.
     *
     * @param userDatabase The UserDatabase instance to load users from.
     */
    private static void loadAndDisplayUsers(UserDatabase userDatabase) {
        users = userDatabase.loadUsers();

        if (users.isEmpty()) {
            System.out.println("No users found in the database.");
        } else {
            System.out.println("Loaded users from the database:");
            System.out.println("------------");
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
          System.out.println("This username exists!");
          userExists = true;
          return;
        }

        char[] tmppassword = console.readPassword("enter your password");

        String password = new String(tmppassword);

        if(userExists){
          while(!users.get(username).logIn(password).x){
            System.out.println("Wrong password! Try again");
            tmppassword = console.readPassword("enter your password");
            password = new String(tmppassword);
          }
        }
        User newUser = new User(username, password);
        // Add the user to the database
        if (userDatabase.addUser(newUser)) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Username already exists. User was not added.");
        }
    }

    /**
     * Prompts the user to delete an existing user from the database.
     *
     * @param userDatabase The UserDatabase instance to delete the user from.
     * @param scanner The scanner used to read input from the user.
     */
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

    /**
     * Prompts the user to log in by providing a username and password.
     *
     * @param userDatabase The UserDatabase instance to validate user credentials.
     * @param scanner The scanner used to read input from the user.
     */
    private static void logIn(UserDatabase userDatabase, Scanner scanner){
      System.out.println("Enter your username: ");
      String username = scanner.nextLine();
      Console console = System.console();

      if (!users.containsKey(username)) {
        System.out.println("User not found.");
        return;
      } 
      char[] tmppassword = console.readPassword("Enter your password: ");
      String password = new String(tmppassword);
      Tuple<Boolean, User> loggedInTuple = users.get(username).logIn(password);
      boolean loggingIn = true;
      while(loggingIn){
        if(!loggedInTuple.x){
          System.out.print("Not correct password. No Friends were added");
        }else{
          currentUser = loggedInTuple.y;
          System.out.println("Successfully logged in! ");
          return;
        }
      }
    }

    /**
     * Logs out the currently logged-in user, if any.
     */
    private static void logOut(){
      if(currentUser == null){
        System.out.println("You are not logged in!");
      }else{
        currentUser.logOut();
        currentUser = null;
        System.out.println("Successfully logged out.");
      }
    }

    /**
     * Prompts the logged-in user to add friends by entering their usernames.
     *
     * @param userDatabase The UserDatabase instance to update friend lists.
     * @param scanner The scanner used to read input from the user.
     */
    private static void addFriendPrompt(UserDatabase userDatabase, Scanner scanner){
      if(currentUser == null){
        System.out.println("You're not logged in ! Log in first. ");
        return;
      }
      boolean addingFriends = true;
      while (addingFriends) {
          System.out.print("Enter a friend's username to add (or type 'done' to finish): ");
          String friendUsername = scanner.nextLine();
          if (friendUsername.equalsIgnoreCase("done")) {
              addingFriends = false;
          } else {
              if((users.containsKey(friendUsername))){
                currentUser.addFriend(friendUsername);
                System.out.println("Friend added!");
                userDatabase.updateUser(currentUser);
              } else{
                System.out.println("That user doesn't exist!");
              }
          }
      }
    }
}
