package com.example.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class hourlyWeather {
    String time;
    String condition;
//    --------------------------------
    private double doubleTemp;
    private double doubleTempMin;
    private double doubleTempMax;
    int numTemp;
    int numTempMin;
    int numTempMax;
//    --------------------------------
    private String temp;
    private String tempMin;
    private String tempMax;
//  ----------------------------------
    String imageURL;
    String dayOfWeek;
    String hour;


    public hourlyWeather( String time, String condition ,String temp,String imageURL,String tempMin,String tempMax) {
        System.out.println("new hourly weather added");
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;

        this.time = time;
        this.condition = condition;
        this.imageURL=imageURL;


        doubleTemp = Double.valueOf(temp)-273;
        numTemp= ((int) doubleTemp);

        doubleTempMin = Double.valueOf(tempMin)-273;
        numTempMin= ((int) doubleTempMin);

        doubleTempMax = Double.valueOf(tempMax)-273;
        numTempMax= ((int) doubleTempMax);



        long unix_seconds = Integer.parseInt(time);
        //convert seconds to milliseconds
        Date date = new Date(unix_seconds*1000L);
        // format of the date
        SimpleDateFormat jdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat jdf2 = new SimpleDateFormat("E");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT+3:30"));
        jdf2.setTimeZone(TimeZone.getTimeZone("GMT+3:30"));
        hour = jdf.format(date);
        dayOfWeek = jdf2.format(date);
    }
}
