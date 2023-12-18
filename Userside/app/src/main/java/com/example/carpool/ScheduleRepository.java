package com.example.carpool;

import static com.example.carpool.ScheduleRoomDatabase.databaseWriteExecutor;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScheduleRepository {
    private scheduleDao mshceduledao;
    private LiveData<List<schedule>> getAllsched;
    public ScheduleRepository(Application app ){
        Log.d("SET", "ScheduleRepository: here");
        ScheduleRoomDatabase db= ScheduleRoomDatabase.getDatabase(app);
        mshceduledao=db.schedDao();
        getAllsched=mshceduledao.getAllschedule();
    }
    public void insert(schedule sched){
        databaseWriteExecutor.execute(() -> {
            mshceduledao.insert(sched);
        });

    }
    public void delete(schedule sched){
        databaseWriteExecutor.execute(() -> {
            mshceduledao.delete(sched);
        });

    }
    public void update(schedule sched){
        databaseWriteExecutor.execute(() -> {
            mshceduledao.update(sched);
        });

    }

    public void deleteallwords(){
        databaseWriteExecutor.execute(() -> {
            mshceduledao.deleteAllsched();
        });

    }

    public LiveData<List<schedule>> getallschedule() {
        return getAllsched;
    }

}
