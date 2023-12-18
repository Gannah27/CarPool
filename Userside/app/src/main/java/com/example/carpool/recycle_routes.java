package com.example.carpool;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class recycle_routes extends AppCompatActivity {
    private Scheduleviewmodel mschedviewmodel;
    RecyclerView recyclerview;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);
        recyclerview=findViewById(R.id.recycle01);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Log.d("SET", "CHECK 1 ");
        final Myadapter adapter = new Myadapter(new Myadapter.ItemClicklistener() {
            @Override
            public void onItemClick(schedule item) {

                Intent i = new Intent(getApplicationContext(),confirmationPayment.class);
                i.putExtra("getDropP",item.getDropP());
                i.putExtra("getDropT",item.getDropT());
                i.putExtra("getPickP",item.getPickP());
                i.putExtra("getPickT",item.getPickT());
                startActivity(i);
            }
        });
        recyclerview.setAdapter(adapter);

        mschedviewmodel = ViewModelProviders.of(this).get(Scheduleviewmodel.class);
        Log.d("SET", "CHECK 3 ");
        Log.d("SET", mschedviewmodel.getAllschedule().toString());
        mschedviewmodel.getAllschedule().observe(this, new Observer<List<schedule>>() {
            @Override
            public void onChanged(List<schedule> schedules) {
                Log.d("SET", "CHECK 4 ");
                Log.d("SET", schedules.toString());

                adapter.setMitemArrayList(schedules);
                Log.d("SET",schedules.toString());


            }
            }

          );


    }
}
