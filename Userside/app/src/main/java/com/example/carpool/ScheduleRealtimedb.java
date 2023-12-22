package com.example.carpool;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;

public class ScheduleRealtimedb {

        FirebaseDatabase fab;
        DatabaseReference databaseReference;
        ValueEventListener eventListener;
        ValueEventListener eventListener2;
        List<schedule> dataList;
         DatabaseReference databaseReference2;



         public List<schedule> updateschedule(){

            fab=FirebaseDatabase.getInstance();
            databaseReference = fab.getReference().child("DriversC");
            databaseReference2=fab.getReference().child("DriversH");
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                        schedule dataClass = itemSnapshot.getValue(schedule.class);
                        dataList.add(dataClass);

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
             eventListener2= databaseReference2.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {

                     for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                         schedule dataClass = itemSnapshot.getValue(schedule.class);
                         dataList.add(dataClass);

                     }


                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                 }
             });






            return dataList;

        }



}

