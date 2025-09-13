package org.example;

import org.example.Utils.Auth;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class UserTestApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            try {
                Auth.register(scanner); // calls your userService.register internally
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (choice == 2) {
            boolean success = Auth.login(scanner); // uses userService.login internally
            if (success) {
                System.out.println("✅ User authenticated successfully!");
            } else {
                System.out.println("❌ Authentication failed.");
            }
        }

        scanner.close();
    }
}
