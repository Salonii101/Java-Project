package org.example.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.dao.UserDAO;
import org.example.dao.impl.UserImpl;
import org.example.models.User;
import org.hibernate.SessionFactory;

public class UserService {
    private final UserDAO userDAO;

    public UserService(SessionFactory sessionFactory) {
        this.userDAO = new UserImpl(sessionFactory);
    }

    public void register(String username, String rawPassword, String role) {
        String hashed = BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
        User user = new User();
        user.setName(username);
        user.setHashPassword(hashed);
        user.setRole(role);
        userDAO.save(user);
    }

    public User login(String userId, String rawPassword) {
        User user = userDAO.findById(userId);
        if (user != null && BCrypt.verifyer().verify(rawPassword.toCharArray(), user.getHashPassword()).verified) {
            return user; // success
        }
        return null; // fail
    }
}
