package org.example.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.dao.UserDAO;
import org.example.models.User;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    // Constructor: inject the DAO implementation
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Registers a new user.
     * Hashes the password and saves the user in the database.
     */
    public void register(String name, String password, String role) throws Exception {
        if (isUserExists(name)) {
            throw new Exception("User already exists with this name.");
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        User user = new User(name, hashedPassword, role);
        userDAO.save(user);
    }

    /**
     * Logs in a user by verifying the password.
     * Returns the user object if successful, otherwise null.
     */
    public User login(String name, String password) {
        List<User> users = userDAO.findAll(); // or use a findByName() method if implemented
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getHashPassword());
                if (result.verified) {
                    return user;
                }
                break;
            }
        }
        return null;
    }

    /**
     * Checks if a user already exists by name.
     */
    public boolean isUserExists(String name) {
        List<User> users = userDAO.findAll();
        return users.stream().anyMatch(user -> user.getName().equalsIgnoreCase(name));
    }

    /**
     * Retrieves all users.
     */
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    /**
     * Finds a user by ID.
     */
    public User getUserById(String id) {
        return userDAO.findById(id);
    }

    /**
     * Updates user details.
     */
    public void updateUser(User user) {
        userDAO.update(user);
    }

    /**
     * Deletes a user from the database.
     */
    public void deleteUser(User user) {
        userDAO.delete(user);
    }
}
