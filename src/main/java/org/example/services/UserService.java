package org.example.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.dao.UserDAO;
import org.example.dao.impl.UserImpl;
import org.example.models.User;

public class UserService {
    private final UserDAO userDAO = new UserImpl();

    public void register(String userId , String username, String rawPassword, String role) {
        String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        User user = new User();
        user.setName(username);
        user.setHashPassword(hashed);
        user.setRole(role);
        userDAO.save(user);
    }

    public User login(String userId , String username, String rawPassword) {
        User user = userDAO.findById(userId);
        if (user != null && BCrypt.checkpw(rawPassword, user.getHashPassword())) {
            return user; // Login successful
        }
        return null; // Invalid login
    }
}
