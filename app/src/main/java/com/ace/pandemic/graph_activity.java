package com.ace.pandemic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.Edits;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineRadarDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kosalgeek.android.caching.FileCacher;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class graph_activity extends AppCompatActivity {
    private static  String url="https://disease.sh/v2/historical/India?lastdays=5";

    BarChart barChart ;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    FileCacher<String> fileCacher=new FileCacher<>(graph_activity.this,"sometext2.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_activity);
        //making an intent object
        Intent intent =getIntent();

        barChart=findViewById(R.id.barChartt);

        //to get and display the data
        getAndSetEntries(this);


        //getting message corresponding to key
        //String message= intent.getStringExtra(MainActivity.key);

    }
    public void getAndSetEntries(final Context ct)
    {
        final StringRequest request =new StringRequest(url,new com.android.volley.Response.Listener<String>(){
            @Override
            public void onResponse(String response)
            {
                //stores entries
                barEntries = new ArrayList<>();

                try
                {
                    JSONObject jsonObject =new JSONObject(response);
                    String timeline = jsonObject.getString("timeline");

                    jsonObject=new JSONObject(timeline);
                    String cases= jsonObject.getString("cases");

                    Log.i("thiss",cases);

                    //writing chache hehe
                    try{
                        fileCacher.clearCache();
                        fileCacher.writeCache(cases);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //storing dates in iterator from case JSONFormat
                    jsonObject=new JSONObject(cases);
                    Iterator iterator = jsonObject.keys();

                    float x=1;

                    String[] dateArr= new String[6];
                    int pos=0;
                    //to fix x axis mismatch
                    dateArr[pos++]="temp";

                    while(iterator.hasNext())
                    {
                        String dates = iterator.next().toString();
                        dateArr[pos++]=dates;

                        int dateCase =Integer.parseInt(jsonObject.getString(dates));

                        barEntries.add(new BarEntry(x++,dateCase));

                    }
                    //storing  in a form of set
                    barDataSet = new BarDataSet(barEntries,"Data Set");
                    // storing in form of bardata
                    barData = new BarData(barDataSet);
                    //setting data in chart
                    barChart.setData(barData);

                    XAxis xAxis = barChart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(dateArr));

                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1f);
                    xAxis.setGranularityEnabled(true);

                    //for barDataSet color

                    /*int endColor = ContextCompat.getColor(ct, android.R.color.holo_purple);
                    int startColor = ContextCompat.getColor(ct, android.R.color.holo_red_light);

                    barDataSet.setGradientColor(startColor,endColor);*/
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                    //for barDataSet font
                    barDataSet.setValueTextColor(Color.BLACK);

                    barDataSet.setValueTextSize(12f);
                    //important
                    barChart.invalidate();


                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.i("thiss","JSON EXCEPTION");
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(ct,"No Internet Connection", LENGTH_SHORT).show();
                //VVVERY IMPORTANT
                barEntries = new ArrayList<>();

                if(fileCacher.hasCache())
                {
                    try {
                        Log.i("thiss","Gactivity");
                        Log.i("thiss",fileCacher.readCache());
                        String cases = "";
                        cases = fileCacher.readCache();


                        JSONObject jsonObject = new JSONObject(cases);
                        ;

                        Iterator iterator = jsonObject.keys();

                        float x = 1;

                        String[] dateArr = new String[6];
                        int pos = 0;
                        //to fix x axis mismatch
                        dateArr[pos++] = "temp";

                            while (iterator.hasNext())
                            {
                                String dates = iterator.next().toString();
                                dateArr[pos++] = dates;

                                int dateCase = Integer.parseInt(jsonObject.getString(dates));
                                barEntries.add(new BarEntry(x++, dateCase));

                            }

                        //storing  in a form of set
                        barDataSet = new BarDataSet(barEntries, "Data Set");
                        // storing in form of bardata
                        barData = new BarData(barDataSet);
                        //setting data in chart
                        barChart.setData(barData);

                        XAxis xAxis = barChart.getXAxis();
                        xAxis.setValueFormatter(new IndexAxisValueFormatter(dateArr));

                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xAxis.setGranularity(1f);
                        xAxis.setGranularityEnabled(true);

                        //for barDataSet color

                    /*int endColor = ContextCompat.getColor(ct, android.R.color.holo_purple);
                    int startColor = ContextCompat.getColor(ct, android.R.color.holo_red_light);

                    barDataSet.setGradientColor(startColor,endColor);*/
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                        //for barDataSet font
                        barDataSet.setValueTextColor(Color.BLACK);

                        barDataSet.setValueTextSize(12f);
                        //important
                        barChart.invalidate();


                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch(JSONException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    public void refresh(View view)
    {
        getAndSetEntries(this);
    }

}
