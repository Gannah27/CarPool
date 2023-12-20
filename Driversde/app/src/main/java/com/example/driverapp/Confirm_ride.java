package com.example.driverapp;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Confirm_ride extends AppCompatActivity {
    TextView Source,Destination,Date,car_number;
    Button confirm, cancel;
    FirebaseDatabase dbF;
    DatabaseReference reference;
    Drivers driver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);
        Intent intent = getIntent();


        String getPickP = intent.getStringExtra("Source");
        String DropP = intent.getStringExtra("Destination");
        String Date_s= intent.getStringExtra("Date");
        String carnumber = intent.getStringExtra("car_number");
        Source=findViewById(R.id.textView10);
        Destination=findViewById(R.id.textView11);
        car_number=findViewById(R.id.textView12);
        Date=findViewById(R.id.textView13);
        confirm=findViewById(R.id.Button09);
        cancel=findViewById(R.id.Button10);
        Source.setText(getResources().getString(R.string.SourceC)+" " + getPickP);
        Destination.setText(getResources().getString(R.string.DestinationC) +" " +DropP );
        Date.setText(getResources().getString(R.string.Date)+":" + Date_s);
        car_number.setText(getResources().getString(R.string.Car_number)+":"+carnumber);
        driver=new Drivers(getPickP,DropP,Date_s,carnumber);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbF= FirebaseDatabase.getInstance();
                reference=dbF.getReference("Drivers");
                reference.child(carnumber).setValue(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Confirm_ride.this, "Confirmed ride", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(getApplicationContext(), offerride.class);
                startActivity(cancel);
                finish();
            }
        });


    }


}