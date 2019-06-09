package com.example.serdar.smartsmsbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MyBlackListAdapter extends RecyclerView.Adapter<MyBlackListAdapter.ViewHolder> {

    ArrayList<String> blackList;
    TextView blackListText;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView blackListText;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            blackListText = (TextView)v.findViewById(R.id.blackListText);
        }
    }

    public void add(int position, String item) {
        blackList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        blackList.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyBlackListAdapter(ArrayList<String> blackList)
    {
        this.blackList = blackList;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyBlackListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_blacklist_recycler, parent, false);

        // set the view's size, margins, paddings and layout parameters
        MyBlackListAdapter.ViewHolder vh = new MyBlackListAdapter.ViewHolder(v);
        return vh;
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyBlackListAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.blackListText.setText(blackList.get(position));

        holder.blackListText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleted = blackList.get(position).toString();
                blackList.remove(position);
                Toast.makeText(v.getContext(), deleted + " removed from blacklist!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return blackList.size();
    }

}
