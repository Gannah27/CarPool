package com.example.driverapp;




import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Confirm_ride extends AppCompatActivity {
    TextView Source,Destination,Available_seat,car_number;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);
        Intent intent = getIntent();

        String getPickP = intent.getStringExtra("Source");
        String DropP = intent.getStringExtra("Destination");
        String carseat= intent.getStringExtra("Available_seat");
        String carnumber = intent.getStringExtra("car_number");
        Source=findViewById(R.id.textView10);
        Destination=findViewById(R.id.textView11);
        car_number=findViewById(R.id.textView12);
        Available_seat=findViewById(R.id.textView13);
        Source.setText(getResources().getString(R.string.SourceC)+" " + getPickP);
        Destination.setText(getResources().getString(R.string.DestinationC) +" " +DropP );
        car_number.setText(getResources().getString(R.string.Availability)+":" + carseat);
        Available_seat.setText(getResources().getString(R.string.Car_number)+":"+carnumber);




    }


}