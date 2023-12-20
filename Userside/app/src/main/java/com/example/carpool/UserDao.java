package com.example.carpool;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;



import java.util.List;

@Dao
public interface  UserDao {
    @Insert
    void insert(User user);
    @Update
    void update(User user);
    @Delete
    void delete(User user);
    @Query("Delete From user")
    void deleteAlluser();

    @Query("SELECT * FROM user WHERE currentUser = :valueToCheck")
    LiveData<List<User>> getAlltrips(String valueToCheck);
    @Query("SELECT COUNT(*) FROM user WHERE carnumber = :valueToCheck and PickP = :valueToCheck2")
    int countByValue(String valueToCheck, String valueToCheck2 );

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> entities);
}
