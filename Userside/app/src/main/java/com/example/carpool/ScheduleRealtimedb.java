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

        List<schedule> dataList;



         public List<schedule> updateschedule(){

            fab=FirebaseDatabase.getInstance();
            databaseReference = fab.getReference("Drivers");
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



            return dataList;

        }

}

