package com.tsulok.formula1client.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Track{

    @SerializedName("trackId")
    private String trackId;

    @SerializedName("trackName")
    private String name;

    @SerializedName("runs")
    private List<Run> runs;

    public String getTrackId() {
        return trackId;
    }

    public String getName() {
        return name;
    }

    public List<Run> getRuns() {
        return runs;
    }
}
