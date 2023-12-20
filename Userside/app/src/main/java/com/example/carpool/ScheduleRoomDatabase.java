package com.example.carpool;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {schedule.class}, version = 2)
public abstract class ScheduleRoomDatabase extends RoomDatabase {

    public abstract scheduleDao schedDao();
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);


    private static volatile ScheduleRoomDatabase instance;



    static  ScheduleRoomDatabase getDatabase( Context context) {
        if (instance == null) {
            Log.d("TAG", "getDatabase: heree");
            synchronized (ScheduleRoomDatabase.class) {
                if (instance == null) {
                    Log.d("TAG", "getDatabase: theree");
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    ScheduleRoomDatabase.class, "schedule_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                    Log.d("SETDB", instance.toString());
                }
            }
        }
        return instance;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("SET", "DBCALLBACK");
            // If you want to keep data through app restarts,
            // comment out the following block
           /* databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                scheduleDao dao = instance.schedDao();


                schedule sche = new schedule("4:30","Gate4","5:30","nasrcity");
                dao.insert(sche);
                sche = new schedule("4:30","Gate3","6:30","masrgdedida");
                dao.insert(sche);
            });*/
        }
    };
}