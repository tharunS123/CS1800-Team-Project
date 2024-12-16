# CS 18000 Team Project - Social Media Platform

------

# Phase 1

------

### UserDatabase

#### The UserDatabase and User classes together make up a file based user management system. The database stores the information in a .txt file and is able to store attributes (for example: username, password, and friend list)
 
UserDatabase handles database operations such as 
- Data retrieval 
- Manage User Data Storage
- It uses a ReentrantReadWriteLock for safe, concurrent access.

##### Features and Methods
- addUser: Adds a new user to the database and checks if they don’t already exist. Returns true on success, false if the user exists.
- getUser: Retrieves a User object based on the given username.
- updateUser: Updates an existing user’s details. Returns true if successful, false if the user doesn’t exist.
-  deleteUser: Deletes a user after verifying the password (for security reasons)
-  loadUsers(): Loads all users from the .txt file into a Map<String, User>.
-  saveUsers(Map<String, User> users): Writes the user map back to the .txt file.

### User Class

#### The User class represent attributes such as username, password, uuid, and friends.

Attributes:
- Username: The user's name, which is unique across the database.
- Password: The user's password to access into the users profile.
- uuid: An another unique identifier for the user.
- loggedIn: A flag indicating whether the user is currently logged in.
- friends: A list of usernames representing the user's friends.

*Methods explained in javadoc

#### Database File Structure
User data is stored in a serialized .txt file specified by UserDatabase. The loadUsers and saveUsers methods ensure that data is read and written correctly, with each entry mapping a username to a User object.

### Several libraries and packages were used to support specific functions

### Java Libraries: 
For using I/O operations and serialization. Java Swing (javax.swing.text.PasswordView): For enabling to hide characters while entering passwords in UI applications (in this case terminal) .

### PostDatabase

#### The PostDatabase and its classes make up the essential of a social media post. The PostDatabase is responsible for the creation, reading, and retrieval of posts created in a .txt file with attributes such as title, content, author and date.

##### PostDatabase handles operations such as:
- Manage Post creation and storage
- Text File creation
- Uses ReentrantReadWriteLock for safe, concurrent access
- Exception handling

##### Features and Methods:
- addPost(Post post): Adds a new post to the post list and appends it to the file. Returns
true if post is successfully added, false otherwise
- savePostFile(Post post): Appends a string representation of a post to the file. Uses
BufferedWriter for efficiency
- readPostDatabase(String fileName): Reads all the posts from the file into an array list.
Returns the list of posts as strings.
- getPost(): Returns all the posts stored in the post list.
- getPostID(int id): Check if a post with a specified ID exists by reading all posts from the
file.

### Post Class

#### The Post class represents attributes such as title, content, author, date, ID, comments, upvotes and downvotes.

##### Attributes:
- Title: The given name of a post
- Content: The material of the post
- Author: The name of the user who created the post
- ID: A unique identifier for a post
- Comments: The reactions to a post
- Upvotes: An interactable agreement with a post
- Downvotes: An interactable disagreement with a post

*Methods explained in javadoc

#### Database File Structure
Post data is stored in a serialized .txt file specified by PostDatabase. The addPost and
savePostFile methods ensure that the data is read, written, and stored correctly.

#### Java Libraries:
(please update cause I'm not that knowledgeable)

------

## Phase 2

### Server Class
The Server class is the backbone of the multi-client server application. It manages client-server
communication, user authentication, and post management by utilizing the UserDatabase and
PostDatabase. The Server listens for incoming client connections, processes user actions, and ensures the
safe storage and retrieval of user and post data.

#### Responsibilities
- Main Method: Listens for client connections and initializes user and post databases
- clientHandler(): Creates a new ClientHandler thread for each client connection, ensuring
  isolation and independent communication.
- handleLogin(): Handles user signup by creating a new account in the user database.
- handleSignup(): Handles the creation of new posts by logged-in users.
- handleCreatePost(): Handles the viewing of posts.

#### Database File Structure:
- User data is managed by the UserDatabase class, and post data is managed by the PostDatabase
  class. Both databases are loaded from and saved to .txt files

### Client Class
The Client class is responsible for managing the communication between the server and an individual
client. It handles user authentication, post creation, and client requests through a set of interactive
commands. Each client gets their own instance of ClientHandler for concurrent handling, allowing
multiple clients to interact with the server simultaneously.

#### Responsibilities
- Client Interaction: Handles client inputs, including login, signup, post creation, and viewing
  posts.
- Authentication: Verifies user credentials during login and creates new accounts during signup.
- Post Management: Facilitates post creation by logged-in users and provides a space for future
  post viewing functionality
- Session Management: Keeps track of the user's login state and allows them to log out when
  necessary.

#### Features and Methods:
- handleLogin(BufferedReader input, PrintWriter output): Authenticates users based on their
  username and password by verifying against the UserDatabase
- handleSignup(BufferedReader input, PrintWriter output): Creates a new user account and
  adds it to the UserDatabase
- handleCreatePost(BufferedReader input, PrintWriter output): Allows logged-in users to
  create posts by providing a title and content. These posts are stored in the PostDatabase
- handleViewPosts(BufferedReader input, PrintWriter output): A placeholder method for
  future implementation that will allow users to view posts
- Session Control: The class maintains a session for the user, tracking whether they are logged in
  or logged out.

#### Database file structure: 
- Post data is stored in the PostDatabase class, and user data is managed in the UserDatabase. Both
  databases are represented by .txt files, with each post and user being serialized into a string
  format and saved.

#### General Flow
1. Server Initialization: The server listens for incoming connections and initializes databases.
2. Client Interaction: Each client is assigned a separate ClientHandler thread that communicates
   with the server and handles specific actions like login, signup, post creation, etc.
3. User Authentication: Users must authenticate before they can perform certain actions, such as
   creating posts.
4. Post Management: Once logged in, users can create posts, which are added to the PostDatabase.