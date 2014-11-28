package com.tsulok.formula1client.model;

public class Driver extends IdentifiedModel{

    private String name;
    private double points;
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
