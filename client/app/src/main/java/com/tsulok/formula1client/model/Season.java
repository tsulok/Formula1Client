package com.tsulok.formula1client.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Season extends IdentifiedModel {

    // TODO add to server
    @SerializedName("year")
    private int year;

    @SerializedName("tracks")
    private List<Track> tracks;

    public Season(int id, int year) {
        super(id);
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
