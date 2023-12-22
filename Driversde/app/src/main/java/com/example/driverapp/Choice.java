package com.example.driverapp;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Choice extends AppCompatActivity {

    ImageButton bt_logout;
    Button btn_home, btn_uni,Mycart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);
        Mycart=findViewById(R.id.button14);
        bt_logout=findViewById(R.id.imageButton01);
        btn_home=findViewById(R.id.button08);
        btn_uni=findViewById(R.id.button12);
        Mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Cart.class);
                startActivity(i);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), offerideu_h.class);
                intent.putExtra("Destination","btn_home");
                startActivity(intent);
                Log.d("TAG", "onClick: reached intent");
            }
        });
        btn_uni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), offerrideh_u.class);
                intent.putExtra("Destination","btn_uni");
                startActivity(intent);
            }
        });
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),home_page.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
