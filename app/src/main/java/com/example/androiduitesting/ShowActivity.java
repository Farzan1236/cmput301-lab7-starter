package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);

        TextView cityNameTextView = findViewById(R.id.text_city_name);
        Button backButton = findViewById(R.id.button_back);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(MainActivity.Extra_City_Name)) {
            String cityName = intent.getStringExtra(MainActivity.Extra_City_Name);

            cityNameTextView.setText(cityName);
        }else{
            cityNameTextView.setText("No City Selected");
        }

        backButton.setOnClickListener(v-> {
            finish();
        });
    }
}
