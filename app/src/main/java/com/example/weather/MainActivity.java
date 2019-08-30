//be name khoda
package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String city="Tehran";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView txtDegree=findViewById(R.id.txtDegree);
        TextView txtDegreeUnit=findViewById(R.id.txtDegreeUnit);

        TextView txtCurrentWeatherCondition=findViewById(R.id.txtCurrentWeatherCondition);
        TextView txtLocation=findViewById(R.id.txtLocation);
        ImageView imgCurrentweatherCondition=findViewById(R.id.imgCurrentweatherCondition);
        final RecyclerView dailyForCastRecycler=findViewById(R.id.dailyForCastRecycler);
        final RecyclerView hourlyForCastRecycler=findViewById(R.id.hourlyForCastRecycler);
        final weatherService weatherService=new weatherService();
        //weatherService.getWheather();
//-----------------------------------------------
        final weatherHourlyRecyclerAdapter hourlyAdapter=new weatherHourlyRecyclerAdapter();
        hourlyForCastRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));
        hourlyAdapter.list=loadingActivity.w.fiveDay3HourForecast;
        hourlyForCastRecycler.setAdapter(hourlyAdapter);
//-----------------------------------------------
        dailyForCastRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));
        hourlyForCastRecycler.setAdapter(hourlyAdapter);
        final dailyForecastRecyclerAdapter dailyAdapter=new dailyForecastRecyclerAdapter(loadingActivity.w.fiveDay3HourForecast);
        dailyForCastRecycler.setAdapter(dailyAdapter);

        imgCurrentweatherCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //weatherService.getWheather();
                //hourlyAdapter.list=weatherService.fiveDay3HourForecast;


            }
        });


    }
}
