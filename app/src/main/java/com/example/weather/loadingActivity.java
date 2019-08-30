package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class loadingActivity extends AppCompatActivity {
    Intent finishLoading;
    static final weatherService w=new weatherService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ImageView gif=findViewById(R.id.loadGif);
//        Picasso.get().load(R.drawable.loader_xxxix).into(gif);
        Glide.with(loadingActivity.this).load(R.drawable.loader_xxxix).into(gif);

        finishLoading =new Intent(loadingActivity.this,MainActivity.class);
//        w.LoadActivity=getApplicationContext();
        w.parent=loadingActivity.this;
        w.getWheather();
//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(w.finishLoading==false);
//                startActivity(finishLoading);
//            }
//        })

    }
    public void finito(){
        System.out.println("FINITOOODODODOODODOODO----------------------");
        startActivity(finishLoading);
    }

}
