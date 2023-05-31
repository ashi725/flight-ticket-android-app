package com.example.myapplication.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.App;
import com.example.myapplication.R;
import com.example.myapplication.util.Continents;
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

    private List<Continents> continents;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        this.dataProvider = App.getDataProvider();

        departureDateInput = findViewById(R.id.departureDate);
        returnDateInput = findViewById(R.id.returnDate);
        departurePriceTextView = findViewById(R.id.departurePriceTextView);
        returnPriceTextView = findViewById(R.id.returnPriceTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

    }

    private void updateTicketPrices() {
        String departureDateStr = departureDateInput.getText().toString();
        String returnDateStr = returnDateInput.getText().toString();

        try {
            // Implement proper dates later
            //Date departureDate = dateFormat.parse(departureDateStr);
            //Date returnDate = dateFormat.parse(returnDateStr);

            double departurePrice = dataProvider.getPrice(country,"day1");
            double returnPrice = dataProvider.getPrice(country,"day2");
            double totalPrice = departurePrice + returnPrice;

            departurePriceTextView.setText(String.valueOf(departurePrice));
            returnPriceTextView.setText(String.valueOf(returnPrice));
            totalPriceTextView.setText(String.valueOf(totalPrice));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
