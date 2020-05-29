package com.ace.pandemic;

import android.content.Context;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class covidApi extends Thread
{
    public static String totalCases="",newCases="",totalRecovered="",totalDeaths="",newDeaths="";
    //textView Ref
    static TextView tDeath;

    private static String url="https://disease.sh/v2/all";

    covidApi(Context ct)
    {
        final StringRequest request = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("thiss",response);
                try
                {
                    //extracting data from JSON format
                    JSONObject object=new JSONObject(response);
                    totalCases = object.getString("cases");
                    newCases = object.getString("todayCases");
                    totalRecovered = object.getString("recovered");
                    totalDeaths = object.getString("deaths");
                    newDeaths = object.getString("todayDeaths");

                    //displaying data
                    tDeath.setText("Total Deaths:  "+totalDeaths);

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("thiss","failure to retrieve data");

            }
        });

        RequestQueue queue = Volley.newRequestQueue(ct);
        queue.add(request);
    }
    static void setPlainTextRef(TextView tDeath1)
    {
        tDeath=tDeath1;
    }

}
