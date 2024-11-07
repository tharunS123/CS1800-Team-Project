package src.PostDatabase;

import src.UserDatabase.Tuple;
import src.UserDatabase.User;
import src.UserDatabase.UserDatabase;

import java.io.Console;
import java.util.Map;
import java.util.Scanner;

public class TestDriver {
    public static Map<String, Post> posts;
    public static Map<String, User> users;
    public static User currentUser = null;
    public static Post currentUserPost = null;
    public static Post previousPost = null;

    public static void main(String[] args) {
        PostDatabase postDatabase = new PostDatabase("post_database.txt");
        UserDatabase userDatabase = new UserDatabase("user_database.txt");

        posts = postDatabase.loadPosts();
        users = userDatabase.loadUsers();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            if (currentUser == null) {
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
            } else {
                System.out.println("4. Display all posts");
                System.out.println("5. Add a new post");
//                System.out.println("6. Comment on a post");
                System.out.println("6. Look up post");
                System.out.println("7. Logout");
            }

            System.out.println("Enter your choice: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1:
                        login(userDatabase, scanner);
                        break;
                    case 2:
                        createUser(userDatabase, scanner);
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting the program.");
                        break;
                    case 4:
                        loadAndDisplayPosts(postDatabase);
                        break;
                    case 5:
                        createPostPrompt(postDatabase, userDatabase, scanner);
                        break;
                    case 6:
                        // Comment on a post
//                        addCommentPrompt(postDatabase, userDatabase, scanner);
                        // Look up post
                        lookupPostPrompt(postDatabase, userDatabase, scanner);
//                        currentUserPost = postDatabase.lookUpPost("test title");
                        break;
                    case 7:
                        logout();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                if (scanner.hasNextInt()) {
                    scanner.nextInt(); // Consume invalid input
                } else {
                    running = false;
                }
            }
        }
    }

    private static void loadAndDisplayPosts(PostDatabase postDatabase) {
        posts = postDatabase.loadPosts();

        if (posts.isEmpty()) {
            System.out.println("No posts to display.");
        } else {
            for (Post post : posts.values()) {
                System.out.println(post);
            }
        }
    }

    private static void createUser(UserDatabase userDatabase, Scanner scanner) {
        Console console = System.console();

        System.out.println("Enter a username: ");
        String username = scanner.nextLine();

        boolean userExists = false;

        if (users.containsKey(username)) {
            System.out.println("User already exists.");
            userExists = true;
            return;
        }

//        char[] tmpPassword = console.readPassword("Enter your password: ");
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        if (userExists) {
            while (!users.get(username).logIn(password).x) {
                System.out.println("Wrong password! try again");
//                tmpPassword = console.readPassword("Enter your password: ");
                System.out.println("Enter your password: ");
                password = scanner.nextLine();
            }
        }
        User newUser = new User(username, password);
        if (userDatabase.addUser(newUser)) {
            System.out.println("User created successfully. " + newUser.getUsername());
        } else {
            System.out.println("Username already exists. User was not added.");
        }
    }

    private static void createPostPrompt(PostDatabase postDatabase, UserDatabase userDatabase, Scanner scanner) {

        // comment this because you know that the user can only post if they are logged in.
//        login(userDatabase, scanner);

        if (currentUser == null) {
            System.out.println("You must be logged in to create a post.");
            return;
        }
        System.out.println("Enter the title of the post: ");
        String title = scanner.nextLine();
        System.out.println("Enter the content of the post: ");
        String content = scanner.nextLine();

        Post newPost = new Post(title, content, currentUser.getUsername());
        if (postDatabase.addPost(newPost)) {
            System.out.println("Post added successfully.");
        } else {
            System.out.println("Post was not added.");
        }
    }

    private static void login(UserDatabase userDatabase, Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("User not found.");
            return;
        }

//        char[] tmpPassword = System.console().readPassword("Enter your password: ");
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        Tuple<Boolean, User> loggedInTuple = users.get(username).logIn(password);
        boolean loggingIn = true;
        while (loggingIn) {
            if (!loggedInTuple.x) {
                System.out.println("Not correct password. no Friends were added");
            } else {
                currentUser = loggedInTuple.y;
                System.out.println("Successfully logged in as " + currentUser.getUsername());
                return;
            }
        }
    }

    private static void logout() {
        if (currentUser == null) {
            System.out.println("You are not logged in!");
        } else {
            currentUser.logOut();
            currentUser = null;
            System.out.println("Successfully logged out.");
        }
    }

    private static void addCommentPrompt(PostDatabase postDatabase, UserDatabase userDatabase, Scanner scanner) {
        if (currentUser == null) {
            System.out.println("You're not logged in! Log in first.");
            return;
        }
        boolean addingComment = true;
        while (addingComment) {
            System.out.println("Enter your comment or (type 'done' to finish): ");
            String comment = scanner.nextLine();
            if (comment.equalsIgnoreCase("done")) {
                addingComment = false;
            } else {
                currentUserPost.addComment(new Comment(currentUser.getUsername(), comment));
                boolean update = postDatabase.updatePost(currentUserPost);
                System.out.println(update);
            }
        }
    }

    public static void lookupPostPrompt(PostDatabase postDatabase, UserDatabase userDatabase, Scanner scanner) {
        if (currentUser == null) {
            System.out.println("You're not logged in! Log in first.");
            return;
        }
        System.out.println("Enter the title of the post that you looking for: ");
        String title = scanner.nextLine();

        currentUserPost= postDatabase.lookUpPost(title);

        if (currentUserPost == null) {
            System.out.println("Post not found.");
            return;
        }

        System.out.println("The you are looking for is there. Choise what task you want todo: ");
        System.out.println("1. Add a comment");
        System.out.println("2. Upvote");
        System.out.println("3. Downvote");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addCommentPrompt(postDatabase, userDatabase, scanner);
                break;
            case 2:
                currentUserPost.upVote();
                previousPost = currentUserPost;
                postDatabase.deletePost(previousPost);
                postDatabase.updatePost(currentUserPost);
                break;
            case 3:
                currentUserPost.downVote();
                previousPost = currentUserPost;
                postDatabase.deletePost(previousPost);
                postDatabase.updatePost(currentUserPost);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

        System.out.println("The post is: " + currentUserPost.toString());
    }
}
