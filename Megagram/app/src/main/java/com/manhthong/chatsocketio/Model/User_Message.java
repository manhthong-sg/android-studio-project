package com.manhthong.chatsocketio.Model;


public class User_Message {
    String UserName;
    String Message_desc;
    String Message_time;
    int imgUser;

    public User_Message(String userName, String message_desc, String message_time, int imgUser) {
        UserName = userName;
        Message_desc = message_desc;
        Message_time = message_time;
        this.imgUser = imgUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMessage_desc() {
        return Message_desc;
    }

    public void setMessage_desc(String message_desc) {
        Message_desc = message_desc;
    }

    public String getMessage_time() {
        return Message_time;
    }

    public void setMessage_time(String message_time) {
        Message_time = message_time;
    }

    public int getImgUser() {
        return imgUser;
    }

    public void setImgUser(int imgUser) {
        this.imgUser = imgUser;
    }

}
