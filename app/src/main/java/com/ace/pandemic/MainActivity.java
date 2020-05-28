package com.ace.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        covidApi temp=new covidApi();
        try {
            temp.start();
            TextView tDeath2 = (TextView) findViewById(R.id.tDeath);
            tDeath2.setText(covidApi.totalDeaths);
            Log.i("thiss", covidApi.totalDeaths);
        }
        catch(Exception e)
        {
            Log.i("thiss",e.getMessage());
        }
    }
}
