package com.example.serdar.smartsmsbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SingleMessage extends AppCompatActivity {
    TextView senderText, messageText;
    String sender, message;
    EditText msgBodyEditText;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_message);

        senderText = (TextView)findViewById(R.id.senderText);
        messageText = (TextView)findViewById(R.id.messageText);
        msgBodyEditText = (EditText)findViewById(R.id.msgBodyEditText);
        sendButton = (Button)findViewById(R.id.sendButton);

        final Context messageContext = this;

        Bundle b = getIntent().getExtras();
        int position = -1; // or other values
        if(b != null){
            sender = (String) b.get("SENDER_STRING");
            message = (String) b.get("MESSAGE_STRING");
        }
        senderText.setText(sender);
        messageText.setText(message);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String phoneNumber = findByName(messageContext, senderText.getText().toString());
                sendSMS(phoneNumber, msgBodyEditText.getText().toString());
                Toast.makeText(SingleMessage.this, "Message sent!",
                        Toast.LENGTH_SHORT).show();
                Intent welcomeIntent = new Intent(SingleMessage.this, SMS.class);
                startActivity(welcomeIntent);
            }
        });

    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);

    }

    public String findByName(Context context , String name) {
        String result= null;
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" like'%" + name +"%'";
        String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, selection, null, null);
        if (c.moveToFirst()) {
            result= c.getString(0);
        }
        c.close();
        if(result==null)
            result= "This contact is not saved into your device";
        return result;
    }


}
