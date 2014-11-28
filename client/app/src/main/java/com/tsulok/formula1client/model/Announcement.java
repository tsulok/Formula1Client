package com.tsulok.formula1client.model;

public class Announcement extends IdentifiedModel {

    private String lead;
    private String content;
    private String author;
    private String date;

    public Announcement(int id, String lead, String content, String author, String date) {
        super(id);
        this.lead = lead;
        this.content = content;
        this.author = author;
        this.date = date;
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
}
