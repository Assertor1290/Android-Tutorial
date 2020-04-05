package com.example.activitytransition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG ="Main2Activity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG,"Inside onCreate");
        Toast.makeText(this,"onCreate2()",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Inside onStart");
        Toast.makeText(this,"onStart2()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"Inside onRestoreInstanceState");
        Toast.makeText(this,"onRestoreInstanceState2()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Inside onResume");
        Toast.makeText(this,"onResume2()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Inside onPause");
        Toast.makeText(this,"onPause2",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Inside onStop");
        Toast.makeText(this,"onStop()2",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"Inside onSaveInstanceState");
        Toast.makeText(this,"onSaveInstanceState()2",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Inside onRestart");
        Toast.makeText(this,"onRestart()2",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Inside onDestroy");
        Toast.makeText(this,"onDestroy()2",Toast.LENGTH_SHORT).show();
    }
}
