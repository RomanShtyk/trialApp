package com.example.rdsh.testapp.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rdsh.testapp.Adapters.ListChatAdapter;
import com.example.rdsh.testapp.Activities.MainActivity;
import com.example.rdsh.testapp.R;

import static com.example.rdsh.testapp.Activities.MainActivity.users;


public class ChatList extends Fragment {

    private Button btnChatList;
    private FragmentTransaction transaction;
    private FragmentManager manager;
    Chat fragmentChat;
    ListView lvMain;

    ListChatAdapter listChatAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);


        fragmentChat = new Chat();
        manager = getFragmentManager();

        listChatAdapter = new ListChatAdapter(view.getContext(), users);

        ListView lvMain = view.findViewById(R.id.list_view);
        lvMain.setAdapter(listChatAdapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    fragmentChat.setArguments(bundle);
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.container, fragmentChat);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    fragmentChat.setArguments(bundle);
                    transaction = manager.beginTransaction();
                    if (fragmentChat.isAdded()) {
                        transaction.remove(fragmentChat);
                        transaction.add(R.id.container2, fragmentChat);
                    } else {
                        transaction.add(R.id.container2, fragmentChat);
                    }
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity())
                .setActionBarTitle("Chats");
    }
}
