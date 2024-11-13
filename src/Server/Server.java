package src.Server;

import src.PostDatabase.Post;
import src.PostDatabase.PostDatabase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Server {
    private static final int POST = 4242;
    private static final List<String[]> POST_DATABASE = new ArrayList<>();

    public static void main(String[] args) {
        PostDatabase postDatabase = new PostDatabase("post_database.txt");
        postDatabase.loadPosts();

        System.out.println("Server Started, waiting for connections....");

        try (ServerSocket serverSocket = new ServerSocket(POST)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected");
                    handleClient(clientSocket, postDatabase);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket, PostDatabase postDatabase) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
//            String searchText = (String) in.readObject();
            Map<String, Post> posts = postDatabase.loadPosts();
            out.writeObject(posts);

            Integer index = (Integer) in.readObject();
            if (index != null && index >= 0 && index < posts.size()) {
                out.writeObject(POST_DATABASE.get(index)[2]);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
