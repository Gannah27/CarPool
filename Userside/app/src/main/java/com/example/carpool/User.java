package com.example.carpool;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
    private  String PickP;
    private String DropP;
    private String carnumber;
    private String Date_s;
    private String currentUser;
    private String payment;
    public User(String pickP, String dropP, String carnumber, String dateS, String payment,String currentUser) {
        PickP = pickP;
        DropP = dropP;
        this.carnumber = carnumber;
        Date_s = dateS;
        this.currentUser = currentUser;
        this.payment=payment;
    }

    public User() {
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPickP() {
        return PickP;
    }

    public void setPickP(String pickP) {
        PickP = pickP;
    }

    public String getDropP() {
        return DropP;
    }

    public void setDropP(String dropP) {
        DropP = dropP;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getDate_s() {
        return Date_s;
    }

    public void setDate_s(String date_s) {
        Date_s = date_s;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
