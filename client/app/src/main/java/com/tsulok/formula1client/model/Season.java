package com.tsulok.formula1client.model;

public class Season extends IdentifiedModel {
    private int year;

    public Season(int id, int year) {
        super(id);
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}
