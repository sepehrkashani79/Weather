package com.example.weather;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cityRecyclerAdapter extends RecyclerView.Adapter<cityRecyclerAdapter.cityReyclerHolder> {
    ArrayList<String> cityList=new ArrayList<>();
    cityDB cityDB;

    public cityRecyclerAdapter(ArrayList<String> cityList, cityDB cityDB) {
        this.cityList = cityList;
        this.cityDB = cityDB;
    }

    @NonNull
    @Override
    public cityReyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item,parent,false);
        cityReyclerHolder holder =new cityReyclerHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final cityReyclerHolder holder, final int position) {
        holder.cityName.setText(cityList.get(position));
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityDB.deleteCity(holder.cityName.getText().toString() );
                cityList.remove(position);
//                notifyItemChanged(position);
                  refresh(position);
//                Log.d("sql","dataBase")
            }
        });
        holder.cityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.citySelectedByDrawer=holder.cityName.getText().toString();
//                MainActivity.citySelectedByDrawer.notify();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class cityReyclerHolder extends RecyclerView.ViewHolder{
        TextView cityName;
        ImageView btndelete;
        public cityReyclerHolder(@NonNull View itemView) {
            super(itemView);
            cityName=itemView.findViewById(R.id.txtCity);
            btndelete=itemView.findViewById(R.id.btnDelete);
        }
    }
    public void refresh(int position){
        notifyItemChanged(position);
    }
    public void fullRefresh(){
        notifyDataSetChanged();
    }
}
