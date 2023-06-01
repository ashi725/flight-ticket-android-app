package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.util.Country;
import com.example.myapplication.util.CountryAdaptor;
import com.example.myapplication.util.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    public static int byContinent = 0;
    public static String byContinentString;

    public static ArrayList<String> favouritesArray = new ArrayList<>(); // Initialize the favouritesArray
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite_activity);

        DataProvider dataProvider = new DataProvider(this, "data.json");
        List<Country> countryList = dataProvider.getCountry();

        List<Country> favouriteList = new ArrayList<>();

        for (Country country : countryList) {
            if (favouritesArray.contains(country.getName())) {
                favouriteList.add(country);
            }
        }
        countryList = favouriteList;

        CountryAdaptor countriesAdapter = new CountryAdaptor(this,
                R.layout.country_item, countryList);

        GridView gridView = (GridView) findViewById(R.id.countries);
        gridView.setAdapter((countriesAdapter));

        ImageView homeButton = (ImageView) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent homeIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(homeIntent);
            }
        });
    }

    public void onViewCountryDetails(Country country) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("country", (CharSequence) country);
        startActivity(intent);
    }

    public static void setFavouritesArray(String countryName){
        favouritesArray.add(countryName);
        return;
    }

    public static void clearFavouritesArray(){
        favouritesArray.clear();
        return;
    }
}