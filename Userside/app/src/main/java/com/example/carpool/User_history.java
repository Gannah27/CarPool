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

@Database(entities = {User.class}, version = 3)
public abstract class User_history extends RoomDatabase {

    public abstract UserDao userDao();
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);


    private static volatile User_history instance;

    static  User_history getDatabase( Context context) {
        if (instance == null) {
            Log.d("TAG", "getDatabase: User");
            synchronized (UserDb.class) {
                if (instance == null) {
                    Log.d("TAG", "getDatabase: User");
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    User_history.class, "user_history")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                    Log.d("User", instance.toString());
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
