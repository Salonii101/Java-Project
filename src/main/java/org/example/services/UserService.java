package org.example.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.models.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register new user
    public User register(String name, String password, String role) throws Exception {
        if (isUserExists(name)) {
            throw new Exception("User already exists with this name.");
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        User user = new User();
        user.setName(name);
        user.setHashPassword(hashedPassword);
        user.setRole(role);

        return userRepository.save(user);
    }

    // Login: find user by name and verify password
    public User login(String name, String password) {
        Optional<User> optionalUser = userRepository.findByNameIgnoreCase(name);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getHashPassword());
            if (result.verified) {
                return user;
            }
        }
        return null;
    }

    // Check if user exists
    public boolean isUserExists(String name) {
        return userRepository.findByNameIgnoreCase(name).isPresent();
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User getUserById(String id) {
    return userRepository.findById(java.util.UUID.fromString(id)).orElse(null);
    }

    // Update user
    public User updateUser(User user) {
        return userRepository.save(user); // save handles both create & update
    }

    // Delete user
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // Delete by ID (optional)
    public void deleteUserById(String id) {
    userRepository.deleteById(java.util.UUID.fromString(id));
    }
}
