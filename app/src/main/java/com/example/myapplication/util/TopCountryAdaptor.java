package com.example.myapplication.util;
import com.example.myapplication.activities.DetailsActivity;


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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.myapplication.R;
import com.example.myapplication.activities.ListActivity;

import java.util.List;

public class TopCountryAdaptor extends RecyclerView.Adapter<TopCountryAdaptor.ViewHolder> {

    int mLayoutID;
    List<Country> mCountryList;
    Context mContext;

    public TopCountryAdaptor(@NonNull Context context, @NonNull List<Country> objects){
        mContext = context;
        mCountryList = objects;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView countryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.top_country_icon);
            countryName = itemView.findViewById(R.id.top_country_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_country_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DetailsIntent = new Intent(mContext, DetailsActivity.class);
                mContext.startActivity(DetailsIntent);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country currentCountry = mCountryList.get(position);

        int i = holder.itemView.getContext().getResources().getIdentifier(
                currentCountry.getImage3(), "drawable", holder.itemView.getContext().getPackageName());

        holder.iconImageView.setImageResource(i);
        holder.countryName.setText(currentCountry.getName());
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }
}
