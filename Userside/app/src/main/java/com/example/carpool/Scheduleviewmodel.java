package com.example.carpool;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;
import java.util.List;

public class Scheduleviewmodel extends ViewModel {
    private ScheduleRepository mschedrepo;
    private LiveData<List<schedule>> mAllsched;

    public Scheduleviewmodel(Application application) {
        mschedrepo=new ScheduleRepository(application);
        mAllsched=mschedrepo.getAllschedule();

    }
    public void insert(schedule sched){
        mschedrepo.insert(sched);

    }
    public void delete(schedule sched){
       mschedrepo.delete(sched);

    }
    public void update(schedule sched){
       mschedrepo.update(sched);

    }

    public void deleteallwords(){
       mschedrepo.deleteallwords();

    }

    public LiveData<List<schedule>> getAllschedule() {

        return mAllsched;
    }
}
