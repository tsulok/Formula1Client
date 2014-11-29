package com.tsulok.formula1client.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Run {

    @SerializedName("results")
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }
}
