package com.tsulok.formula1client.helper;

import android.util.Log;

import com.tsulok.formula1client.model.Announcement;
import com.tsulok.formula1client.model.Comment;
import com.tsulok.formula1client.model.Driver;
import com.tsulok.formula1client.model.Season;
import com.tsulok.formula1client.model.Team;
import com.tsulok.formula1client.model.User;
import com.tsulok.formula1client.rest.container.AnnouncementContainer;
import com.tsulok.formula1client.rest.container.CommentContainer;
import com.tsulok.formula1client.rest.container.DriverContainer;
import com.tsulok.formula1client.rest.container.TeamContainer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DataManager {
    private static final String TAG = "Datamanager";
    private static DataManager instance;

    private LinkedHashMap<Integer, Announcement> announcements;
    private ArrayList<Season> seasons;
    private LinkedHashMap<Integer, Team> teams;
    private LinkedHashMap<Integer, Driver> drivers;
    private LinkedHashMap<Integer, Comment> comments;
    private User currentUser;

    protected DataManager(){
        announcements = new LinkedHashMap<Integer, Announcement>();
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

    public void initAnnouncements(AnnouncementContainer announcementContainer){
        for (Announcement announcement : announcementContainer.getAnnouncements()) {
            announcements.put(announcement.getId(), announcement);
        }
    }

    public void initTeams(TeamContainer teamContainer){
        for (Team team : teamContainer.getTeams()) {
            teams.put(team.getId(), team);
        }
    }

    public void initDrivers(DriverContainer driverContainer){
        for (Driver driver : driverContainer.getDrivers()) {
            drivers.put(driver.getId(), driver);
        }
    }

    public void initComments(CommentContainer commentContainer){
        Announcement announcement = announcements.get(commentContainer.getAnnouncementId());
        if(announcement == null){
            Log.e(TAG, "Announcement was not found in the client. Developer error");
            return;
        }

        for (Comment comment: commentContainer.getComments()) {
            comments.put(comment.getId(), comment);
            announcement.addCommentId(comment.getId());
        }
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

    public ArrayList<Announcement> getAnnouncementsAsList(){
        return new ArrayList<Announcement>(announcements.values());
    }

    public LinkedHashMap<Integer, Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(LinkedHashMap<Integer, Announcement> announcements) {
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
