package com.example.codeintent;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.example.codeintent.App.CHANNEL_ID;

public class ExampleIntentService extends IntentService {

    private static final String TAG="ExampleIntentService";
    private PowerManager.WakeLock wakeLock;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * Must create a constructor.
     * name Used to name the worker thread, important only for debugging.
     */
//    public ExampleIntentService(String name) {
//        super(name);
//    }

    public ExampleIntentService() {
        //name of worker thread
        super("ExampleIntentService");
        //false is equivalent of START_NOT_STICKY
        //true is equivalent of START_REDELIVER_INTENT
        setIntentRedelivery(false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate()");

        //wake lock will keep CPU of phone running when screen is turned off
        PowerManager powerManager=(PowerManager)getSystemService(POWER_SERVICE);
        assert powerManager != null;
        wakeLock=powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "ExampleApp:WakeLock");
        //pass time needed for your work to be completed.
        wakeLock.acquire();
        Log.d(TAG,"WakeLock acquired");

        //Here creating notification in onCreate, we are not updating
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Example IntentService")
                    .setContentText("Running...")
                    .setSmallIcon(R.drawable.ic_android)
                    .build();

            startForeground(1, notification);
        }
    }

    //This is where we do our work on background thread.
    //Each incoming intent is handled sequentially on one single thread.
    //For multiple thread extend Service class
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG,"onHandleIntent");
        assert intent != null;
        String input= intent.getStringExtra("inputExtra");

        for(int i=0;i<10;i++)
        {
            Log.d(TAG,input+" - "+i);
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Inside onDestroy()");

        wakeLock.release();
        Log.d(TAG,"WakeLock release");
    }
}
