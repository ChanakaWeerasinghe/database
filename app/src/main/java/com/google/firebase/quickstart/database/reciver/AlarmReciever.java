package com.google.firebase.quickstart.database.reciver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.quickstart.database.MainActivity;
import com.google.firebase.quickstart.database.R;

public class AlarmReciever extends BroadcastReceiver
{
    int no=0;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO Auto-generated method stub


        // here you can start an activity or service depending on your need
        // for ex you can start an activity to vibrate phone or to ring the phone

//        String phoneNumberReciver="0770888767";// phone number to which SMS to be send
        String message="Upcoming Todo's";// message to send
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
        // Show the toast  like in above screen shot
        Toast.makeText(context, "Alarm Triggered new TODO", Toast.LENGTH_LONG).show();
        displayNotification(context,message,no);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void displayNotification(Context context,String mess,int no) {
        Log.i("Start", "notification");

        /* Invoking the default notification service */
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(context);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("You've received new message.");
        mBuilder.setTicker(mess);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);

        /* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(++no);

        /* Add Big View Specific Configuration */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        events[0] = new String(mess);


        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("ToDo");

        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        mBuilder.setStyle(inboxStyle);

        /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);

        /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify((int) Math.random(), mBuilder.build());
    }

}


