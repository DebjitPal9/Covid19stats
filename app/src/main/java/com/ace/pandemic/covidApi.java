package com.ace.pandemic;

import android.content.Context;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.caching.FileCacher;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static android.widget.Toast.LENGTH_SHORT;


public class covidApi extends Thread
{
    public static String totalCases="",newCases="",totalRecovered="",totalDeaths="",newDeaths="";
    //textView Ref
    static TextView tDeath,nCases,nDeath,tRecover,tCases;

    private static String url="https://disease.sh/v2/all";

    covidApi(final Context ct, final FileCacher<String> stringCacher)//ct -> object of MainActivity(Context)
    {

        final StringRequest request = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try
                {
                    try {
                        //adding new cache
                        stringCacher.clearCache();
                        stringCacher.writeCache(response);
                }
                    catch (IOException exp)
                    {
                        Log.i("thiss","Error in cache writing");
                    }
                    //extracting data from JSON format
                    JSONObject object=new JSONObject(response);

                    totalCases = object.getString("cases");
                    newCases = object.getString("todayCases");
                    totalRecovered = object.getString("recovered");
                    totalDeaths = object.getString("deaths");
                    newDeaths = object.getString("todayDeaths");


                    //displaying data
                    tDeath.setText("Total Deaths : "+totalDeaths);

                    tRecover.setText("Total Recovered : "+totalRecovered);

                    nCases.setText("New Cases : "+newCases);

                    tCases.setText("Total Cases : "+totalCases);

                    nDeath.setText("New Deaths : "+newDeaths);


                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //No internet Toast mandatory
                Toast.makeText(ct,"No Internet Connection", LENGTH_SHORT).show();
                Log.i("thiss","reacing here");
                if(stringCacher.hasCache())
                {
                    //setting cache
                    try
                    {
                        String chacheResponse = stringCacher.readCache();

                        //extracting data from JSON format
                        JSONObject object=new JSONObject(chacheResponse);

                        totalCases = object.getString("cases");
                        newCases = object.getString("todayCases");
                        totalRecovered = object.getString("recovered");
                        totalDeaths = object.getString("deaths");
                        newDeaths = object.getString("todayDeaths");


                        //displaying data
                        tDeath.setText("Total Deaths : "+totalDeaths);

                        tRecover.setText("Total Recovered : "+totalRecovered);

                        nCases.setText("New Cases : "+newCases);

                        tCases.setText("Total Cases : "+totalCases);

                        nDeath.setText("New Deaths : "+newDeaths);


                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
                Log.i("thiss","failure to retrieve data covid api");

            }
        });

        RequestQueue queue = Volley.newRequestQueue(ct);
        queue.add(request);
    }
    static void setPlainTextRef(TextView tDeath1, TextView nCases1 , TextView nDeath1,TextView tRecover1, TextView tCases1)
    {
        tDeath = tDeath1;
        nCases =  nCases1;

        nDeath = nDeath1;
        tRecover = tRecover1;
        tCases = tCases1;

    }
    static void setIntroCases(final TextView textView,final Context ct)
    {
        final StringRequest request =new StringRequest(url,new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject object=new JSONObject(response);
                    //writing cache for introscreen


                    textView.setText("Total Cases : "+object.getString("cases"));
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.i("thiss","Failure to retrieve data for intro");
                Toast.makeText(ct,"No Internet Connection", LENGTH_SHORT).show();
                textView.setText("Total Cases : "+covidApi.totalCases);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(ct);
        queue.add(request);
    }
}
