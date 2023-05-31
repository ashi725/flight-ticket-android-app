package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.util.Country;
import com.example.myapplication.util.CountryAdaptor;
import com.example.myapplication.util.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    public static int bySearch = 0;
    public static String searchString;

    private List<Country> countryList;
    private List<Country> filteredCountryList;
    private CountryAdaptor countriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        DataProvider dataProvider = new DataProvider(this, "data.json");
        countryList = dataProvider.getCountry();
        filteredCountryList = new ArrayList<>(countryList);

        countriesAdapter = new CountryAdaptor(this, R.layout.country_item, filteredCountryList);

        if (bySearch > 0) {
            filterCountries(searchString);
        }

        GridView gridView = findViewById(R.id.countries);
        gridView.setAdapter(countriesAdapter);

        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(listIntent);
            }
        });

        SearchView searchView = findViewById(R.id.searchActivityView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCountries(newText.toLowerCase(Locale.getDefault()));
                return true;
            }
        });
    }

    private void filterCountries(String filterText) {
        filteredCountryList.clear();

        if (filterText.isEmpty()) {
            filteredCountryList.addAll(countryList);
        } else {
            for (Country country : countryList) {
                if (country.getName().toLowerCase(Locale.getDefault()).contains(filterText)) {
                    filteredCountryList.add(country);
                }
            }
        }
        countriesAdapter.notifyDataSetChanged();
    }
}