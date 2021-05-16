package com.example.smartgarden.TRACKING;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.core.app.NotificationCompat;

import com.example.smartgarden.FBDB.SQLiteDB;
import com.example.smartgarden.MODELS.SinglePTrack;
import com.example.smartgarden.MODELS.Step;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;
import com.example.smartgarden.TRACKERS.Tracker;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class AlarmReceiver extends BroadcastReceiver {
    SQLiteDB sqLiteDB;
    List<SinglePTrack> list;


    @Override
    public void onReceive(Context context, Intent intent) {

        sqLiteDB = new SQLiteDB(context);
        list = sqLiteDB.getLastEntryofAll();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(context));
        SharedPreferences.Editor sharedPrefEditor = prefs.edit();

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, MainDashboard.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingI = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "Daily Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Daily Notification");
            if (nm != null) {
                nm.createNotificationChannel(channel);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String s1 = "", title = "", msg = "";
            s1 = String.valueOf("Tomatoe plant: " + list.get(i).getPtid());
            List<Step> stepList = new Tracker().getTomatoStepslist();
            for (int j = 0; j < stepList.size(); j++){
                if (stepList.get(j).getKey().equals(list.get(i).getCompletedstep())){
                    msg = stepList.get(j+1).getTitle();
                }
            }

            NotificationCompat.Builder b = new NotificationCompat.Builder(context, "default");
            b.setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setTicker("{Notification")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(s1)
                    .setContentText(msg)
                    .setContentInfo("")
                    .setContentIntent(pendingI);

            if (nm != null) {
                nm.notify(1, b.build());
                Calendar nextNotifyTime = Calendar.getInstance();
                nextNotifyTime.add(Calendar.DATE, 1);
                sharedPrefEditor.putLong("nextNotifyTime", nextNotifyTime.getTimeInMillis());
                sharedPrefEditor.apply();
            }
        }



    }
}