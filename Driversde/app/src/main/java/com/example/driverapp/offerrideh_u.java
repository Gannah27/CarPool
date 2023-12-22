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

public class offerrideh_u extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText Source,car_number;
    Spinner Destination;
    TextView Date_s;
    Button Ride_confirm;
    String Des_c;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offerrideh_u);

        Date_s =findViewById(R.id.textView07);
        Intent des=getIntent();
        String des_i=des.getStringExtra("Destination");

        Source=findViewById(R.id.editText08);

        Destination=findViewById(R.id.spinner02);
        String[] items = new String[]{"Gate 3", "Gate 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Destination.setAdapter(adapter);
        Destination.setOnItemSelectedListener(this);
        Ride_confirm=findViewById(R.id.button06);
        car_number=findViewById(R.id.editText09);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date dateOfTomorrow = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDateOfTomorrow = dateFormat.format(dateOfTomorrow);
        Date_s.setText(formattedDateOfTomorrow);

        Ride_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Date_t=Date_s.getText().toString();
                String Source_s=Source.getText().toString();
                String car_number_s= car_number.getText().toString();
                if(!(Source_s.isEmpty()&&car_number_s.isEmpty())) {
                    Intent i = new Intent(getApplicationContext(), Confirm_ride.class);
                    i.putExtra("Source", Source.getText().toString());
                    i.putExtra("Destination", Des_c);
                    i.putExtra("Date_s", Date_t);
                    i.putExtra("car_number", car_number.getText().toString());
                    i.putExtra("Destination_i",des_i);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(offerrideh_u.this, "Please fill all the spaces", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Des_c="Gate 3";
                break;
            case 1:
                Des_c="Gate 4";
                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Des_c="Gate 3";

    }
}
