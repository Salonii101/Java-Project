package org.example.models;

public class User {
    private int id ;
    private String name ;
    private String hashPassword ;
    private String role ;


    User() {} ;

    public User(int id , String name , String hashPassword){
        this.id = id ;
        this.name = name  ;
        this.hashPassword = hashPassword ;
        this.role = role ;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
