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
    }
    public void click(View view) throws IOException, JSONException {
        /*TextView tDeath1 =(TextView) findViewById(R.id.tDeath);
        TextView nDeath1 =(TextView) findViewById(R.id.nDeath);
        TextView tRecover1 =(TextView) findViewById(R.id.tRecover);
        TextView tCases1 =(TextView) findViewById(R.id.tCases);
        TextView nCases1 =(TextView) findViewById(R.id.nCases);*/

        covidApi temp=new covidApi();

        //tDeath1.setText(covidApi.totalDeaths);
        Log.i("this",covidApi.totalDeaths);
        /*nDeath1.setText(covidApi.newDeaths);
        tRecover1.setText(covidApi.totalRecovered);
        tCases1.setText(covidApi.totalCases);
        nCases1.setText(covidApi.newCases);*/
    }
}
