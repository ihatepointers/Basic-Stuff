package com.example.serdar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Category3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> input = new ArrayList<>();
        List<String> choices = new ArrayList<>();
        List<String> answers = new ArrayList<>();


        input.add("Q.1 Who represented Turkey in Eurovision 2003?");
        input.add("Q.2 What musician won the Nobel Prize for Literature in 2016?");
        input.add("Q.3 Who was the lead singer of the band Queen?");

        input.add("Q.4 What was Elvis Presley's middle name?");

        input.add("Q.5 Who was the original drummer for Nirvana?");

        input.add("Q.6 The biggest selling music single of all time is?");

        input.add("Q.7 What was the first music video played on MTV ?");
        input.add("Q.8 Eminem : He's nervous but on the surface he looks _ and _ ?");
        input.add("Q.9 Britney Spears: _ Me Baby one more time");

        input.add("Q.10 MC Hammer : U Can't __ This");

        input.add("End of Quiz!");


        choices.add("SERTAB ERENER");
        choices.add("CAN BONOMO");
        choices.add("MANGA");

        choices.add("JUAN SANTOS");
        choices.add("BOB DYLAN");
        choices.add("OLIVER HART");

        choices.add("MIKE JAGGER");
        choices.add("FREDDIE MERCURY");
        choices.add("AXL ROSE");

        choices.add("MICKEY");
        choices.add("SHARKEISHA");
        choices.add("AARON");

        choices.add("CHAD CHANNING");
        choices.add("JOHN BONHAM");
        choices.add("KEITH MOON");

        choices.add("I WLLL SURVIVE");
        choices.add("CANDLE IN THE WIND");
        choices.add("ROCK YOUR BABY");


        choices.add("YOU BETTER RUN");
        choices.add("SHE WON'T DANCE WITH ME");
        choices.add("VIDEO KILLED THE RADIO STAR");

        choices.add("CALM / READY");
        choices.add("HYPER / FIT");
        choices.add("SAD / DEAD");

        choices.add("SLAP");
        choices.add("HIT");
        choices.add("PUSH");

        choices.add("TOUCH");
        choices.add("KILL");
        choices.add("HIT");






        choices.add("OK");
        choices.add("OK");
        choices.add("OK");

        answers.add("1");
        answers.add("2");
        answers.add("2");

        answers.add("3");

        answers.add("1");

        answers.add("2");

        answers.add("3");
        answers.add("1");
        answers.add("2");

        answers.add("1");

        answers.add("OK");




        mAdapter = new MyAdapter(input,choices,answers);
        recyclerView.setAdapter(mAdapter);




    }



}