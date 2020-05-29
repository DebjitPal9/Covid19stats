package com.ace.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static String url="https://disease.sh/v2/all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tDeath2 = (TextView) findViewById(R.id.textView2);

        covidApi.setPlainTextRef(tDeath2);

        covidApi obj=new covidApi(this);
    }
}
