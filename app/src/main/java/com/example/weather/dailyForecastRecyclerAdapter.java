package com.example.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dailyForecastRecyclerAdapter extends RecyclerView.Adapter<dailyForecastRecyclerAdapter.dailyWeatherHolder> {
    ArrayList<hourlyWeather> weatherList=new ArrayList<>();
    private ArrayList<dailyWeather> dailyWeatherList =new ArrayList<>();

    public dailyForecastRecyclerAdapter(ArrayList<hourlyWeather> weatherList) {
        this.weatherList = weatherList;
        dailyWeather tempDaily;
//        String day=weatherList.get(0).dayOfWeek;
        try {
            tempDaily=new dailyWeather(weatherList.get(0).dayOfWeek,weatherList.get(0).numTempMin,weatherList.get(0).numTempMax);
            for(int i=1;i<weatherList.size();i++){
                if(tempDaily.day.compareTo(weatherList.get(i).dayOfWeek)==0){
                    if(weatherList.get(i).numTempMin<tempDaily.minTemp)
                        tempDaily.minTemp=weatherList.get(i).numTempMin;
                    if(weatherList.get(i).numTempMax>tempDaily.maxTemp)
                        tempDaily.maxTemp=weatherList.get(i).numTempMax;
                }
                else{
                    dailyWeatherList.add(new dailyWeather(tempDaily.day,tempDaily.minTemp,tempDaily.maxTemp));
                    tempDaily.day=weatherList.get(i).dayOfWeek;
                    tempDaily.minTemp=weatherList.get(i).numTempMin;
                    tempDaily.maxTemp=weatherList.get(i).numTempMax;
                    //i=i+2
                }
            }
            dailyWeatherList.add(new dailyWeather(tempDaily.day,tempDaily.minTemp,tempDaily.maxTemp));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @NonNull
    @Override
    public dailyWeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_forecast_item,parent
                ,false);
        dailyWeatherHolder holder =new dailyWeatherHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull dailyWeatherHolder holder, int position) {
        holder.txtday.setText(dailyWeatherList.get(position).day);
        holder.txtLowTemp.setText(dailyWeatherList.get(position).minTemp+"°");
        holder.txtHighTemp.setText(dailyWeatherList.get(position).maxTemp+"°");
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    class dailyWeatherHolder extends RecyclerView.ViewHolder{
        TextView txtday;
//        TextView txtcondition;
        TextView txtLowTemp;
        TextView txtHighTemp;
//        ImageView imgCondition;
        public dailyWeatherHolder(@NonNull View itemView) {
            super(itemView);
            txtday=itemView.findViewById(R.id.txtDay);
            txtLowTemp=itemView.findViewById(R.id.txtLowTemp);
            txtHighTemp=itemView.findViewById(R.id.txtHighTemp);
        }
    }
}
