package com.example.codebackground;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BackService extends Service {

    private static final String TAG = "tag";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Inside onCreate()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"Inside onBind()");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"Inside onStart()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"Inside onDestroy()");
        super.onDestroy();
    }
}
