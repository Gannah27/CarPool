package com.example.carpool;

import static com.example.carpool.ScheduleRoomDatabase.databaseWriteExecutor;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScheduleRepository {
    private scheduleDao mshceduledao;
    private scheduleDao mshceduledao_r;
    ValueEventListener eventListener;
    ValueEventListener eventListener_r;
    private LiveData<List<schedule>> getAllsched;
    private LiveData<List<schedule>> getAllsched_r;
    FirebaseDatabase dbf;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference_r;

    public ScheduleRepository(Application app) {
        Log.d("SET", "ScheduleRepository: here");
        ScheduleRoomDatabase db = ScheduleRoomDatabase.getDatabase(app);
        mshceduledao = db.schedDao();
        ScheduleRoomDatabase_return db_r=ScheduleRoomDatabase_return.getDatabase(app);
        mshceduledao_r = db_r.schedDao();
        Log.d("TAG", "ScheduleRepository: ooops");
        dbf = FirebaseDatabase.getInstance();
        databaseReference = dbf.getReference().child("DriversC");
        databaseReference_r = dbf.getReference().child("DriversH");
        getAllsched = mshceduledao.getAllschedule();
        getAllsched_r = mshceduledao_r.getAllschedule();
        this.updateschedule();


    }

    public void insert(schedule sched) {
        databaseWriteExecutor.execute(() -> {
            mshceduledao.insert(sched);
        });

    }

    public void delete(schedule sched) {
        databaseWriteExecutor.execute(() -> {
            mshceduledao.delete(sched);
        });

    }

    public void update(schedule sched) {
        databaseWriteExecutor.execute(() -> {
            mshceduledao.update(sched);
        });

    }

    public LiveData<List<schedule>> getGetAllsched() {
        return getAllsched;
    }
    public LiveData<List<schedule>> getGetAllsched_r() {
        return getAllsched_r;
    }

    public void deleteallwords() {
        databaseWriteExecutor.execute(() -> {
            mshceduledao.deleteAllsched();
        });

    }
    public void updateschedule() {
        Log.d("TAG", "updateschedule:hehee ");
        eventListener_r= databaseReference_r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                new UpdateScheduleTask_r().execute(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                new UpdateScheduleTask().execute(snapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
            }
        });
    }

    private class UpdateScheduleTask extends AsyncTask<DataSnapshot, Void, Void> {
        @Override
        protected Void doInBackground(DataSnapshot... snapshots) {
            List<schedule> firebaseData = new ArrayList<>();

            for (DataSnapshot itemSnapshot : snapshots[0].getChildren()) {
                Log.d("TAG", "updateschedule:thereee ");


                schedule dataClass = itemSnapshot.getValue(schedule.class);
                firebaseData.add(dataClass);


                mshceduledao.deleteAllsched();
                mshceduledao.insertAll(firebaseData);

            }

            getAllsched = mshceduledao.getAllschedule();


            return null;
        }
    }
    private class UpdateScheduleTask_r extends AsyncTask<DataSnapshot, Void, Void> {
        @Override
        protected Void doInBackground(DataSnapshot... snapshots) {
            List<schedule> firebaseData = new ArrayList<>();

            for (DataSnapshot itemSnapshot : snapshots[0].getChildren()) {
                Log.d("TAG", "updateschedule:thereee ");


                schedule dataClass = itemSnapshot.getValue(schedule.class);
                firebaseData.add(dataClass);


                mshceduledao_r.deleteAllsched();
                mshceduledao_r.insertAll(firebaseData);

            }

            getAllsched = mshceduledao.getAllschedule();


            return null;
        }
    }
}