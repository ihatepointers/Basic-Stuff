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



public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        final Button bCategory1 = findViewById(R.id.bCategory1);
        final Button bCategory2 = findViewById(R.id.bCategory2);
        final Button bCategory3 = findViewById(R.id.bCategory3);
        final Button bCategory4 = findViewById(R.id.bCategory4);


        bCategory1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent welcomeIntent = new Intent(CategoryActivity.this, Category1Activity.class);
                startActivity(welcomeIntent);
            }
        });

        bCategory2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent welcomeIntent = new Intent(CategoryActivity.this, Category2Activity.class);
                startActivity(welcomeIntent);
            }
        });

        bCategory3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent welcomeIntent = new Intent(CategoryActivity.this, Category3Activity.class);
                startActivity(welcomeIntent);
            }
        });

        bCategory4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent welcomeIntent = new Intent(CategoryActivity.this, Category4Activity.class);
                startActivity(welcomeIntent);
            }
        });




    }
}
