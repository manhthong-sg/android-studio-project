package com.manhthong.chatsocketio.Model;

public class MessageFormat {

    //private String Username;
    private String Message;
    private String Time;
    private String roomId;
    private String _id;
    private String UniqueId;

    public MessageFormat(String uniqueId, String message, String time) {
        Message = message;
        Time = time;
        UniqueId = uniqueId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }
}
