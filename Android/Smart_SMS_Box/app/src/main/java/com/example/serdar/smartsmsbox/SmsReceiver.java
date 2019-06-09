package com.example.serdar.smartsmsbox;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class SmsReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsReceiver";

    private String smsSender, smsBody;
    private Context context;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            smsSender = "";
            smsBody = "";
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                smsBody += smsMessage.getMessageBody();
                smsSender += smsMessage.getOriginatingAddress();
            }
            createNotification();
            Toast.makeText(context, "Message received!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("5", "SMS Received", importance);
            channel.setDescription("SMS");
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "5")
                    .setSmallIcon(R.drawable.msgicon)
                    .setContentTitle(smsSender)
                    .setContentText(smsBody)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
            notificationManager.notify(125, mBuilder.build());
        }
    }
}
