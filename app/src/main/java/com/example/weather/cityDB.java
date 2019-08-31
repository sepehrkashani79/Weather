package com.example.weather;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class cityDB extends SQLiteOpenHelper {
    String tableName="citytable";
    ArrayList<String> List = new ArrayList<String>();
    public cityDB(@Nullable Context context) {
        super(context, "helloDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String db_create_query="CREATE TABLE "+tableName+"("
                +"_id integer primary key autoincrement,"
                +"city text)";
        sqLiteDatabase.execSQL(db_create_query);
//        this.insert("Tehran");
        Log.d("myTag","database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insert(String cityName){
        String insert_query="INSERT INTO "+tableName+"(city)VALUES('"+cityName+"')";
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(insert_query);
        db.close();
        Log.d("sql",cityName+" added to the database");
    }
    public ArrayList<String> getAllcities() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT city from " + tableName, null);
        while (cursor.moveToNext()) {
            List.add(cursor.getString(0));
        }
        db.close();
        return List;
    }
    public void deleteCity(String cityName){
        SQLiteDatabase db = this.getWritableDatabase();
        String delete_query="DELETE FROM "+tableName+" WHERE city='"+cityName+"'";
        db.execSQL(delete_query);
        db.close();
    }

}
