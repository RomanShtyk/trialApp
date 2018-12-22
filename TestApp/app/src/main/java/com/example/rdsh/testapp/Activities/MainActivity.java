package com.example.rdsh.testapp.Activities;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rdsh.testapp.Data.MyAppDatabase;
import com.example.rdsh.testapp.Fragments.ListFragment;
import com.example.rdsh.testapp.Data.Message;
import com.example.rdsh.testapp.Data.User;
import com.example.rdsh.testapp.MyApplication;
import com.example.rdsh.testapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static MyAppDatabase myAppDatabase;

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    private ListFragment fragmentChatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAppDatabase = Room.databaseBuilder(MyApplication.getAppContext(), MyAppDatabase.class, "chatApp")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        generateDB(savedInstanceState);

        fragmentChatList = new ListFragment();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragmentChatList).commit();
        }

    }

    private void generateDB(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            //.fallbackToDestructiveMigration() shitty code
            myAppDatabase.daoMessage().deleteAll();
            myAppDatabase.daoUser().deleteAll();
            User user = new User("Friend0");
            User user1 = new User("Friend1");
            myAppDatabase.daoUser().addUser(user);
            myAppDatabase.daoUser().addUser(user1);
            // TODO: 21.12.2018 sqlite browser , визначити чи автоінкрементиться айді і порішати то всьо
            @SuppressLint("SimpleDateFormat") String time = new SimpleDateFormat("HH:mm")
                    .format(Calendar.getInstance().getTime());
            Message message = new Message("Hi!", time, TRUE, user.getId());
            Message message1 = new Message("Hi!", time, FALSE, user.getId());
            Message message2 = new Message("Hi!", time, TRUE, user1.getId());
            Message message3 = new Message("Hi!", time, FALSE, user1.getId());
            myAppDatabase.daoMessage().addMessage(message);
            myAppDatabase.daoMessage().addMessage(message1);
            myAppDatabase.daoMessage().addMessage(message2);
            myAppDatabase.daoMessage().addMessage(message3);
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
