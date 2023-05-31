package com.example.myapplication.util;

import android.content.Context;

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

import java.util.List;

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
        }
        else {
            ImageView iconImageView = (ImageView) currentViewItem.findViewById(R.id.country_icon);
            int i = mContext.getResources().getIdentifier(
                    currentCountry.getImage3(), "drawable", mContext.getPackageName()
            );

            iconImageView.setImageResource(i);

            TextView countryName = (TextView)currentViewItem.findViewById(R.id.country_name);
            countryName.setText((currentCountry.getName()));
        }

        return currentViewItem;
    }
}
