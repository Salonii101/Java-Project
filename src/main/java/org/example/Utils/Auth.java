package org.example.Utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.example.dao.UserDAO;
import org.example.dao.SubjectDAO;
import org.example.dao.impl.UserImpl;
import org.example.dao.impl.SubjectImpl;
import org.example.models.*;
import org.example.services.UserService;
import org.example.services.SubjectService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class Auth {
   // private static final String FILE_NAME = "users.txt";
    private static String currentUserId = "";
    private static String currentUserName = "";

    public static String register(Scanner scanner) throws IOException {

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(User.class);


        SessionFactory sessionFactory = cfg.buildSessionFactory() ;
        try {
            UserDAO userDAO = new UserImpl(sessionFactory);
            UserService userService = new UserService(userDAO);

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Create a password: ");
            String password = scanner.nextLine();

            String hashedPassword = hashPassword(password);
            String userId = "U" + UUID.randomUUID().toString().substring(0,12) ;

            UserService.register(name,hashedPassword,"Admin") ;


            currentUserId = userId;
            currentUserName = name;

            System.out.println("Registration successful! Your User ID is: " + userId);
            return userId;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public static boolean login(Scanner scanner) throws IOException {
//        System.out.print("Enter User ID: ");
//        String userId = scanner.nextLine();
//        System.out.print("Enter Password: ");
//        String password = scanner.nextLine();
//
//        String hashedPassword = hashPassword(password);
//
//        try (BufferedReader br = new BufferedReader(new FileReader(PathFile.USER_FILE))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 3 && parts[0].equals(userId) && parts[1].equals(hashedPassword)) {
//                    currentUserId = parts[0];
//                    currentUserName = parts[2];
//                    System.out.println("Welcome, " + currentUserName + "!");
//                    return true;
//                }
//            }
//        }
//        System.out.println("Invalid User ID or Password.");
//        return false;
//    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available.");
        }
    }
    // Required for QuizApp.java to access logged-in user details
    public static String getCurrentUserId() {
    return currentUserId;
}
    public static String getCurrentUserName() {
    return currentUserName;
}
}
