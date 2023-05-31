package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.util.Country;
import com.example.myapplication.util.CountryAdaptor;
import com.example.myapplication.util.DataProvider;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        DataProvider dataProvider = new DataProvider(this, "data.json");
        List<Country> countryList = dataProvider.getCountry();

        CountryAdaptor countriesAdapter = new CountryAdaptor(this,
                R.layout.country_item, countryList);

        GridView gridView = (GridView) findViewById(R.id.countries);
        gridView.setAdapter((countriesAdapter));

        ImageView homeButton = (ImageView) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent listIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(listIntent);
            }
        });
    }

}