package com.example.myapplication.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.App;
import com.example.myapplication.R;
import com.example.myapplication.util.Continent;
import com.example.myapplication.util.Country;
import com.example.myapplication.util.DataProvider;
import java.text.SimpleDateFormat;
import java.util.List;

public class SelectionActivity extends AppCompatActivity {
    private EditText departureDateInput;
    private EditText returnDateInput;
    private TextView countryTextView;
    private TextView departurePriceTextView;
    private TextView returnPriceTextView;
    private TextView totalPriceTextView;
    private DataProvider dataProvider;
    private Country country;

    private List<Continent> continents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        this.dataProvider = App.getDataProvider();

        countryTextView = findViewById(R.id.countryTextView);
        String countryName = getIntent().getStringExtra("countryName");
        countryTextView.setText(countryName + " Ticket Prices");

        departureDateInput = findViewById(R.id.departureDateInput);
        returnDateInput = findViewById(R.id.returnDateInput);
        departurePriceTextView = findViewById(R.id.departurePriceTextView);
        returnPriceTextView = findViewById(R.id.returnPriceTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        Button priceButton = findViewById(R.id.priceButton);

        // Set OnClickListener on the priceButton
        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTicketPrices();
            }
        });

        ImageView homeButton = (ImageView) findViewById(R.id.homeButton);
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

    private void updateTicketPrices() {
        String departureDateStr = departureDateInput.getText().toString();
        String returnDateStr = returnDateInput.getText().toString();
        String countryName = getIntent().getStringExtra("countryName");
        System.out.println(countryName);
    
        try {
            dataProvider = new DataProvider(this, "data.json");
            double departurePrice = dataProvider.getPrice(countryName, departureDateStr, "departure_ticket_prices");
            double returnPrice = dataProvider.getPrice(countryName, returnDateStr, "return_ticket_prices");
    
            double totalPrice = departurePrice + returnPrice;
    
            departurePriceTextView.setText("$" + String.format("%.2f", departurePrice));
            returnPriceTextView.setText("$" + String.format("%.2f", returnPrice));
            totalPriceTextView.setText("$" + String.format("%.2f", totalPrice));
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
