package com.example.codejobintentservice;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ExampleJobIntentService extends JobIntentService {

    private static final String TAG = "ExampleJobIntentService";

    //to start service
    static void enqueueWorK(Context context,Intent work)
    {
        enqueueWork(context,ExampleJobIntentService.class,123,work);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork: ");
        String input=intent.getStringExtra("inputExtra");

        for (int i = 0; i <10 ; i++) {
            Log.d(TAG,  input+" - "+i);

            if(isStopped()) return;
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    //This method will be triggered when job has been stopped,
    //example due to memory pressure
    //jobs have a time limit
    //boolean whether to resume job or not
    //default va;ue is true
    @Override
    public boolean onStopCurrentWork() {
        Log.d(TAG, "onStopCurrentWork: ");
        return super.onStopCurrentWork();
    }
}
