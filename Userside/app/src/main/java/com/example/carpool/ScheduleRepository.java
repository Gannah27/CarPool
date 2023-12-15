package com.example.carpool;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScheduleRepository {
    private scheduleDao mshceduledao;
    private LiveData<List<schedule>> getAllsched;
    public ScheduleRepository(Application app ){
        ScheduleRoomDatabase db= ScheduleRoomDatabase.getDatabase(app);
        mshceduledao=db.schedDao();
        getAllsched=mshceduledao.getAllschedule();
    }
    public void insert(schedule sched){
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mshceduledao.insert(sched);
        });

    }
    public void delete(schedule sched){
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mshceduledao.delete(sched);
        });

    }
    public void update(schedule sched){
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mshceduledao.update(sched);
        });

    }

    public void deleteallwords(){
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mshceduledao.deleteAllsched();
        });

    }

    public LiveData<List<schedule>> getAllschedule() {
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() -> {
           getAllsched= mshceduledao.getAllschedule();
        });
        return getAllsched;
    }

}
