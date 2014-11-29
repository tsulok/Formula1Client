package com.tsulok.formula1client.rest.container;

import com.google.gson.annotations.SerializedName;
import com.tsulok.formula1client.model.BasicAnswer;
import com.tsulok.formula1client.model.Team;

import java.util.ArrayList;

public class TeamContainer extends BasicAnswer{

    @SerializedName("teams")
    private ArrayList<Team> teams;

    public ArrayList<Team> getTeams() {
        return teams;
    }
}
