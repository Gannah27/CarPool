package com.example.carpool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Payment extends AppCompatActivity {

    private ImageButton lastClickedButton;
    UserDb user_db;
    UserDao User_Dao;
    Button confirm;
    String payment;
    FirebaseAuth user_auth= FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user_db= UserDb.getDatabase(getApplicationContext());
        User_Dao=user_db.userDao();



        Intent info = getIntent();
        setContentView(R.layout.activity_payment);
        ImageButton card=findViewById(R.id.imageButton01);
        ImageButton cash=findViewById(R.id.imageButton02);
        confirm=findViewById(R.id.button06);
        String DropP = info.getStringExtra("getDropP");
        String getDateS = info.getStringExtra("getDate_s");
        String getPickP = info.getStringExtra("getPickP");
        String getCarnumber = info.getStringExtra("getCarnumber");
        String rider=info.getStringExtra("Rider");

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastClickedButton != null) {
                    lastClickedButton.setSelected(false);
                }

                // Add border to the clicked button
                v.setSelected(true);
                lastClickedButton = (ImageButton) v;
                payment="card";
            }
        });
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastClickedButton != null) {
                    lastClickedButton.setSelected(false);
                }

                // Add border to the clicked button
                v.setSelected(true);
                lastClickedButton = (ImageButton) v;
                payment="cash";

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(getApplicationContext(),Cart.class);
                User user=new User(getPickP,DropP,getCarnumber,getDateS,payment,user_auth.getCurrentUser().getUid(),rider);
                startActivity(cart);
                new UpdateScheduleTask().execute(user);
                finish();
            }
        });

    }
    private class UpdateScheduleTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User...user) {

            User_Dao.insert(user[0]);

            return null;
        }


    }

}