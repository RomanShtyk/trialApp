package com.example.rdsh.testapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rdsh.testapp.Data.Message;
import com.example.rdsh.testapp.R;

import java.util.List;

public class MessageChatAdapter extends BaseAdapter {

    private final LayoutInflater lInflater;
    private  List<Message> messages;

    public MessageChatAdapter(Context context, List<Message> messages) {
        this.messages = messages;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void updateList(List<Message> messages){
        this.messages.clear();
        this.messages = messages;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (messages.get(position).getIsFromMe() == 1) {
            view = lInflater.inflate(R.layout.layout_chat_out, parent, false);
            ((TextView) view.findViewById(R.id.message)).setText(messages.get(position).getMessage());
            ((TextView) view.findViewById(R.id.time)).setText(messages.get(position).getTime());
        } else if (messages.get(position).getIsFromMe() == 0) {
            view = lInflater.inflate(R.layout.layout_chat_in, parent, false);
            ((TextView) view.findViewById(R.id.message)).setText(messages.get(position).getMessage());
            ((TextView) view.findViewById(R.id.time)).setText(messages.get(position).getTime());
        }
        return view;
    }

    private Message getMessage(int position) {
        return (getItem(position));
    }
}