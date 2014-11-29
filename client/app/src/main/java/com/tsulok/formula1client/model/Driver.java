package com.tsulok.formula1client.model;

import com.google.gson.annotations.SerializedName;

public class Driver extends IdentifiedModel{

    @SerializedName("name")
    private String name;

    // TODO server points
    private double points;

    @SerializedName("teamId")
    private int teamId;

    public Driver(int id, String name, double points, int teamId) {
        super(id);
        this.name = name;
        this.points = points;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public double getPoints() {
        return points;
    }

    public int getTeamId() {
        return teamId;
    }
}
