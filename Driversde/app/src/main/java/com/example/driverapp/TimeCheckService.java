package com.example.driverapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class TimeCheckService extends Service {

    private DatabaseReference specificNodeRef;
    private DatabaseReference specificNodeRef2;
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        specificNodeRef2 = database.getReference().child("DriversC");
        specificNodeRef = database.getReference().child("DriversH");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Perform your time checks here
        checkAndRemoveNodeAt430AM();
        checkAndRemoveNodeAt1130PM();

        // The service will be restarted if it's killed by the system
        return START_STICKY;
    }

    private void checkAndRemoveNodeAt430AM() {
        Calendar currentTime = Calendar.getInstance();
        int hourOfDay = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        if (hourOfDay == 4 && minute == 30) {
            specificNodeRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial data and again
                    // whenever data at this location is updated.

                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        // Iterate through each child node
                        childSnapshot.child("confirm");
                        String childKey = childSnapshot.getKey();
                        Object childValue = childSnapshot.getValue();
                        if (childValue.toString().equals("Pending")){
                            DatabaseReference r =childSnapshot.getRef();
                            r.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                        }

                        // Do something with the child key and value
                        Log.d("FirebaseExample", "Child key: " + childKey + ", Value: " + childValue);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle errors
                    Log.e("FirebaseExample", "Error reading data: " + databaseError.getMessage());
                }
            });

        }
    }

    private void checkAndRemoveNodeAt1130PM() {
        Calendar currentTime = Calendar.getInstance();
        int hourOfDay = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        if (hourOfDay == 23 && minute == 30) {
            specificNodeRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial data and again
                    // whenever data at this location is updated.

                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        // Iterate through each child node
                        childSnapshot.child("confirm");
                        String childKey = childSnapshot.getKey();
                        Object childValue = childSnapshot.getValue();
                        if (childValue.toString().equals("Pending")){
                            DatabaseReference r =childSnapshot.getRef();
                            r.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                        }

                        // Do something with the child key and value
                        Log.d("FirebaseExample", "Child key: " + childKey + ", Value: " + childValue);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle errors
                    Log.e("FirebaseExample", "Error reading data: " + databaseError.getMessage());
                }
            });


        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
