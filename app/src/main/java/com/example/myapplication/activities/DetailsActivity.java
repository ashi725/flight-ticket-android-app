package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.util.Country;

public class DetailsActivity extends AppCompatActivity {

    public static int byCountry = 0;
    public static String byCountryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        // Retrieve the passed country details
        Intent intent = getIntent();
        String countryName = intent.getStringExtra("countryName");
        String countryImage = intent.getStringExtra("countryImage");
        String countryDescription = intent.getStringExtra("countryDescription");

        // Update the views with the country details
        ImageView countryImageView = findViewById(R.id.detailsIcon);
        TextView countryTitle = findViewById(R.id.countryTitle);
        TextView whyCountry = findViewById(R.id.whyCountry);
        TextView description = findViewById(R.id.description);

        // Set country image
        int imageResId = getResources().getIdentifier(countryImage, "drawable", getPackageName());
        countryImageView.setImageResource(imageResId);

        // Set country title and description
        countryTitle.setText(countryName);
        whyCountry.setText("Why " + countryName + "?");
        description.setText(countryDescription);

        Button checkPricesButton = findViewById(R.id.pricesButton);
        checkPricesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckPrices();
            }
        });
    }

    public void onCheckPrices() {
        startActivity(new Intent(DetailsActivity.this, SelectionActivity.class));
    }


}