package com.example.serdar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Category4Activity extends AppCompatActivity {

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


        input.add("Q.1 In the movie The Wizard of Oz, what did the Scarecrow want from the wizard?");
        input.add("Q.2 What fictional city is the home of Batman?");
        input.add("Q.3 Which famous actor is known for saying : I'll be back ?");

        input.add("Q.4 What school does Harry Potter attend?");

        input.add("Q.5 What was Marilyn Monroe's name at birth? ");

        input.add("Q.6 What was the name of the whale in the 1993 movie, Free Willy ?");

        input.add("Q.7 Who played the fictional anti hero Deadpool in the 2016 movie?");
        input.add("Q.8 In 'The Lord Of The Rings', what's the name of the Elf that takes part in The Fellowship Of The Ring?");
        input.add("Q.9 In the Lion King, what was Simba's fathers name?");

        input.add("Q.10 What is the favorite food of the Teenage Mutant Ninja Turtles?");

        input.add("End of Quiz!");


        choices.add("BRAIN");
        choices.add("HEART");
        choices.add("HANDS");

        choices.add("CITY 17");
        choices.add("VICE CITY");
        choices.add("GOTHAM");

        choices.add("CHUCK NORRIS");
        choices.add("ARNOLD SCHWARZENGGER ");
        choices.add("VIN DIESEL");

        choices.add("UMBRIDGE");
        choices.add("HOGWARTS");
        choices.add("BUCKBEAK");

        choices.add("KAREN LUCILLE HALE");
        choices.add("JULIE ANNE SMITH");
        choices.add("NORMA JEANE MORTENSON ");

        choices.add("WILLY");
        choices.add("KEIKO");
        choices.add("MICKEY");


        choices.add("JHONNY DEPP");
        choices.add("WILL SMITH");
        choices.add("RYAN REYNOLDS");

        choices.add("LEGOLAS");
        choices.add("ISILDUR");
        choices.add("LOTHLORIEN");

        choices.add("SCAR");
        choices.add("JAFFAR");
        choices.add("MUFASA");

        choices.add("CHEESEBURGER");
        choices.add("PIZZA");
        choices.add("HOTDOGS");






        choices.add("OK");
        choices.add("OK");
        choices.add("OK");

        answers.add("1");
        answers.add("3");
        answers.add("2");

        answers.add("2");

        answers.add("3");

        answers.add("2");

        answers.add("3");
        answers.add("1");
        answers.add("3");

        answers.add("2");

        answers.add("OK");




        mAdapter = new MyAdapter(input,choices,answers);
        recyclerView.setAdapter(mAdapter);




    }



}