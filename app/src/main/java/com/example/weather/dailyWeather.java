package com.example.weather;

public class dailyWeather {
    String day;//SAT SUN TUE ...
    int minTemp;
    int maxTemp;

    public dailyWeather(String day, int minTemp, int maxTemp) {
        this.day = day;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
}
