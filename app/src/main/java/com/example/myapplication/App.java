package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.util.DataProvider;

public class App {

    private Context context;
    private String fileName = "data.json";
    private static DataProvider dataProvider;

    public App(Context context) {
        this.context = context.getApplicationContext();
        dataProvider = new DataProvider(context, fileName);
    }

    public static DataProvider getDataProvider() {
        return dataProvider;
    }
}
