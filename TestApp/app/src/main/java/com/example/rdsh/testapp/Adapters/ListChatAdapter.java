package com.example.rdsh.testapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rdsh.testapp.EntitiesOLD.User;
import com.example.rdsh.testapp.R;

import java.util.List;

public class ListChatAdapter extends BaseAdapter {

    private final LayoutInflater lInflater;
    private final List<User> users;

    public ListChatAdapter(Context context, List<User> users) {
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

        User user = getUser(position);
        ((TextView) view.findViewById(R.id.userName)).setText(user.getUserName());
        if (user.chatHistory.get((user.chatHistory.size() - 1)).getFromMe())
            ((TextView) view.findViewById(R.id.lastMessage)).setText("me:" + user.chatHistory.get((user.chatHistory.size() - 1)).getMessage());
        else
            ((TextView) view.findViewById(R.id.lastMessage)).setText(user.chatHistory.get((user.chatHistory.size() - 1)).getMessage());
        ((TextView) view.findViewById(R.id.time)).setText(user.chatHistory.get((user.chatHistory.size() - 1)).getTime());
        return view;
    }

    private User getUser(int position) {
        return ((User) getItem(position));
    }
}
