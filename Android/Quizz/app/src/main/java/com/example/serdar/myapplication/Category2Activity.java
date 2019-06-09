package com.example.serdar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Category2Activity extends AppCompatActivity {

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


        input.add("Q.1 How long does it take for light from the Sun to reach Earth?");
        input.add("Q.2 Which kind of waves are used to make and receive cellphone calls?");
        input.add("Q.3 Which planet has the most moons?");

        input.add("Q.4 What does a light-year measure?");

        input.add("Q.5 Which of these elements is needed to make nuclear energy and nuclear weapons? ");

        input.add("Q.6 Which of these people developed the polio vaccine?");

        input.add("Q.7 NaCl is the chemical formula for which common household chemical?");
        input.add("Q.8 What is the name of the fourth planet from the Sun?");
        input.add("Q.9 What do you call the red pigment found in vertebrates that functions in oxygen transport?");

        input.add("Q.10 How many legs do spiders have?");

        input.add("End of Quiz!");


        choices.add("1 SECOND");
        choices.add("8 MINUTES 20 SECONDS");
        choices.add("1 MINUTE 34 SECONDS");

        choices.add("RADIO WAVES");
        choices.add("SOUND WAVES");
        choices.add("GRAVITY WAVES");

        choices.add("EARTH");
        choices.add("JUPITER");
        choices.add("MARS");

        choices.add("BRIGHTNESS");
        choices.add("TIME");
        choices.add("DISTANCE");

        choices.add("SODIUM CHLORIDE");
        choices.add("URANIUM");
        choices.add("NITROGEN");

        choices.add("MARIE CURIE");
        choices.add("ISAAC NEWTON");
        choices.add("JONAS SALK");

        choices.add("SODIUM CHLORIDE(TABLE SALT)");
        choices.add("HYDROGEN PEROXIDE");
        choices.add("AMMONIA");

        choices.add("MERCURY");
        choices.add("MARS");
        choices.add("EARTH");

        choices.add("MELANIN");
        choices.add("MELATONIN");
        choices.add("HEMOGLOBIN");

        choices.add("4");
        choices.add("6");
        choices.add("8");






        choices.add("OK");
        choices.add("OK");
        choices.add("OK");

        answers.add("2");
        answers.add("1");
        answers.add("2");

        answers.add("3");

        answers.add("2");

        answers.add("3");

        answers.add("1");
        answers.add("2");
        answers.add("3");

        answers.add("3");

        answers.add("OK");




        mAdapter = new MyAdapter(input,choices,answers);
        recyclerView.setAdapter(mAdapter);




    }



}