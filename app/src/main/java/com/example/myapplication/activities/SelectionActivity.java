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
    }

    private void updateTicketPrices() {
        String departureDateStr = departureDateInput.getText().toString();
        String returnDateStr = returnDateInput.getText().toString();

        try {
            //dataProvider = new DataProvider(this, "data.json");
            //double departurePrice = dataProvider.getPrice("Egypt", departureDateStr);
            //double returnPrice = dataProvider.getPrice("Egypt", returnDateStr);

            double departurePrice = 80.00;
            double returnPrice = 120.00;

            if (departureDateStr.equals("01-01-23")) {
                departurePrice = 100.00;
            } else {
                departurePrice = 90.00;
            }

            if (returnDateStr.equals("02-02-23")) {
                returnPrice = 80.00;
            } else {
                returnPrice = 120.00;
            }

            double totalPrice = departurePrice + returnPrice;

            departurePriceTextView.setText("$" + String.valueOf(departurePrice) + "0");
            returnPriceTextView.setText("$" + String.valueOf(returnPrice)+ "0");
            totalPriceTextView.setText("$" + String.valueOf(totalPrice)+ "0");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
