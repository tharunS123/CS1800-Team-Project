# CS 18000 Team Project - Social Media Platform

This is a Java-based social networking application that allows users to create accounts, manage profiles, interact with friends, and create posts. The application uses a client-server architecture with socket programming for communication.

----

## Getting Started
To run the social media platform application, follow these steps:
```gitexclude
1. Clone the repository to your local machine.
git clone https://github.com/tharunS123/CS1800-Team-Project.git
```
```gitexclude
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
```
```gitexclude
3. Run the Server class to start the server application.
```
```gitexclude
4. Run the Cl ient class to start the client application.
```

---

## User Management
 
### [User](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/User.java) 
The User class represents a user in the social media platform application. It implements
the UserInterface and is serializable, allowing user objects to be easily saved and 
retrieved from a file. The class includes various attributes and methods to manage user 
information and interactions.

**Attributes**:
- userId: A unique identifier for the user 
- password: The user's password. 
- name: The user's name. 
- email: The user's email address. 
- friendList: A list of the user's friends. 
- requestList: A list of friend requests received by the user. 
- pendingList: A list of friend requests sent by the user that are pending approval. 
- userProfile: A Profile object containing additional user information.

----

## Profile Management & Friend Management

### [Profile](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Profile.java)
The Profile class represents additional user information in the social media platform 
application. It includes various attributes and methods to manage user profile details.

**Attributes**:
- bio: A brief biography of the user. 
- profilePicture: The URL or path to the user's profile picture. 
- location: The user's location. 
- website: The user's personal or professional website. 
- birthdate: The user's birthdate. 
- interests: The user's interests.

----

## Post Management

### [Post](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Post.java)
The Post class represents a post in the social media platform application. It includes various attributes and methods to manage post details

**Attributes**:
- title: The title of the post. 
- content: The content of the post. 
- author: The author of the post. 
- timestamp: The time when the post was created.

----

## Technical Architecture

### Components
- [Client](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Server/Client.java): The client application that users interact with to access the social media platform.
- [Server](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Server/Server.java): The server application that manages user accounts, profiles, posts, and interactions.
- [User](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/User.java): The user class that represents a user in the social media platform application.
- [Profile](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Profile.java): The profile class that represents additional user information in the social media platform application.
- [Post](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Post.java): The post class that represents a post in the social media platform application.

**The User class make up a file based user management system. The database stores the information in a .dat file and is able to store attributes (for example: username, password, and friend list)**

### Communication Protocol
- ServerSocket based communication using the server ObjectInputStream and FileInputStream.
```java
ServerSocket serverSocket = new ServerSocket(1112);
```
- Socket based communication between client and server.
```java
Socket socket = new Socket("localhost", 1112);
```

### [Server](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Server/Server.java)
The Server class is the backbone of the multi-client server application. 
It manages client-server communication, user authentication, and post management. 
The Server listens for incoming client connections, processes user actions, and ensures 
the safe storage and retrieval of user and post data. 

#### Server Features
- Multithreaded server handling multiple client connections
- Synchronized methods for thread-safe operations
- Dynamic user and post management

**Attributes**:
- users: A HashMap that stores user objects with their unique user IDs as keys.
- posts: An ArrayList that stores post objects created by users.
- userFile: The file path for storing user data.
- postFile: The file path for storing post data.
- serverSocket: The ServerSocket object for accepting client connections.

