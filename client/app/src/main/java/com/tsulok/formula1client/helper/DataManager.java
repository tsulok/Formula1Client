package com.tsulok.formula1client.helper;

import com.tsulok.formula1client.model.Announcement;
import com.tsulok.formula1client.model.Comment;
import com.tsulok.formula1client.model.Driver;
import com.tsulok.formula1client.model.Season;
import com.tsulok.formula1client.model.Team;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DataManager {
    private static DataManager instance;

    private ArrayList<Announcement> announcements;
    private ArrayList<Season> seasons;
    private LinkedHashMap<Integer, Team> teams;
    private LinkedHashMap<Integer, Driver> drivers;
    private LinkedHashMap<Integer, Comment> comments;

    protected DataManager(){
        announcements = new ArrayList<Announcement>();
        teams = new LinkedHashMap<Integer, Team>();
        drivers = new LinkedHashMap<Integer, Driver>();
        seasons = new ArrayList<Season>();
        comments = new LinkedHashMap<Integer, Comment>();
    }

    public static DataManager getInstance(){
        if(instance == null){
            instance = new DataManager();
        }
           return instance;
    }

    public ArrayList<Announcement> getAnnouncements() {
        return announcements;
    }

    public ArrayList<Team> getTeamsAsList(){
        return new ArrayList<Team>(teams.values());
    }

    public LinkedHashMap<Integer, Team> getTeams() {
        return teams;
    }

    public LinkedHashMap<Integer, Driver> getDrivers() {
        return drivers;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public LinkedHashMap<Integer, Comment> getComments() {
        return comments;
    }

    public void setAnnouncements(ArrayList<Announcement> announcements) {
        this.announcements = announcements;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public void setTeams(LinkedHashMap<Integer, Team> teams) {
        this.teams = teams;
    }

    public void setDrivers(LinkedHashMap<Integer, Driver> drivers) {
        this.drivers = drivers;
    }

    public void setComments(LinkedHashMap<Integer, Comment> comments) {
        this.comments = comments;
    }
}
