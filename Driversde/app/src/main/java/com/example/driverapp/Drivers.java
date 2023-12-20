package com.example.driverapp;

public class Drivers {
    String PickP;
    String DropP;
    String Date_s;
    String carnumber;

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

    public String getDate_s() {
        return Date_s;
    }

    public void setDate_s(String date_s) {
        Date_s = date_s;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public Drivers() {
    }

    public Drivers(String pickP, String dropP, String date_s, String carnumber) {
        PickP = pickP;
        DropP = dropP;
        Date_s = date_s;
        this.carnumber = carnumber;
    }
}
