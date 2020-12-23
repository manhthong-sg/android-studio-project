package com.manhthong.chatsocketio.Model;

public class MessageFormat {

    private String Username;
    private String Message;
    private String Time;
    private String UniqueId;
    public MessageFormat(){

    }
    public MessageFormat(String uniqueId, String username, String message, String time) {
        Username = username;
        Message = message;
        UniqueId = uniqueId;
        Time=time;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
