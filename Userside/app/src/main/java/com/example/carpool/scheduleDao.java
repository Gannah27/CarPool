package com.example.carpool;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface scheduleDao {
    @Insert
    void insert(schedule sched);
    @Update
    void update(schedule sched);
    @Delete
    void delete(schedule sched);
    @Query("Delete From schedule")
    void deleteAllsched();

    @Query("SELECT * FROM schedule")
    LiveData<List<schedule>>getAllschedule();
}
