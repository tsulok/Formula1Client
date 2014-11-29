package com.tsulok.formula1client.rest.container;

import com.google.gson.annotations.SerializedName;
import com.tsulok.formula1client.model.BasicAnswer;
import com.tsulok.formula1client.model.Driver;

import java.util.ArrayList;

public class DriverContainer extends BasicAnswer{

    @SerializedName("drivers")
    private ArrayList<Driver> drivers;

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }
}
