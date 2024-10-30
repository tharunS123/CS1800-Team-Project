package src;

import src.PostDatabase.Post;
import src.PostDatabase.PostDatabase;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PostDatabase postdatabase = new PostDatabase("postdatabase20.txt");

        createPostPrompt(postdatabase);

        loadAndDisplayPosts(postdatabase);
    }

    private static void loadAndDisplayPosts(PostDatabase postdatabase) {
        ArrayList<Post> posts = postdatabase.getPosts();

        if (posts.isEmpty()) {
            System.out.println("No posts found.");
        } else {
            System.out.println("Loaded Post from the database: ");
            for (Post post : posts) {
                System.out.println(post);
            }
        }
    }

    private static void createPostPrompt(PostDatabase postdatabase) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title of the post: ");
        String title = scanner.nextLine();

        System.out.println("Enter the content of the post: ");
        String content = scanner.nextLine();

        System.out.println("Enter the author of the post: ");
        String author = scanner.nextLine();

        System.out.println("Enter the date of the post: ");
        String date = scanner.nextLine();

        Post post = new Post(title, content, author, date);
        postdatabase.addPost(post);
    }
}
