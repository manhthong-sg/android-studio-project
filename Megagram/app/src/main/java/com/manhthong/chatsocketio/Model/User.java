package com.manhthong.chatsocketio.Model;

import java.io.Serializable;

public class User implements Serializable{
    String userID;
    String displayName;
    String email;
    String password;
    String phoneNumber;
    String gender;
    String birthday;
    String address;
    public User(){}
    public User(String displayName, String email, String password, String phoneNumber) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(String displayName, String email, String phoneNumber, String gender, String birthday, String address) {
        this.displayName = displayName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

