package com.example.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class weatherHourlyRecyclerAdapter  extends RecyclerView.Adapter<weatherHourlyRecyclerAdapter.hourlyWheatherHolder> {
    ArrayList<hourlyWeather> list=new ArrayList<>();

    Map<String,Integer> map=new HashMap<>();

    public weatherHourlyRecyclerAdapter() {
        map.put("01d",R.drawable.a01d);
        map.put("02d",R.drawable.a02d);
        map.put("03d",R.drawable.a03d);
        map.put("04d",R.drawable.a04d);
        map.put("09d",R.drawable.a09d);
        map.put("10d",R.drawable.a10d);
        map.put("11d",R.drawable.a11d);
        map.put("13d",R.drawable.a13d);
        map.put("50d",R.drawable.a50d);
        map.put("01n",R.drawable.a01n);
        map.put("02n",R.drawable.a02n);
        map.put("03n",R.drawable.a03n);
        map.put("04n",R.drawable.a04n);
        map.put("09n",R.drawable.a09n);
        map.put("10n",R.drawable.a10n);
        map.put("11n",R.drawable.a11n);
        map.put("13n",R.drawable.a13n);
        map.put("50n",R.drawable.a50n);

    }

    @NonNull
    @Override
    public hourlyWheatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_forecast_item,parent,false);
        hourlyWheatherHolder holder =new hourlyWheatherHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull hourlyWheatherHolder holder, int position) {
        holder.txttime.setText(list.get(position).hour);
        holder.imgCondition.setImageResource(+map.get(list.get(position).imageURL));
        holder.txtTemp.setText(list.get(position).numTemp+"°");
        holder.txtcondition.setText(list.get(position).condition);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class hourlyWheatherHolder extends RecyclerView.ViewHolder {
         TextView txttime;
         TextView txtcondition;
         TextView txtTemp;

         ImageView imgCondition;
        public hourlyWheatherHolder(@NonNull View itemView) {
            super(itemView);
            txttime =itemView.findViewById(R.id.txtTime);
            txtcondition =itemView.findViewById(R.id.txtHourlyCondition);
            txtTemp =itemView.findViewById(R.id.txthourlyTemp);
            imgCondition =itemView.findViewById(R.id.imgHourlyCondition);

        }
    }

}
