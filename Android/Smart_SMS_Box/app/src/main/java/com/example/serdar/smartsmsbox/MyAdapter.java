package com.example.serdar.smartsmsbox;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<String> senders;
    ArrayList<String> messages;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView senderText;
        public TextView messageText;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            senderText = (TextView)v.findViewById(R.id.senderText);
            messageText = (TextView)v.findViewById(R.id.messageText);
        }
    }

    public void add(int position, String item) {
        senders.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        senders.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> senders, ArrayList<String> messages)
    {
        this.senders = senders;
        this.messages = messages;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_message_recycler, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.senderText.setText(senders.get(position));
        holder.messageText.setText(messages.get(position));

        holder.messageText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageIntent = new Intent(v.getContext(), SingleMessage.class);
                messageIntent.putExtra("SENDER_STRING",senders.get(position));
                messageIntent.putExtra("MESSAGE_STRING",messages.get(position));
                v.getContext().startActivity(messageIntent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return senders.size();
    }

}
