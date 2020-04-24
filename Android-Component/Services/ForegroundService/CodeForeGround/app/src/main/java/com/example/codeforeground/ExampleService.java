package com.example.codeforeground;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.example.codeforeground.App.CHANNEL_ID;


public class ExampleService extends Service {

    private static final String TAG = "tag";

    //called first time we create service
    @Override
    public void onCreate() {
        Log.d(TAG,"Inside onCreate()");
        super.onCreate();
    }

    //called every time we start service
    //all work in onStart command is done on main thread by default
    //or use Intent Service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        Log.d(TAG,"Inside onStart()");

        //it is better to write it in onCreate method, but here we write in onStart
        //because we regularly update it from edit text
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        //do heavy work on a background thread
        //stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"Inside onDestroy()");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
