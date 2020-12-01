package com.manhthong.chatsocketio.Model;


public class Online_User {
    String UserName;
    int imgUser;

    public Online_User(String userName, int imgUser) {
        UserName = userName;
        this.imgUser = imgUser;
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

}
