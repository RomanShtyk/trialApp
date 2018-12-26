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
import com.example.rdsh.testapp.Activities.MainActivity;
import com.example.rdsh.testapp.Adapters.ListAdapter;
import com.example.rdsh.testapp.R;

import java.util.Objects;

import static com.example.rdsh.testapp.Activities.MainActivity.chatFragment;


public class ListFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        ListAdapter listAdapter = new ListAdapter(view.getContext(), MainActivity.myAppDatabase.daoUser().getAll());
        ListView lvMain = view.findViewById(R.id.list_view);
        lvMain.setAdapter(listAdapter);


        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    bundle.putInt("position", position);
                    chatFragment.setArguments(bundle);
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.container, chatFragment).commit();
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    bundle.putInt("position", position);
                    chatFragment.setArguments(bundle);
                    if (chatFragment.isAdded()) {
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction().remove(chatFragment)
                                .add(R.id.container, chatFragment).addToBackStack(null).commit();
                    } else {
                        Objects.requireNonNull(getActivity()).findViewById(R.id.tvChooseChat).setVisibility(View.GONE);
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction()
                                .add(R.id.container, chatFragment).addToBackStack(null).commit();
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
