package com.example.rdsh.testapp.EntitiesOLD;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    public final List<Message> chatHistory = new ArrayList<>();

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
