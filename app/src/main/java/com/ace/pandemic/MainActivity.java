package com.ace.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView cases = null;
    covidApi api = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    try {
                        api = new covidApi();
                        Log.i("COVID","case no. "+covidApi.totalCases);
                    } catch (IOException e) {
                        Log.i("COVID","IOEXCEPTION");
                    } catch (JSONException e) {
                        Log.i("COVID","JSONEXCEPTION");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cases = findViewById(R.id.cases);
        cases.setText("Total Cases   "+covidApi.totalCases);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
