package com.example.myapplication.activities;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.denzcoskun.imageslider.ImageSlider;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class DetailsActivity extends AppCompatActivity {

    public static int byCountry = 0;
    public static String byCountryString;
    public static String countryName;

    boolean isFavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        // Retrieve the passed country details
        Intent intent = getIntent();
        countryName = intent.getStringExtra("countryName");
        String countryImage = intent.getStringExtra("countryImage");
        String countryImage1 = intent.getStringExtra("countryImage1");
        String countryImage2 = intent.getStringExtra("countryImage2");
        String countryDescription = intent.getStringExtra("countryDescription");

        ImageView heartButton = (ImageView) findViewById(R.id.heartIcon);

        // See if favourited
        ArrayList<String> favouritesArray = FavouriteActivity.getFavouritesArray();
        if (favouritesArray.contains(countryName)){
            isFavourite = true;
            heartButton.setImageResource(R.drawable.heart);
        }

        // Update the views with the country details
        ImageView countryImageView = findViewById(R.id.detailsIcon);
        TextView countryTitle = findViewById(R.id.countryTitle);
        TextView whyCountry = findViewById(R.id.whyCountry);
        TextView description = findViewById(R.id.description);

        // Set country image
        int imageResId;
        if (countryImage != null) {
            imageResId = getResources().getIdentifier(countryImage, "drawable", getPackageName());
        } else {
            imageResId= getResources().getIdentifier("japan3", "drawable", getPackageName());
        }
        if (imageResId == 0){
            imageResId = getResources().getIdentifier("japan3", "drawable", getPackageName());
        }
        countryImageView.setImageResource(imageResId);
        Drawable drawableIcon = ContextCompat.getDrawable(this, imageResId);

        int firstSlide;
        if (countryImage1 != null) {
            firstSlide = getResources().getIdentifier(countryImage1, "drawable", getPackageName());
        } else {
            firstSlide = getResources().getIdentifier("japan1", "drawable", getPackageName());
        }
        if (firstSlide == 0){
            firstSlide = getResources().getIdentifier("japan1", "drawable", getPackageName());
        }
        Drawable drawable1 = ContextCompat.getDrawable(this, firstSlide);

        // Get country image 2
        int secondSlide;
        if (countryImage2 != null) {
            secondSlide = getResources().getIdentifier(countryImage2, "drawable", getPackageName());
        } else {
            secondSlide = getResources().getIdentifier("japan2", "drawable", getPackageName());
        }
        if (secondSlide == 0){
            secondSlide = getResources().getIdentifier("japan2", "drawable", getPackageName());
        }
        Drawable drawable2 = ContextCompat.getDrawable(this, secondSlide);

        // Set image slider
        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(firstSlide, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(secondSlide, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(imageResId, ScaleTypes.CENTER_CROP));

        // Set country title and description
        countryTitle.setText(countryName);
        whyCountry.setText("Why " + countryName + "?");
        description.setText(countryDescription);

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);

        imageSlider.stopSliding();

        Button checkPricesButton = findViewById(R.id.pricesButton);
        checkPricesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckPrices();
            }
        });

        heartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (isFavourite) {
                    heartButton.setImageResource(R.drawable.heart_outline);
                    FavouriteActivity.removeFavouritesArray(countryName);
                    isFavourite = false;
                }
                else{
                    heartButton.setImageResource(R.drawable.heart);
                    FavouriteActivity.setFavouritesArray(countryName);
                    isFavourite = true;
                }
            }
        });

        ImageView homeButton = (ImageView) findViewById(R.id.homeDetails);
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent homeIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(homeIntent);
            }
        });

        ImageView searchButton = (ImageView) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent searchIntent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        ImageView favouriteButton = (ImageView) findViewById(R.id.favouriteButton);
        favouriteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent favouriteIntent = new Intent(getBaseContext(), FavouriteActivity.class);
                startActivity(favouriteIntent);
            }
        });
    }

    public void onCheckPrices() {
        Intent intent = new Intent(DetailsActivity.this, SelectionActivity.class);
        intent.putExtra("countryName", countryName);
        startActivity(intent);
    }
}