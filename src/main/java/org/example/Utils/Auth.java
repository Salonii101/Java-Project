//package org.example.Utils;
//
//import org.example.dao.UserDAO;
//import org.example.dao.impl.UserImpl;
//import org.example.models.User;
//import org.example.services.UserService;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import java.util.Scanner;
//
//public class Auth {
//    private static String currentUserId = "";
//    private static String currentUserName = "";
//
//    public static String register(Scanner scanner) {
//        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
//        cfg.addAnnotatedClass(User.class);
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//
//        try {
//            UserDAO userDAO = new UserImpl(sessionFactory);
//            UserService userService = new UserService(userDAO);
//
//            System.out.print("Enter your name: ");
//            String name = scanner.nextLine().trim();
//            System.out.print("Create a password: ");
//            String password = scanner.nextLine();
//            System.out.print("Enter role:  ");
//            String role = scanner.nextLine();
//
//            // UserService will hash with BCrypt and persist
//            userService.register(name, password, role); // or "admin", depending on UI
//
//            // fetch the persisted user to get DB-generated UUID
//            User registeredUser = userDAO.findAll().stream()
//                    .filter(u -> u.getName().equalsIgnoreCase(name))
//                    .findFirst()
//                    .orElseThrow(() -> new RuntimeException("Registered user not found"));
//
//            currentUserId = registeredUser.getId().toString();
//            currentUserName = registeredUser.getName();
//
//            System.out.println("Registration successful! Your User ID is: " + currentUserId);
//            return currentUserId;
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            sessionFactory.close();
//        }
//    }
//
//    public static boolean login(Scanner scanner) {
//        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
//        cfg.addAnnotatedClass(User.class);
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//
//        try {
//            UserDAO userDAO = new UserImpl(sessionFactory);
//            UserService userService = new UserService(userDAO);
//
//            System.out.print("Enter your name: ");
//            String name = scanner.nextLine().trim();
//            System.out.print("Enter your password: ");
//            String password = scanner.nextLine();
//
//            User user = userService.login(name, password);
//
//            if (user != null) {
//                currentUserId = user.getId().toString();
//                currentUserName = user.getName();
//                System.out.println("Login successful! Welcome, " + currentUserName);
//                return true;
//            } else {
//                System.out.println("Invalid username or password.");
//                return false;
//            }
//        } finally {
//            sessionFactory.close();
//        }
//    }
//
//    // Accessors
//    public static String getCurrentUserId() { return currentUserId; }
//    public static String getCurrentUserName() { return currentUserName; }
//}
