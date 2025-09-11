package org.example;

import org.example.dao.UserDAO;
import org.example.dao.SubjectDAO;
import org.example.dao.impl.UserImpl;
import org.example.dao.impl.SubjectImpl;
import org.example.models.*;
import org.example.services.UserService;
import org.example.services.SubjectService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in) ;
        System.out.println("Welcome to the Quiz App");
        System.out.println("Choose 1) Register 2)Login ");
        int choosedOption = input.nextInt() ;

        if(choosedOption == 1){

        }
    }
}
