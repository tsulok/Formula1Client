package com.tsulok.formula1client.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("driverId")
    private int driverId;

    @SerializedName("point")
    private double point;

    @SerializedName("position")
    private int position;

    public int getDriverId() {
        return driverId;
    }

    public double getPoint() {
        return point;
    }

    public int getPosition() {
        return position;
    }
}
