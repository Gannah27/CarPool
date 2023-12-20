package com.example.carpool;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class confirmationPayment extends AppCompatActivity {
    TextView pickup,pickupT,dropoff,dropoffT;
    Button confirm ,cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_confirmation_payment);
        Intent intent = getIntent();

        String DropP = intent.getStringExtra("getDropP");
        String getDateS = intent.getStringExtra("getDate_s");
        String getPickP = intent.getStringExtra("getPickP");
        String getCarnumber = intent.getStringExtra("getCarnumber");
        pickup=findViewById(R.id.textView10);
        pickupT=findViewById(R.id.textView11);
        dropoff=findViewById(R.id.textView12);
        dropoffT=findViewById(R.id.textView13);
        confirm=findViewById(R.id.Button09);
        cancel=findViewById(R.id.Button10);
        pickup.setText(getResources().getString(R.string.SourceC)+" " + getPickP);
        pickupT.setText(getResources().getString(R.string.carnumberc) +" " + getCarnumber);
        dropoff.setText(getResources().getString(R.string.DestinationC)+" " + DropP);
        dropoffT.setText(getResources().getString(R.string.DateC)+" "+getDateS);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirm= new Intent(getApplicationContext(),Payment.class);
                confirm.putExtra("getDropP",DropP);
                confirm.putExtra("getDate_s",getDateS);
                confirm.putExtra("getPickP",getPickP);
                confirm.putExtra("getCarnumber",getCarnumber);
                startActivity(confirm);
                finish();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel= new Intent(getApplicationContext(),recycle_routes.class);
                startActivity(cancel);
                finish();


            }
        });




    }


}