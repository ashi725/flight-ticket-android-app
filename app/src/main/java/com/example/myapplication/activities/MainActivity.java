package com.example.myapplication.controllers;

import com.example.myapplication.util.Country;

import com.example.myapplication.App;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;
import java.util.ArrayList;
import android.util.Log;


import com.example.myapplication.R;
import com.example.myapplication.util.CountryAdaptor;
import com.example.myapplication.util.DataProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        DataProvider dataProvider = new DataProvider(this, "data.json");
        List<Country> countryList = dataProvider.getCountry();

        CountryAdaptor countriesAdapter = new CountryAdaptor(this,
                R.layout.top_country_item, countryList);

        ListView listView = (ListView) findViewById(R.id.topCountries);
        listView.setAdapter((countriesAdapter));
    }

}