package com.example.myapplication.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    private Context context;
    private String fileName;

    public DataProvider(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public List<Country> getCountry() {
        List<Country> countryList = new ArrayList<>();

        try {
            String jsonData = loadJSONFromAsset();
            JSONObject jsonRootObject = new JSONObject(jsonData);
            JSONArray continentsArray = jsonRootObject.getJSONArray("continents");

            for (int i = 0; i < continentsArray.length(); i++) {
                JSONObject continentObject = continentsArray.getJSONObject(i);
                String continentName = continentObject.getString("name");
                JSONArray countriesArray = continentObject.getJSONArray("countries");

                for (int j = 0; j < countriesArray.length(); j++) {
                    JSONObject countryObject = countriesArray.getJSONObject(j);
                    String name = countryObject.getString("name");
                    String image1 = countryObject.getString("image1");
                    String image2 = countryObject.getString("image2");
                    String image3 = countryObject.getString("image3");
                    String description = countryObject.getString("description");

                    Country country = new Country(name, image1, image2, image3, description, continentName);
                    countryList.add(country);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return countryList;
    }

    public List<Continent> getContinents() {
        List<Continent> continentList = new ArrayList<>();

        try {
            String jsonData = loadJSONFromAsset();
            JSONObject jsonRootObject = new JSONObject(jsonData);
            JSONArray continentsArray = jsonRootObject.getJSONArray("continents");

            for (int i = 0; i < continentsArray.length(); i++) {
                JSONObject continentObject = continentsArray.getJSONObject(i);
                String continentName = continentObject.getString("name");
                String continentImage = continentObject.getString("image");
                Continent continent = new Continent(continentName, continentImage);
                continentList.add(continent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return continentList;
    }


    private String loadJSONFromAsset() {
        String json = null;

        try {
            InputStream inputStream = context.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            json = stringBuilder.toString();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
