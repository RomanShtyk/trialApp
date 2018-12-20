package com.example.rdsh.testapp.Entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    public List<Message> chatHistory = new ArrayList<Message>();

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
