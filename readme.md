# CS 18000 Team Project - Social Media Platform

## UserDatabase

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

## User Class

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

## PostDatabase

