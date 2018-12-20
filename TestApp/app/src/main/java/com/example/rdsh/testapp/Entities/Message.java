package com.example.rdsh.testapp.Entities;

import java.util.Date;

public class Message {
    private String message;
    private String time;
    final private Boolean isFromMe;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Message(String message, String time, Boolean isFromMe) {
        this.message = message;
        this.time = time;
        this.isFromMe = isFromMe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFromMe() {
        return isFromMe;
    }
}
