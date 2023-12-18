package com.example.carpool;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class confirmationPayment extends AppCompatActivity {
    TextView pickup,pickupT,dropoff,dropoffT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_confirmation_payment);
        Intent intent = getIntent();

        String DropP = intent.getStringExtra("getDropP");
        String getDropT = intent.getStringExtra("getDropT");
        String getPickP = intent.getStringExtra("getPickP");
        String getPickT = intent.getStringExtra("getPickT");
        pickup=findViewById(R.id.textView10);
        pickupT=findViewById(R.id.textView11);
        dropoff=findViewById(R.id.textView12);
        dropoffT=findViewById(R.id.textView13);
        pickup.setText(getResources().getString(R.string.SourceC)+" " + getPickP);
        pickupT.setText(getResources().getString(R.string.pickupTC) +" " + getPickT);
        dropoff.setText(getResources().getString(R.string.DestinationC)+" " + DropP);
        dropoffT.setText(getResources().getString(R.string.DropOffTimeC)+" "+getDropT);




    }


}