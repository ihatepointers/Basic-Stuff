package com.example.serdar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Category1Activity extends AppCompatActivity {

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


        input.add("Q.1 When was Turkish Republic established?");
        input.add("Q.2 Who was the founder of the Turkish Republic?");
        input.add("Q.3 Who was the second president of Turkey?");
        input.add("Q.4 World War I began in which year?");
        input.add("Q.5 The Magna Carta was published by the King of which country? ");
        input.add("Q.6 What's the name of the famous battle where Napoleon Bonaparte was finally defeated?");
        input.add("Q.7 Which Roman Emperor built a massive wall across Northern Britain in 122 A.D.?");
        input.add("Q.8 In 1594 William Shakespeare joined the company of this London theatre.");
        input.add("Q.9 What famous 5th century A.D conqueror was known as 'The Scourge of God'?");
        input.add("Q.10 In which year did the demolition of the Berlin Wall begin?");
        input.add("End of Quiz!");


        choices.add("1920");
        choices.add("1922");
        choices.add("1923");

        choices.add("KAZIM KARABEKIR");
        choices.add("M. KEMAL ATATURK");
        choices.add("FEVZI CAKMAK");

        choices.add("TURGUT OZAL");
        choices.add("M. KEMAL ATATURK");
        choices.add("ISMET INONU");

        choices.add("1935");
        choices.add("1917");
        choices.add("1914");

        choices.add("ITALY");
        choices.add("ENGLAND");
        choices.add("FRANCE");

        choices.add("BATTLE OF WATERLOO");
        choices.add("BATTLE BUNKER HILL");
        choices.add("BATTLE OF THE BULGE");


        choices.add("AUGUSTUS");
        choices.add("HADRIAN");
        choices.add("MARCUS AURELIUS");

        choices.add("THE LONDON PALLADIUM");
        choices.add("OXFORD UNIVERSITY THEATRE");
        choices.add("THE GLOBE");

        choices.add("ATILLA THE HUN");
        choices.add("WILLIAM THE CONQUERER");
        choices.add("HANNIBAL");

        choices.add("1985");
        choices.add("1987");
        choices.add("1989");

        choices.add("OK");
        choices.add("OK");
        choices.add("OK");

        answers.add("3");
        answers.add("2");
        answers.add("3");
        answers.add("3");
        answers.add("2");
        answers.add("1");
        answers.add("2");
        answers.add("3");
        answers.add("1");
        answers.add("3");

        answers.add("OK");




        mAdapter = new MyAdapter(input,choices,answers);
        recyclerView.setAdapter(mAdapter);




    }



}
