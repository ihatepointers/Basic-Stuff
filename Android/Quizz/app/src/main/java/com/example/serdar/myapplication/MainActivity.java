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

public class MainActivity extends AppCompatActivity {

    int loginTryCounter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText etUsername = findViewById(R.id.etUsername);
        final EditText etPassword = findViewById(R.id.etPassword);
        final TextView tvFailedLogin = findViewById(R.id.tvFailedLogin);

        final Button bLogin = findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(etUsername.getText().toString().equals("admin") && etPassword.getText().toString().equals("password")){

                    Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent welcomeIntent = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(welcomeIntent);

                }else{

                    Toast.makeText(getApplicationContext(), "Wrong " + "" + "Credentials", Toast.LENGTH_SHORT).show();

                    tvFailedLogin.setVisibility(View.VISIBLE);
                    tvFailedLogin.setBackgroundColor(Color.RED);
                    loginTryCounter--;
                    tvFailedLogin.setText("Tries left: " + Integer.toString(loginTryCounter));

                    if (loginTryCounter == 0) {
                        tvFailedLogin.setEnabled(false);
                    }

                }
            }
        });
    }
}
