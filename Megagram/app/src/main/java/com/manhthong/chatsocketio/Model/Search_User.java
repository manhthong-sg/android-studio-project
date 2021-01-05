package com.manhthong.chatsocketio.Model;


public class Search_User {
    String UserName;
    int imgUser;
    String address;
    String phoneNumber;
    public  Search_User(){}
    public Search_User(String userName, int imgUser, String address, String phoneNumber) {
        UserName = userName;
        this.imgUser = imgUser;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public int getImgUser() {
        return imgUser;
    }

    public void setImgUser(int imgUser) {
        this.imgUser = imgUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
