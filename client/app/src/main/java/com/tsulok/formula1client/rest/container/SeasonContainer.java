package com.tsulok.formula1client.rest.container;

import com.google.gson.annotations.SerializedName;
import com.tsulok.formula1client.model.BasicAnswer;
import com.tsulok.formula1client.model.Season;

public class SeasonContainer extends BasicAnswer{

    @SerializedName("season")
    private Season season;

    public Season getSeason() {
        return season;
    }
}
