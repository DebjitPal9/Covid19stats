package com.ace.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static String url="https://disease.sh/v2/all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //making textView objects
        TextView tDeath2,nCases2,nDeath2,tRecover2, tCases2;
        tDeath2 = (TextView) findViewById(R.id.tDeath);
        nCases2 = (TextView) findViewById(R.id.nCases);
        nDeath2 = (TextView) findViewById(R.id.nDeath);
        tRecover2 = (TextView) findViewById(R.id.tRecover);
        tCases2 = (TextView) findViewById(R.id.tCases);

        covidApi.setPlainTextRef(tDeath2,nCases2,nDeath2,tRecover2,tCases2);

        covidApi obj=new covidApi(this);
    }
}
