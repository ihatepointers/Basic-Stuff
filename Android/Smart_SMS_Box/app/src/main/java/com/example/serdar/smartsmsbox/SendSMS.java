package com.example.serdar.smartsmsbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendSMS extends AppCompatActivity{
    private Button sendButton;
    private EditText phoneNumberEditText, msgBodyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        msgBodyEditText = (EditText)findViewById(R.id.msgBodyEditText);
        sendButton = (Button)findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sendNewSMS(phoneNumberEditText.getText().toString(), msgBodyEditText.getText().toString());
                Toast.makeText(SendSMS.this, "Message sent!",
                        Toast.LENGTH_SHORT).show();
                Intent welcomeIntent = new Intent(SendSMS.this, SMS.class);
                startActivity(welcomeIntent);
            }
        });

    }

    private void sendNewSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

}