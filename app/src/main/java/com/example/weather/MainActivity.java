//be name khoda
package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String city="Tehran";
    public static String citySelectedByDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView txtDegree=findViewById(R.id.txtDegree);
        TextView txtDegreeUnit=findViewById(R.id.txtDegreeUnit);

        TextView txtCurrentWeatherCondition=findViewById(R.id.txtCurrentWeatherCondition);
        final TextView txtLocation=findViewById(R.id.txtLocation);
        ImageView imgCurrentweatherCondition=findViewById(R.id.imgCurrentweatherCondition);

        hourlyWeather firstWeather=loadingActivity.w.fiveDay3HourForecast.get(0);
        txtDegree.setText(firstWeather.numTemp+"");
        txtCurrentWeatherCondition.setText(firstWeather.condition);



        final RecyclerView dailyForCastRecycler=findViewById(R.id.dailyForCastRecycler);
        final RecyclerView hourlyForCastRecycler=findViewById(R.id.hourlyForCastRecycler);
//        final weatherService weatherService=new weatherService();
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



        int t=hourlyAdapter.map.get(firstWeather.imageURL);
        imgCurrentweatherCondition.setImageResource(t);

        imgCurrentweatherCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //weatherService.getWheather();
                //hourlyAdapter.list=weatherService.fiveDay3HourForecast;


            }
        });
//        PreferenceManager.getDefaultSharedPreferences(this).edit().;
        //--------------Drawer part------------------------------------
        final EditText edtNewCity=findViewById(R.id.edtNewCityName);
        edtNewCity.setText(citySelectedByDrawer);
        Button btnAddCity=findViewById(R.id.btnAddCity);

        final RecyclerView cityRecycler=findViewById(R.id.cityRecycler);
        cityRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
        final cityDB db=new cityDB(MainActivity.this);
        final cityRecyclerAdapter cityAdapter=new cityRecyclerAdapter(db.getAllcities(),db);
        if(db.List.size()!=0){
            txtLocation.setText(db.List.get(db.List.size()-1));
        }
        else{
            txtLocation.setText("Tehran");
            db.insert("Tehran");
        }

        city=txtLocation.getText().toString();
        cityRecycler.setAdapter(cityAdapter);
        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean haveSame=false;
                for(int i=0;i<cityAdapter.cityList.size();i++){
                    if(cityAdapter.cityList.get(i).compareToIgnoreCase(edtNewCity.getText().toString())==0){
                        String t;
                        haveSame=true;
                        t=db.List.get(i);
                        db.List.remove(i);
                        db.deleteCity(t);
//                        db.List.add(t);
                        break;
                    }
                }

                db.insert(edtNewCity.getText().toString());
                cityAdapter.cityList.add(edtNewCity.getText().toString());
                cityAdapter.notifyDataSetChanged();


                city=edtNewCity.getText().toString();
//                txtLocation.setText(city);
                loadingActivity.w.getWheather();
                dailyAdapter.notifyDataSetChanged();
                hourlyAdapter.notifyDataSetChanged();
                txtLocation.setText(city);
//                txtLocation.setText(city);

            }
        });
        final Intent about=new Intent(MainActivity.this,AboutApp.class);
        Button btnAbout=findViewById(R.id.btnAboutApp);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(about);
            }
        });


    }
}
