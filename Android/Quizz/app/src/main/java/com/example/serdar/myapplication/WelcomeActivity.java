package com.example.serdar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    private String name;
    private String age;
    private String placeOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final EditText etName = findViewById(R.id.etName);
        final EditText etAge = findViewById(R.id.etAge);
        final EditText etPlace = findViewById(R.id.etPlace);

        final Button bSave = findViewById(R.id.bSave);

        bSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*
                name=etName.getText().toString();
                age=etAge.getText().toString();
                placeOfBirth=etPlace.getText().toString();
                */

                Intent welcomeIntent = new Intent(WelcomeActivity.this, CategoryActivity.class);
                startActivity(welcomeIntent);

            }
        });



    }

}
