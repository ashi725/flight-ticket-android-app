package com.example.myapplication.util;
import com.example.myapplication.activities.ListActivity;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.activities.ListActivity;
import com.example.myapplication.activities.MainActivity;

import java.util.List;

public class ContinentAdaptor extends ArrayAdapter {

    int mLayoutID;
    List<Continent> mContinents;
    Context mContext;

    public ContinentAdaptor(@NonNull Context context, int resource, @NonNull List<Continent> objects){
        super(context, resource, objects);
        mLayoutID = resource;
        mContext = context;
        mContinents = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View currentViewItem = convertView;

        if (currentViewItem == null){
            currentViewItem = LayoutInflater.from(getContext()).inflate(mLayoutID, parent, false);
        }

        Continent currentContinent = mContinents.get(position);

        ImageView iconImageView = (ImageView) currentViewItem.findViewById(R.id.continent_icon);
        int i = mContext.getResources().getIdentifier(
                currentContinent.getImage(), "drawable", mContext.getPackageName()
        );

        iconImageView.setImageResource(i);

        TextView continentName = (TextView)currentViewItem.findViewById(R.id.continent_name);
        continentName.setText((currentContinent.getName()));

        currentViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListActivity.byContinent = 1;
                ListActivity.byContinentString = currentContinent.getName();
                Intent listIntent = new Intent(mContext, ListActivity.class);
                mContext.startActivity(listIntent);
            }
        });

        return currentViewItem;
    }
}
