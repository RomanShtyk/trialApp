package com.example.rdsh.testapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rdsh.testapp.Activities.MainActivity;
import com.example.rdsh.testapp.Data.User;
import com.example.rdsh.testapp.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private final LayoutInflater lInflater;
    private final List<User> users;

    public ListAdapter(Context context, List<User> users) {
        this.users = users;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.layout_chat_list_item, parent, false);
        }
       // int userId = position + 13;
        int userId = position + 9;
        User user = getUser(position);
        user.setChatHistory(MainActivity.myAppDatabase.daoMessage().getChatByUserId(userId));

        ((TextView) view.findViewById(R.id.userName)).setText(user.getName());
        if (user.getChatHistory().get((user.getChatHistory().size() - 1)).getIsFromMe() == 1)
            ((TextView) view.findViewById(R.id.lastMessage)).setText("me:" + user.getChatHistory().get((user.getChatHistory().size() - 1)).getMessage());
        else
            ((TextView) view.findViewById(R.id.lastMessage)).setText(user.getChatHistory().get((user.getChatHistory().size() - 1)).getMessage());
        ((TextView) view.findViewById(R.id.time)).setText(user.getChatHistory().get((user.getChatHistory().size() - 1)).getTime());
        return view;
    }

    private User getUser(int position) {
        return ((User) getItem(position));
    }
}
