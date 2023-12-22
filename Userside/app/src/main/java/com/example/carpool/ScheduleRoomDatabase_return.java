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
public abstract class ScheduleRoomDatabase_return extends RoomDatabase {

    public abstract scheduleDao schedDao();
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);


    private static volatile ScheduleRoomDatabase_return instance;



    static  ScheduleRoomDatabase_return getDatabase( Context context) {
        if (instance == null) {
            Log.d("TAG", "getDatabase: heree");
            synchronized (ScheduleRoomDatabase_return.class) {
                if (instance == null) {
                    Log.d("TAG", "getDatabase: theree");
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    ScheduleRoomDatabase_return.class, "schedule_databaseR")
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

        }
    };
}
