package com.ace.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kosalgeek.android.caching.FileCacher;

public class MainActivity extends AppCompatActivity {

    public static final String key="WHATGOESYOURBAPUS";

    //cacher object
    FileCacher<String> stringCacher=new FileCacher<>(MainActivity.this,"sometext.txt");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent intent =new Intent(this,IntroScreen.class);

        //setting main xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //making textView objects
        TextView tDeath2,nCases2,nDeath2,tRecover2, tCases2;
        tDeath2 = findViewById(R.id.tDeath);
        nCases2 = findViewById(R.id.nCases);
        nDeath2 = findViewById(R.id.nDeath);
        tRecover2 = findViewById(R.id.tRecover);
        tCases2 = findViewById(R.id.tCases);

        covidApi.setPlainTextRef(tDeath2,nCases2,nDeath2,tRecover2,tCases2);

        new covidApi(this,stringCacher);
        startActivity(intent);

    }
    public void  retryNet(View view)
    {
        new covidApi(this,stringCacher);
    }
    public void getGraph(View view)
    {

        //creating intent object
        Intent intent =new Intent(this,graph_activity.class);
        //message to be passed

        //My bapu doesn't know
        intent.putExtra(key,covidApi.totalCases);

        //starting new Activity
        startActivity(intent);
    }
}
