package com.tsulok.formula1client.rest.container;

import com.google.gson.annotations.SerializedName;
import com.tsulok.formula1client.model.Announcement;
import com.tsulok.formula1client.model.BasicAnswer;

import java.util.ArrayList;

public class AnnouncementContainer extends BasicAnswer{

    @SerializedName("announcements")
    private ArrayList<Announcement> announcements;

    public ArrayList<Announcement> getAnnouncements() {
        return announcements;
    }
}
