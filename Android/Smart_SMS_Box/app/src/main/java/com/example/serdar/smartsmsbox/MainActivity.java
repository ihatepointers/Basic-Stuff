package com.example.serdar.smartsmsbox;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static ArrayList<String> commercialSenders = new ArrayList<String>();
    public static ArrayList<String> names = new ArrayList<String>();
    public static ArrayList<String> commercialMessages = new ArrayList<String>();
    public static ArrayList<String> whiteListNumbers = new ArrayList<String>();
    public static ArrayList<String> whiteListNames = new ArrayList<String>();
    public static ArrayList<String> whiteListSenders = new ArrayList<String>();
    public static ArrayList<String> whiteListMessages = new ArrayList<String>();
    public static ArrayList<String> blackList = new ArrayList<String>();
    public static ArrayList<String> blackListSenders = new ArrayList<String>();
    public static ArrayList<String> blackListMessages = new ArrayList<String>();
    public static ArrayList<String> otpSenders = new ArrayList<String>();
    public static ArrayList<String> otpMessages = new ArrayList<String>();

    DatabaseHelper db;

    String msgData = "Messages";

    private Button messagesButton, blackListButton;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messagesButton = (Button) findViewById(R.id.messagesButton);
        blackListButton = (Button) findViewById(R.id.blackListButton);

        SmsReceiver mySmsReceiver = new SmsReceiver();

        context = getApplicationContext();

        blackList.add("DAP YAPl.");
        blackList.add("UCUZNET");
        blackList.add("hummel");
        blackList.add("0850");

        int GET_MY_PERMISSION = 1;
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_SMS)){
                /* do nothing*/
            }
            else{

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_SMS},GET_MY_PERMISSION);
            }
        }

        GET_MY_PERMISSION = -1;
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_CONTACTS)){
                /* do nothing*/
            }
            else{

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},GET_MY_PERMISSION);
            }
        }

        GET_MY_PERMISSION = -1;
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS)){
                /* do nothing*/
            }
            else{

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},GET_MY_PERMISSION);
            }
        }

        GET_MY_PERMISSION = -1;
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.RECEIVE_SMS)){
                /* do nothing*/
            }
            else{

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.RECEIVE_SMS},GET_MY_PERMISSION);
            }
        }

        /*

        recyclerView = (RecyclerView)findViewById(R.id.recycler);

        senders.add("Serdar Efe");
        messages.add("Merhaba");

        senders.add("5555");
        messages.add("Faturaniz 29.99");

        senders.add("850 254 5114");
        messages.add("Nijerya prensiyim. Paramı senin bankana göndermek istiyorum");

        senders.add("Serdar Efe");
        messages.add("Merhaba");

        senders.add("5555");
        messages.add("Faturaniz 29.99");

        senders.add("850 254 5114");
        messages.add("Nijerya prensiyim. Paramı senin bankana göndermek istiyorum");

        senders.add("Serdar Efe");
        messages.add("Merhaba");

        senders.add("5555");
        messages.add("Faturaniz 29.99");

        senders.add("850 254 5114");
        messages.add("Nijerya prensiyim. Paramı senin bankana göndermek istiyorum");

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(senders, messages);
        recyclerView.setAdapter(mAdapter);

        */

        /*
        sender message
        1 13

        for(int idx=0;idx<cursor.getColumnCount();idx++)
                //for(int idx=0;idx<10;idx++)
                {
                    //msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);

                }
         */

        ContentResolver resolver = getContentResolver();
        Cursor contactsCursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        while(contactsCursor.moveToNext()){
            String id = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String phoneNumber = "123";

            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

            while(phoneCursor.moveToNext()){
                phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

            if(!whiteListNumbers.contains(phoneNumber)){
                whiteListNumbers.add(phoneNumber);
                whiteListNames.add(name);
            }
        }

        int msgCounter = 0;

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        db = new DatabaseHelper(context);
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {


                if(containsCaseInsensitive(cursor.getString(2), whiteListNumbers)){
                    whiteListSenders.add(whiteListNames.get(whiteListNumbers.indexOf(cursor.getString(2))));
                    whiteListMessages.add(cursor.getString(13));
                    //db.insertMessage(cursor.getString(2), cursor.getString(13), "09.01.1997", Integer.parseInt(cursor.getString(0)), 0);
                }else if(containsCaseInsensitive(cursor.getString(2), blackList)){
                    blackListSenders.add(cursor.getString(2));
                    blackListMessages.add(cursor.getString(13));
                    //db.insertMessage(cursor.getString(2), cursor.getString(13), "09.01.1997", Integer.parseInt(cursor.getString(0)), 1);
                }else if(checkForOTP(cursor.getString(13))){
                    otpSenders.add(cursor.getString(2));
                    otpMessages.add(cursor.getString(13));
                    //db.insertMessage(cursor.getString(2), cursor.getString(13), "09.01.1997", Integer.parseInt(cursor.getString(0)), 2);
                }else{
                    commercialSenders.add(cursor.getString(2));
                    commercialMessages.add(cursor.getString(13));
                    //db.insertMessage(cursor.getString(2), cursor.getString(13), "09.01.1997", Integer.parseInt(cursor.getString(0)), 3);
                }




            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }

        db.close();
        /*
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                if(whiteListNumbers.contains(cursor.getString(2))){
                    whiteListSenders.add(whiteListNames.get(whiteListNumbers.indexOf(cursor.getString(2))));
                    whiteListMessages.add(cursor.getString(13));
                }else if(blackList.contains(cursor.getString(2))){
                    blackListSenders.add(cursor.getString(2));
                    blackListMessages.add(cursor.getString(13));
                }else{
                    senders.add(cursor.getString(2));
                    messages.add(cursor.getString(13));
                }


            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
        */

        //msgData = "Messages _id:616 thread_id:31 address:KIZILAY     person:null date:1527248903564 date_sent:1527248898000 sc_timestamp:null protocol:0 read:1 status:-1 type:1 reply_path_present:0 subject:null body:SN.BAGISCIMIZ,RAMAZAN AYINDA DUSEN KAN BAGISLARI NEDENIYLE DESTEGINIZ ICIN SIZI  RAMAZAN AYININ HER GUNU 19:30-01:00 ARASINDA ESENLER SENLIK ALANINA BEKLIYORUZ. B001 service_center:+905598008000 locked:0 sub_id:0 phone_id:-1 creator:null error_code:0 seen:1 priority:0 pri:-1 lgeMsgType:6 lgeSiid:null lgeCreated:null lgeExpires:null lgeReceived:null lgeAction:null lgeSec:null lgeMac:null lgeDoc:null doInstalled:0 lgePinRemainCnt:null index_on_icc:null service_msg_sender_address:null lgeCallbackNumber:null sms_imsi_data:null name:null tag:null tag_eng:null typeex:null group_id:1527248903652 ui_duplicate:0 dcs:-1 original_address: reply_address:null confirm_read:-1 extra_data:null simcopy:0 save_call_type:0 msg_boxtype:0 modified:1 modified_time:0 reply_option:0 sms_format:0 app_id:null cmd:null payload:null spam_report:0 reserve_time:0 chatting_read_reply:0 kpas_messageid:0 kpas_serialnumber:0 insert_time:1527248903564 textlink:-1 message_class:0 reference_number:-1 c0_iei:0";

        messagesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent welcomeIntent = new Intent(MainActivity.this, SMS.class);
                startActivity(welcomeIntent);
            }
        });

        blackListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent welcomeIntent = new Intent(MainActivity.this, BlackList.class);
                startActivity(welcomeIntent);
            }
        });

    }

    public boolean containsCaseInsensitive(String strToCompare, ArrayList<String>list)
    {
        for(String str:list)
        {
            if((str.toLowerCase().contains(strToCompare.toLowerCase())) || (str.contains(strToCompare)) || (strToCompare.contains(str)))
            {
                return(true);
            }
        }
        return(false);
    }

    public boolean checkForOTP(String msgBody){
        if ( msgBody.toLowerCase().contains("verification code") ||
                msgBody.toLowerCase().contains("doğrulama kodu") ||
                msgBody.toLowerCase().contains("confirmation code") ||
                msgBody.contains("PIN") || msgBody.toLowerCase().contains("sifre") ||
                msgBody.toLowerCase().contains("tek kullanımlık") || msgBody.toLowerCase().contains("onay kodu") ||
                msgBody.toLowerCase().contains("dogrulama kodu") || msgBody.toLowerCase().contains("tek kullanimlik"))
            return true;
        return false;
    }0


}
