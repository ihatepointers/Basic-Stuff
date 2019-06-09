package com.example.serdar.smartsmsbox;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentSpam extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<String> senders = new ArrayList<String>();
    ArrayList<String> messages = new ArrayList<String>();

    final FragmentActivity c = getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sm, container, false);
        //super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        recyclerView.setAdapter(new MyAdapter(MainActivity.blackListSenders, MainActivity.blackListMessages));

        return rootView;

        /*
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(senders, messages);
        recyclerView.setAdapter(mAdapter);
        */

    }

    @Override
    public String toString() {
        return "spam";
    }
}
