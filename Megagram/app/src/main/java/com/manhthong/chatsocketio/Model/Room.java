package com.manhthong.chatsocketio.Model;

import java.io.Serializable;

public class Room implements Serializable{
    String _id;
    String user1;
    String user2;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public Room(String _id, String user1, String user2) {
        this._id = _id;
        this.user1 = user1;
        this.user2 = user2;
    }

    public Room(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
}

