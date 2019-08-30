package com.example.weather;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class weatherService {
    ArrayList<hourlyWeather> fiveDay3HourForecast = new ArrayList<>();
    String adress;
    public boolean finishLoading=false;
//    Context LoadActivity=null;
    loadingActivity parent=null;

    public void getWheather() {
        finishLoading=false;
        fiveDay3HourForecast.clear();
        adress = "http://api.openweathermap.org/data/2.5/forecast?q=" + MainActivity.city + ",IR&appid=768f57d21d453201b41f97544565f56b";
        Log.d("myTag", adress);
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("-----------------------------------------");
        client.get(adress, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                fiveDay3HourForecast.clear();
                super.onSuccess(statusCode, headers, response);
                System.out.println("hellooooooooooooooooooooooooooooooooooo   :   " + statusCode);
                System.out.println(response.toString());
                try {
                    JSONObject all = new JSONObject(response.toString());
                    Log.d("myTag", "1");
                    JSONArray weatherlist = new JSONArray(all.getString("list"));
                    Log.d("myTag", "2");

                    for (int i = 0; i < weatherlist.length(); i++) {
                        JSONObject list = weatherlist.getJSONObject(i);
//                         dt , list
                        //list --> main , weather
                        //          ||       ||
                        //          \/       \/
                        //          temp    main
//                                          description
//                                          icon
                        Log.d("myTag", "3");
                        String time = list.getString("dt");
                        JSONObject main = new JSONObject(list.getString("main"));
                        JSONArray weather = new JSONArray(list.getString("weather"));
                        JSONObject trueweather = weather.getJSONObject(0);
                        String temp = main.getString("temp");
                        String mintemp = main.getString("temp_min");
                        String maxtemp = main.getString("temp_max");

                        String mainCondition = trueweather.getString("main");
                        //String description=weather.getString("description");
                        String icon = trueweather.getString("icon");
                        Log.d("weather", time + " : " + temp);
                        fiveDay3HourForecast.add(new hourlyWeather(time, mainCondition, temp, icon,mintemp, maxtemp));
                        Log.d("myTag", temp);
                        Log.d("myTag", "hello");

                        System.out.println("hello");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("mytag", "something went wrong in json system");
                    System.out.println("something went wrong in json system");
                }
                parent.finito();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                System.out.println("something went wrong in internet");
                Log.d("mytag", "something went wrong in internet");
            }

            @Override
            public void onFinish() {

                super.onFinish();
            }
        });

    }
}
