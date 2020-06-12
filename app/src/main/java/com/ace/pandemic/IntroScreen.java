package com.ace.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IntroScreen extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        intent=getIntent();
        //getting message corresponding to key
        String message= intent.getStringExtra(MainActivity.key);

        TextView textViewI = findViewById(R.id.lulCases);

        covidApi.setIntroCases(textViewI,this);
    }
    public void getStarted(View view)
    {
        // baper kaaj shesh
       finish();
    }
}
