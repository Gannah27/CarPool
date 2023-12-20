package com.example.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class offerride extends AppCompatActivity {
    EditText Date,Source,Destination,car_number;
    Button Ride_confirm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offerride);
        Date=findViewById(R.id.editTextDate01);

        Source=findViewById(R.id.editText07);

        Destination=findViewById(R.id.editText08);

        Ride_confirm=findViewById(R.id.button06);
        car_number=findViewById(R.id.editText09);

        Ride_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Date_s=Date.getText().toString();
                String Source_s=Source.getText().toString();
                String Destination_s=Destination.getText().toString();
                String car_number_s= car_number.getText().toString();
                if(!(Date_s.isEmpty()&&Source_s.isEmpty()&&Destination_s.isEmpty()&&car_number_s.isEmpty())) {
                    Intent i = new Intent(getApplicationContext(), Confirm_ride.class);
                    i.putExtra("Source", Source.getText().toString());
                    i.putExtra("Destination", Destination.getText().toString());
                    i.putExtra("Date", Date.getText().toString());
                    i.putExtra("car_number", car_number.getText().toString());
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(offerride.this, "Please fill all the spaces", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
