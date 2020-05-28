package com.ace.pandemic;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class covidApi extends Thread
{
    public static String totalCases="",newCases="",totalRecovered="",totalDeaths="",newDeaths="";

       public void run()
       {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://disease.sh/v2/all").build();
        try {
            Response response = client.newCall(request).execute();

            String responseBody = "";
            responseBody = response.body().string();

            JSONObject object = new JSONObject(responseBody);
            //now we fetch the data from the ob j

            totalCases = object.getString("cases");
            newCases = object.getString("todayCases");
            totalRecovered = object.getString("recovered");
            totalDeaths = object.getString("deaths");
            newDeaths = object.getString("todayDeaths");
        }
        catch(IOException e)
        {
            Log.i("this","IOException");
        }
        catch (JSONException e)
        {
            Log.i("this","JSONException");
        }
        catch (Exception e)
        {
            Log.i("thiss","Exception");
            Log.i("thiss",covidApi.totalDeaths);
        }
    }

}
