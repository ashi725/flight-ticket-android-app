package com.example.myapplication.activities;

import com.example.myapplication.util.Continent;
import com.example.myapplication.util.ContinentAdaptor;
import com.example.myapplication.util.Country;

import com.example.myapplication.App;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;
import java.util.ArrayList;
import android.util.Log;


import com.example.myapplication.R;
import com.example.myapplication.util.CountryAdaptor;
import com.example.myapplication.util.DataProvider;
import com.example.myapplication.util.TopCountryAdaptor;

import kotlin.coroutines.Continuation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        DataProvider dataProvider = new DataProvider(this, "data.json");
        List<Country> countryList = dataProvider.getCountry();
        List<Country> topCountries = countryList.subList(0, Math.min(countryList.size(), 3));

        // Recycler View
        TopCountryAdaptor countriesAdapter = new TopCountryAdaptor(this, topCountries);

        RecyclerView recyclerView = findViewById(R.id.topCountries);
        recyclerView.setAdapter((countriesAdapter));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Continents list
        List<Continent> continentList = dataProvider.getContinents();
        ContinentAdaptor continentsAdapter = new ContinentAdaptor(this,
                R.layout.continent_item, continentList);

        ListView continentsView = (ListView) findViewById(R.id.continents);
        continentsView.setAdapter((continentsAdapter));


//        CardView oceaniaCardView = (CardView) findViewById(R.id.oceania);
//        oceaniaCardView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent listIntent = new Intent(getBaseContext(), ListActivity.class);
//                startActivity(listIntent);
//            }
//        });

    }

}