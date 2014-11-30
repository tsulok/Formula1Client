package com.tsulok.formula1client.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;

public class Announcement extends IdentifiedModel {

    @SerializedName("lead")
    private String lead;

    @SerializedName("content")
    private String content;

    @SerializedName("author")
    private String author;

    @SerializedName("date")
    private String date;

    @SerializedName("title")
    private String title;

    private HashSet<Integer> commentIds = new HashSet<Integer>();

    public Announcement(int id, int id1, String lead, String content, String author, String date, String title) {
        super(id);
        id = id1;
        this.lead = lead;
        this.content = content;
        this.author = author;
        this.date = date;
        this.title = title;
    }

    public Announcement(int id, String lead, String content, String author, String date) {
        super(id);
        this.lead = lead;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public void addCommentId(int commentId){
        if(commentIds == null){
            commentIds = new HashSet<Integer>();
        }
        commentIds.add(commentId);
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public HashSet<Integer> getCommentIds() {
        return commentIds;
    }
}
