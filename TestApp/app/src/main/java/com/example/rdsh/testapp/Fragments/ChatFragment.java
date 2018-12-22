package com.example.rdsh.testapp.Fragments;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rdsh.testapp.Activities.MainActivity;
import com.example.rdsh.testapp.Adapters.MessageChatAdapter;
import com.example.rdsh.testapp.Data.Message;
import com.example.rdsh.testapp.Data.User;
import com.example.rdsh.testapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static com.example.rdsh.testapp.Activities.MainActivity.FALSE;
import static com.example.rdsh.testapp.Activities.MainActivity.TRUE;

public class ChatFragment extends Fragment {

    private String title;

    private MessageChatAdapter messageChatAdapter;

    EditText creatorEd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chat, container, false);

        int itemPosition = 0;
        if (getArguments() != null)
            itemPosition = getArguments().getInt("position");
        title = MainActivity.myAppDatabase.daoUser().getName(itemPosition);

        List<Message> messages = MainActivity.myAppDatabase.daoMessage().getChatByUserId(itemPosition);

        messageChatAdapter = new MessageChatAdapter(view.getContext(), messages );
        final ListView lvMain = view.findViewById(R.id.listview_message_list);
        lvMain.setAdapter(messageChatAdapter);

        Button sendButton = view.findViewById(R.id.button_chatbox_send);
        creatorEd = view.findViewById(R.id.edittext_chatbox);
        final int finalItemPosition = itemPosition;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage = creatorEd.getText().toString();
                @SuppressLint("SimpleDateFormat") String time = new SimpleDateFormat("HH:mm")
                        .format(Calendar.getInstance().getTime());
                Message message = new Message(newMessage, time, TRUE, finalItemPosition);
                MainActivity.myAppDatabase.daoMessage().addMessage(message);
                messageChatAdapter.notifyDataSetChanged();
            }

        });

//        //notification test
//        final Runnable run = notificationSet(users);
//
//        Button testButton = view.findViewById(R.counter.testButton);
//        testButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                run.run();
//            }
//
//        });

        return view;
    }

//    @NonNull
//    private Runnable notificationSet(final List<User> users) {
//        final NotificationManager notificationManager = (NotificationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel notificationChannel = new NotificationChannel("my_channel0", "my_channel", importance);
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.enableVibration(true);
//            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//        final NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(getActivity(), "my_channel0");
//
//        final Handler h = new Handler();
//        return new Runnable() {
//            //stops after n times
//            final int nTimes = 3;
//            boolean stopMe = false;
//            int i = 0;
//
//            public void run() {
//                if (!stopMe) {
//                    @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
//                    Message message = new Message(messageGenerateId,"testNotify #" + (i++ + 1), date, FALSE, finalItemPosition);
//                    if (i > nTimes) {
//                        stopMe = true;
//                    }
//                    users.get().chatHistory.add(message);
//                    builder.setSmallIcon(R.drawable.ic_launcher_background)
//                            .setContentTitle("New message from: " + users.get(itemPosition).getUserName())
//                            .setContentText(message.getMessage());
//
//                    notificationManager.notify(1, builder.build());
//                    messageChatAdapter.notifyDataSetChanged();
//                    h.postDelayed(this, 1000);
//
//                }
//            }
//        };
//    }

    public void onResume() {
        super.onResume();

        ((MainActivity) Objects.requireNonNull(getActivity()))
                .setActionBarTitle(title);

    }
}
