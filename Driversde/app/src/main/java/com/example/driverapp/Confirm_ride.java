package com.example.driverapp;




import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Confirm_ride extends AppCompatActivity {
    TextView Source,Destination,Date,car_number;
    Button confirm, cancel;
    FirebaseAuth fa = FirebaseAuth.getInstance();
    FirebaseUser fa_u;
    FirebaseDatabase dbF;
    DatabaseReference reference;
    Drivers driver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);
        Intent intent = getIntent();
        Intent c= new Intent(getApplicationContext(), Cart.class);
        fa_u=fa.getCurrentUser();
        String id = fa_u.getUid();

        String getPickP = intent.getStringExtra("Source");
        String DropP = intent.getStringExtra("Destination");
        String Date_s= intent.getStringExtra("Date_s");
        String carnumber = intent.getStringExtra("car_number");
        String Des= intent.getStringExtra("Destination_i");
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
        driver=new Drivers(getPickP,DropP,Date_s,carnumber,id,"Pending");
        Log.d("TAG", "onCreate: "+driver.getFirebase());
        Log.d("TAG", "onCreate: "+driver.getPickP() +driver.getDropP() +Date_s +driver.getFirebase() + driver.getConfirm());
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbF= FirebaseDatabase.getInstance();
                if(Des.equals("btn_uni")){
                    Log.e("FirebaseError", "HEREEE");
                    reference=dbF.getReference().child("DriversC"); //home to college

                }else{
                    reference=dbF.getReference().child("DriversH");//college to home
                }

                reference.child(id).setValue(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e("FirebaseError", "entered");
                        Toast.makeText(Confirm_ride.this, "Confirmed ride", Toast.LENGTH_SHORT).show();

                        Exception e = task.getException();
                        if (e != null) {
                            Log.e("FirebaseError", "Error writing to Firebase", e);
                        }
                    }

                });

                startActivity(c);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(getApplicationContext(), offerrideh_u.class);
                startActivity(cancel);
                finish();
            }
        });


    }


}