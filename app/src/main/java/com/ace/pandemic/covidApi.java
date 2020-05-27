package com.ace.pandemic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class covidApi
{
    public String totalCases="",newCases="",totalRecovered="",totalDeaths="",newRecovered="",newDeaths="";

    public covidApi() throws IOException, JSONException {

        OkHttpClient client=new OkHttpClient();
        Request request = new Request.Builder().url("https://disease.sh/v2/all").build();
        Response response = client.newCall(request).execute();

        String responseBody = "";
        responseBody = response.body().string();

        JSONObject object=new JSONObject(responseBody);
        //now we fetch the data from the ob j

        totalCases = object.getString("cases");
        newCases = object.getString("todayCases");
        totalRecovered = object.getString("recovered");
        totalDeaths = object.getString("deaths");
        newRecovered = object.getString("");
        newDeaths = object.getString("deaths");

    }
    static int getDeaths(String)

}