**Methods**:
- ``main(String[] args)``: The main method that initializes the server, user database, and post database, and listens for client connections.
- ``synchronized boolean login(String username, String password)``: Authenticates a user based on the provided username and password. Returns true if the user is successfully authenticated.
```java
@Override
public synchronized boolean login(String username, String password) {
  if (!userList.isEmpty()) {
    if(userList.containsKey(username)){
      User user = userList.get(username);
        System.out.println("User password:" + user.getPassword());
        if(user.getPassword().equals(password)){
          System.out.println(username + " logged in @ address: " + socket.getInetAddress());
          return true;
        }
      }
    }
  return false;
}
```
- ``synchronized boolean createPost(String title, String content, String userId)``: Creates a new post with the provided title, content, and author (user ID). Returns true if the post is successfully created.
```java
public synchronized boolean createPost(String title, String content, String userId){
  System.out.println("Got to createPost");
  Post post = new Post(title, content, userId);
  postList.put(postNumbering, post);
  postNumbering++;
  for (Post p : postList.values()) {
    System.out.println(p);
  }
  return true;
}
```
- ``synchronized boolean uniqueIdCheck(String userId)``: checks if the given parameter userId is unique in the database. Returns true if the userId is unique.
- ``synchronized boolean uniquePhoneNoCheck(String phoneNumber)``: checks if the given parameter phoneNumber is unique in the database. Returns true if the phoneNumber is unique.
- ``void run()``: run method
  * Start whenever a new socket is accepted
  * create a printWriter and a bufferedReader
  * Use a switch to perform different tasks required by the clients

### [Client](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Server/Client.java)
The Client class is the client-side application that interacts with the server to access the social media platform. It allows users to log in, create posts, and view posts created by friends.

**Attributes**:
- socket: The Socket object for connecting to the server.

**Methods**:
- ``main(String[] args)``: The main method that initializes the client and connects to the server.
```java
public static void main(String[] args) {
  Socket socket;
  try {
    socket = new Socket("localhost", 1112);
  } catch (IOException e) {
    JOptionPane.showMessageDialog(null,
            "Unable to connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
    return;
  }
  // Invoke Frame.LoginFrame in the Event Dispatch Thread
  SwingUtilities.invokeLater(new LoginFrame(socket));
}
```

---

## Swing GUI
Swing is a part of Java's standard library used to create Graphical User Interfaces (GUIs). It provides a set of lightweight, platform-independent components for building desktop applications

Key features of Swing:
- Customizable: Components can be easily styled and customized. 
- Event-Driven: User actions like clicks or key presses trigger events that can be handled programmatically. 
- Platform-Independent: Looks consistent across operating systems. 
- Flexible Layout Management: Offers multiple layout managers to control component placement.

**We have split which functionality is handled by the client which calls the Swing compound and which is handled by the server. 
The server handles the user authentication and post creation, while the client handles the user interface and user interactions.**

**Compounds**:
- [``LoginFrame.java``](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Frame/LoginFrame.java): The login frame that allows users to log in to the social media platform.
- [``RegisterFrame.java``](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Frame/RegisterFrame.java): The register frame that allows users to create new accounts.
- [``UserFrame.java``](https://github.com/tharunS123/CS1800-Team-Project/blob/main/src/Frame/UserFrame.java): The user frame will display the post and the friends list. 

**And anymore compounds like this.**

**ActionListener**: For login buttons, we have implemented an ActionListener interface to handle user actions.
```java
ActionListener actionListener = new ActionListener() {
  /**
     * @param e Invoked when any of the button in the frame is selected.
     *          There are two button choices: Login and Register.
     *          Register button would lead to the Frame.RegisterFrame while login button
     *          would perform the functionality by sending userId and password to the server.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == loginButton) {
        userId = userIdField.getText();
        char[] rawPassword = passwordField.getPassword();
        StringBuilder actualPassword = new StringBuilder();
        actualPassword.append(rawPassword);
        try {
          printWriter.println("Login");
          printWriter.println(userId);
          printWriter.println(actualPassword.toString());
          printWriter.flush();
          String result = bufferedReader.readLine();
          if (result.equals("Success")) {
            JOptionPane.showMessageDialog(null, "Login Successful!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(new UserFrame(socket, userId));
            loginFrame.dispose();
          } else {
            if (result.equals("Invalid")) {
              JOptionPane.showMessageDialog(null, "Invalid username/password", "Login Failure", JOptionPane.INFORMATION_MESSAGE);
              return;
            }
          }
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
      if (e.getSource() == registerButton) {
        SwingUtilities.invokeLater(new RegisterFrame(socket));
        loginFrame.dispose();
      }
    }
};
```

---

## License
This project is open-source. 


