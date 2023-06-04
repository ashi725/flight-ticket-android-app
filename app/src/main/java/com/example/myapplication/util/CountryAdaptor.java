package com.example.myapplication.util;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.activities.DetailsActivity;
import com.example.myapplication.activities.ListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryAdaptor extends ArrayAdapter {

    int mLayoutID;
    List<Country> mCountries;
    Context mContext;

    public static int byContinent = 0;
    public static String byContinentString;

    public CountryAdaptor(@NonNull Context context, int resource, @NonNull List<Country> objects){
        super(context, resource, objects);
        mLayoutID = resource;
        mContext = context;
        mCountries = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View currentViewItem = convertView;

        if (currentViewItem == null){
            currentViewItem = LayoutInflater.from(getContext()).inflate(mLayoutID, parent, false);
        }

        Country currentCountry = mCountries.get(position);

        if (mLayoutID == R.layout.top_country_item){
            ImageView iconImageView = (ImageView) currentViewItem.findViewById(R.id.top_country_icon);
            int i = mContext.getResources().getIdentifier(
                    currentCountry.getImage3(), "drawable", mContext.getPackageName()
            );

            iconImageView.setImageResource(i);

            TextView countryName = (TextView)currentViewItem.findViewById(R.id.top_country_name);
            countryName.setText((currentCountry.getName()));

            currentViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailsActivity.byCountry = 1;
                    DetailsActivity.byCountryString = currentCountry.getName();
                    Intent detailsIntent = new Intent(mContext, DetailsActivity.class);
                    mContext.startActivity(detailsIntent);
                }
            });
        }
        else {
            ImageView iconImageView = (ImageView) currentViewItem.findViewById(R.id.country_icon);
            int i = mContext.getResources().getIdentifier(
                    currentCountry.getImage3(), "drawable", mContext.getPackageName()
            );

            iconImageView.setImageResource(i);

            TextView countryName = (TextView)currentViewItem.findViewById(R.id.country_name);
            countryName.setText((currentCountry.getName()));

            currentViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailsActivity.byCountry = 1;
                    DetailsActivity.byCountryString = currentCountry.getName();
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("countryName", currentCountry.getName());
                    intent.putExtra("countryImage", currentCountry.getImage3());
                    intent.putExtra("countryImage1", currentCountry.getImage1());
                    intent.putExtra("countryImage2", currentCountry.getImage2());
                    intent.putExtra("countryDescription", currentCountry.getDescription());
                    mContext.startActivity(intent);
                }
            });
        }

        return currentViewItem;
    }
}
