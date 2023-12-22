package com.example.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class offerideu_h extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText Destination,car_number;
    String Source;
    TextView Date_t;
    Button Ride_confirm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offerrideu_h);
        Spinner dropdown = findViewById(R.id.spinner01);
        String[] items = new String[]{"Gate 3", "Gate 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        Destination=findViewById(R.id.editText10);
        car_number=findViewById(R.id.editText12);
        Date_t=findViewById(R.id.textView20);
        Ride_confirm=findViewById(R.id.button12);

        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);


        // Calculate the date of tomorrow if before 12 midnight
        if (currentHour < 24) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        Date dateToShow = calendar.getTime();

        // Format the dates as strings
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDateToShow = dateFormat.format(dateToShow);

        // Update the TextView with the appropriate date

        Date_t.setText(formattedDateToShow);
        Ride_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Destination_s=Destination.getText().toString();
                String car_number_s= car_number.getText().toString();
                if(!(Destination_s.isEmpty()&&car_number_s.isEmpty())) {
                    Intent i = new Intent(getApplicationContext(), Confirm_ride.class);
                    i.putExtra("Source", Source);
                    i.putExtra("Destination", Destination_s);
                    i.putExtra("Date_s", Date_t.getText().toString());
                    i.putExtra("car_number",car_number_s);
                    i.putExtra("Destination_i","btn_home");
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(offerideu_h.this,"Please fill all the spaces", Toast.LENGTH_LONG).show();
                }
            }
        });









    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Source="Gate 3";
                break;
            case 1:
               Source="Gate 4";
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Source="Gate 3";
    }
}
