package com.example.serdar.myapplication;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {



    private List<String> questions;
    private List<String> choices;
    private List<String> answers;
    private String[] userAnswers = new String[11];

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView txtHeader;
        public Button choice1;
        public Button choice2;
        public Button choice3;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            choice1 = (Button) v.findViewById(R.id.bChoice1);
            choice2 = (Button) v.findViewById(R.id.bChoice2);
            choice3 = (Button) v.findViewById(R.id.bChoice3);
        }
    }

    public void add(int position, String item) {
        questions.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        questions.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> questions1,List<String> choices1,List<String> answers1) {
        questions = questions1;
        choices = choices1;
        answers = answers1;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.example, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final int maxPos=10;
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = questions.get(position);
        holder.txtHeader.setText(name);
        holder.choice1.setText(choices.get(3*position));
        holder.choice2.setText(choices.get(3*position+1));
        holder.choice3.setText(choices.get(3*position+2));
        holder.choice1.setBackgroundColor(Color.parseColor("#f4ffffff"));
        holder.choice2.setBackgroundColor(Color.parseColor("#f4ffffff"));
        holder.choice3.setBackgroundColor(Color.parseColor("#f4ffffff"));

        holder.choice1.setVisibility(View.GONE);
        holder.choice2.setVisibility(View.GONE);
        holder.choice3.setVisibility(View.GONE);




        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.choice1.isShown()){
                    if(position != maxPos){
                        holder.choice1.setVisibility(View.VISIBLE);
                        holder.choice2.setVisibility(View.VISIBLE);
                        holder.choice3.setVisibility(View.VISIBLE);
                    }else{
                        holder.choice1.setVisibility(View.VISIBLE);
                    }

                }else{
                    holder.choice1.setVisibility(View.GONE);
                    holder.choice2.setVisibility(View.GONE);
                    holder.choice3.setVisibility(View.GONE);
                }


            }
        });


        if(position==maxPos) {
            holder.choice2.setVisibility(View.GONE);
            holder.choice3.setVisibility(View.GONE);

        }

        holder.choice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int pass=0;
                int correct=0;
                int wrong=0;

                userAnswers[position] = "1";
                holder.choice1.setBackgroundColor(Color.YELLOW);
                holder.choice2.setBackgroundColor(Color.parseColor("#f4ffffff"));
                holder.choice3.setBackgroundColor(Color.parseColor("#f4ffffff"));
                if (position == maxPos) {

                    for (int i=0;i<maxPos;i++) {
                        if (userAnswers[i] == null) {
                            pass++;
                        } else if (userAnswers[i].equals(answers.get(i)) ) {
                            correct++;
                        } else {
                            wrong++;
                        }
                    }

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(v.getContext());
                    dlgAlert.setMessage("Correct answers: " + correct + " , Wrong answers: " + wrong + " , Blank answers: "+pass);
                    dlgAlert.setTitle("Results:");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    holder.choice1.setText("Go back to menu");

                    holder.choice1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent welcomeIntent = new Intent(v.getContext(), CategoryActivity.class);
                            v.getContext().startActivity(welcomeIntent);
                        }
                    });


                }
            }
        });

        holder.choice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                userAnswers[position]="2";
                holder.choice1.setBackgroundColor(Color.parseColor("#f4ffffff"));
                holder.choice2.setBackgroundColor(Color.YELLOW);
                holder.choice3.setBackgroundColor(Color.parseColor("#f4ffffff"));
            }
        });

        holder.choice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                userAnswers[position]="3";
                holder.choice1.setBackgroundColor(Color.parseColor("#f4ffffff"));
                holder.choice2.setBackgroundColor(Color.parseColor("#f4ffffff"));
                holder.choice3.setBackgroundColor(Color.YELLOW);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return questions.size();
    }

}