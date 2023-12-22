package com.example.carpool;

import android.app.Application;
import android.util.Log;

import androidx.annotation.LongDef;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class Scheduleviewmodel extends AndroidViewModel {
    private ScheduleRepository mschedrepo;
    private LiveData<List<schedule>> mAllsched;
    private LiveData<List<schedule>> mAllsched_r;

    public Scheduleviewmodel(Application application) {
        super(application);
        Log.d("SET", "Scheduleviewmodel: 5 ");
        mschedrepo= new ScheduleRepository(application);
        mAllsched=mschedrepo.getGetAllsched();
        mAllsched_r=mschedrepo.getGetAllsched_r();
        Log.d("SET", mAllsched.toString());

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
    public LiveData<List<schedule>> getAllschedule_r() {

        return mAllsched_r;
    }
}
