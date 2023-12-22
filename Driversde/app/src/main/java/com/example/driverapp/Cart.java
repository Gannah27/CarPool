package com.example.driverapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity   {

    private PopupWindow popupWindow;
    RecyclerView recyclerView;
    List<Drivers> itemList;
    ItemAdapter adapter;
    FirebaseDatabase database;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference reference;
    DatabaseReference reference2;
    int res=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Switch mySwitch = findViewById(R.id.switch01);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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


        ImageButton hoverButton = findViewById(R.id.imageButton2);
        recyclerView = findViewById(R.id.recycle02);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        adapter = new ItemAdapter(new ItemAdapter.ItemClicklistener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(Drivers item) {
                LocalTime currentTime = LocalTime.now();
                LocalDate currentDate = LocalDate.now();
                LocalDate nextDay = currentDate.plus(1, ChronoUnit.DAYS);
                // Define registration deadlines
                LocalTime deadlineForMorningEvent = LocalTime.of(11, 30);
                LocalTime deadlineForEveningEvent = LocalTime.of(16, 30);

                // Define event times
                LocalTime morningEventTime = LocalTime.of(7, 30);
                LocalTime eveningEventTime = LocalTime.of(17, 30);

                    if(item.getPickP().equals("Gate 3")||item.getPickP().equals("Gate 4")){
                        Log.d("TAG", "onItemClick: reached here");

                        Log.d("TAG", "onItemClick: oops reached here");

                        if (currentTime.isBefore(deadlineForEveningEvent)|| res==1) {
                            // Check if it's before 5:30 PM the next day
                            if (currentTime.isBefore(eveningEventTime.minus(1, ChronoUnit.HOURS))|| res==1) {
                                showConfirmationDialog(reference.child(item.getFirebase())); //11:30pm
                            } else {
                                Toast.makeText(Cart.this, "You have missed time of confirmation", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(Cart.this, "You have missed time of confirmation", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if ((currentTime.isBefore(morningEventTime) && currentDate.isBefore(nextDay)) || res==1) {

                                showConfirmationDialog(reference2.child(item.getFirebase())); //4:30pm

                        } else {
                            Toast.makeText(Cart.this, "You have missed time of confirmation", Toast.LENGTH_SHORT).show();
                        }

                    }



            }
        });
        recyclerView.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("DriversC");
        reference2 = database.getReference().child("DriversH");
        reference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("TAG", "onChildAdded: here");
                Drivers item = snapshot.getValue(Drivers.class);
                if (item.getFirebase().equals(auth.getCurrentUser().getUid())) {
                    adapter.mitemArrayList.add(item);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Drivers item = snapshot.getValue(Drivers.class);
                if (item.getFirebase().equals(auth.getCurrentUser().getUid())) {
                    adapter.mitemArrayList.remove(item);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Drivers item = snapshot.getValue(Drivers.class);
                Log.d("TAG", "onChildAdded: here");
                if (item.getFirebase().equals(auth.getCurrentUser().getUid())) {
                    adapter.mitemArrayList.add(item);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);

        // Create the PopupWindow
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Set a transparent background
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);

        hoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v, getResources().getString(R.string.recycler_Cart));
            }
        });


    }
            private void showPopup(View anchorView, String text) {
                Log.d("TAG", "showPopup: ");

                TextView popupText = popupWindow.getContentView().findViewById(R.id.popupText);
                popupText.setText(text);


                int offset = -9;

                // Show the PopupWindow at the specified location
                popupWindow.showAsDropDown(anchorView, offset, -offset);
            }

            private void dismissPopup() {
                Log.d("TAG", "dismissPopup: ");
                // Dismiss the PopupWindow
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
    private void showConfirmationDialog(DatabaseReference item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to confirm?")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        updateDatabase(item);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
    private void updateDatabase(DatabaseReference item) {
        item.child("confirm").setValue("Confirm");
    }
}