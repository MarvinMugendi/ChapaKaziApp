package com.marvedie.servicesmarketapp;

public class User {
    private String name;
    private String password;
    private String email;
    private String phone;
    private String id;


    public User() {

    }

    public User(String name, String password, String email, String phone, String id) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}