package com.tsulok.formula1client.model;

import java.util.HashSet;

public class Team extends IdentifiedModel {

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
}
