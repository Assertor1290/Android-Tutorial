package com.example.savestate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    static final String NAME_TAG = "personName";
    static final String PHONE_TAG = "personNo";
    EditText edit1,edit2;
    String name,phoneno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(savedInstanceState!=null)
//        {
//            savedInstanceState.getString(NAME_TAG);
//            savedInstanceState.getString(PHONE_TAG);
//        }

        edit1=findViewById(R.id.editText);
        edit2=findViewById(R.id.editText2);

        if(edit1 != null && !TextUtils.isEmpty(edit1.getText())){
            name = edit1.getText().toString();
        }
        if(edit2 != null && !TextUtils.isEmpty(edit2.getText())){
            phoneno = edit2.getText().toString();
        }

        Log.d(TAG,"Inside onCreate");
        Toast.makeText(this,"onCreate()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Inside onStart");
        Toast.makeText(this,"onStart()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getString(PHONE_TAG);
        savedInstanceState.getString(NAME_TAG);


        Log.d(TAG,"Inside onRestoreInstanceState()");
        Toast.makeText(this,"onRestoreInstanceState()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Inside onResume");
        Toast.makeText(this,"onResume()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Inside onPause");
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState)
    {
        savedInstanceState.putString(NAME_TAG,name);
        savedInstanceState.putString(PHONE_TAG,phoneno);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"Inside onSaveInstanceState");
        Toast.makeText(this,"onSaveInstanceState()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Inside onStop");
        Toast.makeText(this,"onStop()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Inside onRestart");
        Toast.makeText(this,"onRestart()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Inside onDestroy");
        Toast.makeText(this,"onDestroy()",Toast.LENGTH_SHORT).show();
    }


}
