package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.util.Country;
import com.example.myapplication.util.CountryAdaptor;
import com.example.myapplication.util.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static int byContinent = 0;
    public static String byContinentString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        DataProvider dataProvider = new DataProvider(this, "data.json");
        List<Country> countryList = dataProvider.getCountry();

        if (byContinent > 0){
            TextView continentTitle = (TextView)findViewById(R.id.continent_title);
            continentTitle.setText(byContinentString);
            List<Country> sublist = new ArrayList<>();

            for (Country country : countryList) {
                if (country.getContinentName().equals(byContinentString)) {
                    sublist.add(country);
                }
            }
            countryList = sublist;
        }

        CountryAdaptor countriesAdapter = new CountryAdaptor(this,
                R.layout.country_item, countryList);

        GridView gridView = (GridView) findViewById(R.id.countries);
        gridView.setAdapter((countriesAdapter));

        ImageView homeButton = (ImageView) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent homeIntent = new Intent(getBaseContext(), MainActivity.class);
                byContinent = 0;
                startActivity(homeIntent);
            }
        });
    }

    public void onViewCountryDetails(Country country) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("country", (CharSequence) country);
        startActivity(intent);
    }

}