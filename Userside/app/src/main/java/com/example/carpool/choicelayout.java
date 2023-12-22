package com.example.carpool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class choicelayout extends AppCompatActivity {

        ImageButton bt_logout;
        Button btn_home, btn_uni,btn_cart,btn_history;
        Switch aSwitch;
        int res=0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.choicelayout);
            aSwitch=findViewById(R.id.switch01);
            bt_logout=findViewById(R.id.imageButton01);
            btn_home = findViewById(R.id.button08);
            btn_uni=findViewById(R.id.button07);
            btn_history=findViewById(R.id.button12);
            btn_history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(getApplicationContext(),history.class);
                    startActivity(i);
                }
            });
            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Handle the switch state change
                    if (isChecked) {
                        res=1;
                    } else {
                        res=0;
                    }
                }
            });
            bt_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            btn_cart=findViewById(R.id.button11);
            btn_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(),Cart.class);
                        startActivity(intent);


                }
            });
            btn_uni.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(res==1 || canReserveFor7_30()){
                        Intent intent = new Intent(getApplicationContext(),recycle_routes.class);
                        startActivity(intent);

                    }else {
                        Log.d("AG", "onClick: " + canReserveFor7_30());
                        Toast.makeText(choicelayout.this, "Sorry you missed booking ride to uni", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            btn_home.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(res==1 || canReserveFor5_30()){
                        Intent intent = new Intent(getApplicationContext(),recycle_routes2.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(choicelayout.this, "Sorry you missed booking time from uni", Toast.LENGTH_LONG).show();
                    }


                }
            });


        }
    private boolean canReserveFor7_30() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 0);

        Date limitTime = calendar.getTime();
        Date currentTime = new Date();
        Log.d("TAG", "canReserveFor7_30: " + limitTime);
        Log.d("TAG", "canReserveFor7_30: " + currentTime);

        return currentTime.before(limitTime);
    }

    private boolean canReserveFor5_30() {
        // Check if the current time is before 1:00 PM of the same day
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 0);

        Date limitTime = calendar.getTime();
        Date currentTime = new Date();

        return currentTime.before(limitTime);
    }
}