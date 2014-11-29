package com.tsulok.formula1client.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;

public class Team extends IdentifiedModel {

    @SerializedName("name")
    private String name;

    private HashSet<Integer> driverIds;

    public Team(int id, String name, HashSet<Integer> driverIds) {
        super(id);
        this.name = name;
        this.driverIds = driverIds;
    }

    public String getName() {
        return name;
    }

    public HashSet<Integer> getDriverIds() {
        return driverIds;
    }

    public void addDriverId(int driverId){
        if(driverIds == null){
            driverIds = new HashSet<Integer>();
        }
        driverIds.add(driverId);
    }
}
