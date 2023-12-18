package com.example.driverapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class offerride extends AppCompatActivity {
    EditText Available_seat,Source,Destination,car_number;
    Button Ride_confirm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offerride);
        Available_seat=findViewById(R.id.editTextNumberDecimal01);
        Source=findViewById(R.id.editText07);
        Destination=findViewById(R.id.editText08);
        Ride_confirm=findViewById(R.id.button06);
        car_number=findViewById(R.id.editText09);
        Ride_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Confirm_ride.class);
                i.putExtra("Source",Source.getText().toString());
                i.putExtra("Destination",Destination.getText().toString());
                i.putExtra("Available_seat",Available_seat.getText().toString());
                i.putExtra("car_number",car_number.getText().toString());
                startActivity(i);
            }
        });

    }
}
