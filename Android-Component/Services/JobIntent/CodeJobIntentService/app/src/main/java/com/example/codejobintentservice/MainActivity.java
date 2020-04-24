package com.example.codejobintentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.edit_text_input);
    }

    public void enqueueWork(View view) {
        String input=ed.getText().toString();

        Intent serviceIntent=new Intent(this,ExampleJobIntentService.class);
        serviceIntent.putExtra("inputExtra",input);

        //we cant set constraints like Wifi here
        ExampleJobIntentService.enqueueWorK(this,serviceIntent);
    }
}
