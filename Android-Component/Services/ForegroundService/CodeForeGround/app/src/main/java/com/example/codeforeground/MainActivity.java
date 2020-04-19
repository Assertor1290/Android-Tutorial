package com.example.codeforeground;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

//From Android Oreo( API 26) you can no longer keep a background service running when your app is
//in background. Two alternatives- Foreground Services and JobScheduler
public class MainActivity extends AppCompatActivity {
    private EditText editTextInput;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent= new Intent(this, ExampleService.class);
        editTextInput = findViewById(R.id.edit_text_input);
    }

    public void startService(View v) {
        String input = editTextInput.getText().toString();
        serviceIntent.putExtra("inputExtra", input);

        //startService(serviceIntent)
        //works while app is still open

        //works even when app is in background
        //after calling this method, you have 5 seconds to call startForeground otherwise
        //you will get ANR. Therefore call startForeground in onCreate or use Job Scheduler(min A
        //Api 21
        ContextCompat.startForegroundService(this, serviceIntent);
        //requires api 26. To check that ContextCompat is used
    }

    public void stopService(View v) {

        stopService(serviceIntent);
    }
}
