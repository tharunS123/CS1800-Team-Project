package src.PostDatabase.OldCode;

import src.PostDatabase.Post;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class TestDriverOld {
    public TestDriverOld() {
    }

    public static void main(String[] args) throws Exception {
        PostDatabaseOld postdatabase = new PostDatabaseOld("postdatabase.txt");

//        createPostPrompt(postdatabase);

//        loadAndDisplayPosts(postdatabase);

//        Post post = new Post("Test Title", "Test Content", "Author");
//        postdatabase.addPost(post);
        postdatabase.readPostDatabase("postdatabase.txt");
    }

    private static void loadAndDisplayPosts(PostDatabaseOld postdatabase) throws Exception {
        ArrayList<Post> posts = postdatabase.getPosts();

        if (posts.isEmpty()) {
            System.out.println("No posts found.");
        } else {
            System.out.println("Loaded Post from the database: ");

            // display all the post  in the database
            postdatabase.readPostDatabase("postdatabase.txt");
        }
    }

    private static void createPostPrompt(PostDatabaseOld postdatabase) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title of the post: ");
        String title = scanner.nextLine();

        System.out.println("Enter the content of the post: ");
        String content = scanner.nextLine();

        System.out.println("Enter the author of the post: ");
        String author = scanner.nextLine();

        String date = new Timestamp(System.currentTimeMillis()).toString();

        Post post = new Post(title, content, author);
        postdatabase.addPost(post);
    }
}
