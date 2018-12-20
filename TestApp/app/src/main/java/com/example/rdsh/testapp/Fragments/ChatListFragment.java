package com.example.rdsh.testapp.Fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rdsh.testapp.Adapters.ListChatAdapter;
import com.example.rdsh.testapp.Activities.MainActivity;
import com.example.rdsh.testapp.R;

import java.util.Objects;

import static com.example.rdsh.testapp.Activities.MainActivity.users;


public class ChatListFragment extends Fragment {

    private ChatFragment fragmentChat;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        fragmentChat = new ChatFragment();
        ListChatAdapter listChatAdapter = new ListChatAdapter(view.getContext(), users);
        ListView lvMain = view.findViewById(R.id.list_view);
        lvMain.setAdapter(listChatAdapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    bundle.putInt("position", position);
                    fragmentChat.setArguments(bundle);
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.container, fragmentChat).addToBackStack(null).commit();
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    bundle.putInt("position", position);
                    fragmentChat.setArguments(bundle);
                    if (fragmentChat.isAdded()) {
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction().remove(fragmentChat)
                                .add(R.id.container2, fragmentChat).addToBackStack(null).commit();
                    } else {
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction()
                                .add(R.id.container2, fragmentChat).addToBackStack(null).commit();
                    }
                }

            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        ((MainActivity) Objects.requireNonNull(getActivity()))
                .setActionBarTitle("Chats");
    }
}
