package com.manhthong.chatsocketio.Model;

public class User {
    String displayName;
    String email;
    String password;
    String phoneNumber;
    public User(){}
    public User(String displayName, String email, String password, String phoneNumber) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

