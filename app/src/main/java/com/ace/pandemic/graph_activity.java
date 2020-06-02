package com.ace.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class graph_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_activity);
        //making an intent object
        Intent intent =getIntent();

        //getting message corresponding to key
        String message= intent.getStringExtra(MainActivity.key);

        TextView textV= findViewById(R.id.msg);

        textV.setText(message);

    }
}
