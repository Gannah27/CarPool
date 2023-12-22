package com.example.carpool;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "schedule")
public class schedule {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String PickP;
    private String DropP;
    private String Date_s;
    private String carnumber;
    private  String Firebase;
    private String confirm;

    public schedule(String pickP, String dropP, String date_s, String carnumber, String firebase, String confirm) {
        PickP = pickP;
        DropP = dropP;
        Date_s = date_s;
        this.carnumber = carnumber;
        Firebase = firebase;
        this.confirm = confirm;
    }

    public String getFirebase() {
        return Firebase;
    }

    public void setFirebase(String firebase) {
        Firebase = firebase;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setDate_s(String date_s) {
        Date_s = date_s;
    }

    public void setDropP(String dropP) {
        DropP = dropP;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public void setPickP(String pickP) {
        PickP = pickP;
    }


    public schedule() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate_s() {
        return Date_s;
    }

    public String getDropP() {
        return DropP;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public String getPickP() {
        return PickP;
    }



}
