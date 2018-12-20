package com.example.rdsh.testapp.Activities;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rdsh.testapp.EntitiesOLD.Message;
import com.example.rdsh.testapp.EntitiesOLD.User;
import com.example.rdsh.testapp.Fragments.ChatListFragment;
import com.example.rdsh.testapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ChatListFragment fragmentChatList;
    public static final List<User> users = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateUsersChats(savedInstanceState);
        fragmentChatList = new ChatListFragment();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragmentChatList).commit();
        }

    }

    private void generateUsersChats(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            User friend1 = new User("friend1");
            User friend2 = new User("friend2");
            @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
            Message message = new Message("Message 1 true", date, true);
            Message message2 = new Message("Hi!! false", date, false);
            Message message3 = new Message("Message 2 false", date, false);
            Message message4 = new Message("Message 3 false", date, false);
            Message message5 = new Message("Message 4 false", date, false);
            Message message6 = new Message("Message 5 false", date, false);
            Message message7 = new Message("Message 7 false", date, false);
            Message message8 = new Message("Message 8 false", date, false);
            Message message9 = new Message("Message 9 false", date, false);
            Message message10 = new Message("Message 10 false", date, false);
            Message message11 = new Message("Message 11 false", date, false);
            Message message12 = new Message("Message 12 false", date, false);
            Message message13 = new Message("Message 13 false", date, false);
            friend1.chatHistory.add(message);
            friend1.chatHistory.add(message3);
            friend1.chatHistory.add(message4);
            friend1.chatHistory.add(message5);
            friend1.chatHistory.add(message6);
            friend1.chatHistory.add(message7);
            friend1.chatHistory.add(message8);
            friend1.chatHistory.add(message9);
            friend1.chatHistory.add(message10);
            friend1.chatHistory.add(message11);
            friend1.chatHistory.add(message12);
            friend1.chatHistory.add(message13);

            friend2.chatHistory.add(message2);

            users.add(friend1);
            users.add(friend2);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager().beginTransaction().add(R.id.ChatListFragment, fragmentChatList).commit();
        }
    }

    public void setActionBarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }


}
