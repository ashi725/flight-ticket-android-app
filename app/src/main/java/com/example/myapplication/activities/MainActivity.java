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
    private SearchView searchView;
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

        ImageView searchButton = (ImageView) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent searchIntent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        ImageView favouriteButton = (ImageView) findViewById(R.id.favouriteButton);
        favouriteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent favouriteIntent = new Intent(getBaseContext(), FavouriteActivity.class);
                startActivity(favouriteIntent);
            }
        });

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.bySearch = 1;
                SearchActivity.searchString = query;
                Intent SearchIntent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(SearchIntent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

}