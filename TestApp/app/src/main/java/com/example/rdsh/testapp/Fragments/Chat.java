package com.example.rdsh.testapp.Fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rdsh.testapp.Entities.Message;
import com.example.rdsh.testapp.Entities.User;
import com.example.rdsh.testapp.Activities.MainActivity;
import com.example.rdsh.testapp.Adapters.MessageChatAdapter;
import com.example.rdsh.testapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Chat extends Fragment {

    String title;

    private FragmentManager manager;
    MessageChatAdapter messageChatAdapter;
    Button sendButton, testButton, stopButton;
    EditText creatorEd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chat, container, false);

        Bundle bundle = getArguments();
        final int itemPosition = bundle.getInt("position");
        manager = getFragmentManager();
        final List<User> users = MainActivity.users;
        title = users.get(itemPosition).getUserName();

        messageChatAdapter = new MessageChatAdapter(view.getContext(), users.get(itemPosition).chatHistory);
        final ListView lvMain = view.findViewById(R.id.listview_message_list);
        lvMain.setAdapter(messageChatAdapter);
        sendButton = view.findViewById(R.id.button_chatbox_send);
        creatorEd = view.findViewById(R.id.edittext_chatbox);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage = creatorEd.getText().toString();
                String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                Message message = new Message(newMessage, date, true);
                users.get(itemPosition).chatHistory.add(message);
                messageChatAdapter.notifyDataSetChanged();
                messageChatAdapter.notifyDataSetChanged();
            }
        });

        //notification test
        final NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel("my_channel0", "my_channel", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getActivity(), "my_channel0");

        final Handler h = new Handler();
        final Runnable run = new Runnable() {
            //stops after n times
            int nTimes = 3;
            boolean stopMe = false;
            int i = 0;

            public void run() {
                if (!stopMe) {
                    String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                    Message message = new Message("testNotify #" + (i++ + 1), date, false);
                    if (i > nTimes) {
                        stopMe = true;
                    }
                    users.get(itemPosition).chatHistory.add(message);
                    builder.setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("New message from: " + users.get(itemPosition).getUserName())
                            .setContentText(message.getMessage());

                    notificationManager.notify(1, builder.build());
                    messageChatAdapter.notifyDataSetChanged();
                    h.postDelayed(this, 1000);

                }
            }
        };

        testButton = view.findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run.run();
            }

        });

        return view;
    }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle(title);

    }
}
