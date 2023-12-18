package com.example.carpool;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "schedule")
public class schedule {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String DropT;
    private String DropP;
    private String PickT;
    private String PickP;

    public schedule(String DropT, String DropP, String PickT, String PickP) {
        this.DropT = DropT;
        this.DropP = DropP;
        this.PickT = PickT;
        this.PickP = PickP;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDropT() {
        return DropT;
    }

    public String getDropP() {
        return DropP;
    }

    public String getPickT() {
        return PickT;
    }

    public String getPickP() {
        return PickP;
    }



}
