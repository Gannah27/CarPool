package com.example.carpool;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {schedule.class}, version = 1, exportSchema = false)
public abstract class ScheduleRoomDatabase extends RoomDatabase {

    public abstract scheduleDao schedDao();

    private static volatile ScheduleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ScheduleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ScheduleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ScheduleRoomDatabase.class, "schedule_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                scheduleDao dao = INSTANCE.schedDao();


                schedule sche = new schedule("4:30","Gate4","5:30","nasrcity");
                dao.insert(sche);
                sche = new schedule("4:30","Gate3","6:30","masrgdedida");
                dao.insert(sche);
            });
        }
    };
}