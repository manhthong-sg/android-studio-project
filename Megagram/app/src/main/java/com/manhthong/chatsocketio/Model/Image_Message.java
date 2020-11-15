package com.manhthong.chatsocketio.Model;

public class Image_Message {
    int imgUser;
    String userName;
    public Image_Message(int imgUser, String userName) {
        this.imgUser = imgUser;
        this.userName=userName;
    }

    public int getImgUser() {
        return imgUser;
    }

    public void setImgUser(int imgUser) {
        this.imgUser = imgUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
