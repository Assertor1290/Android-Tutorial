package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"Inside onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Inside onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Inside onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Inside onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Inside onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Inside onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Inside onDestroy");
    }
}
