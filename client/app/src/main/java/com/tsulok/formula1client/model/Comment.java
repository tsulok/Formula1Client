package com.tsulok.formula1client.model;

public class Comment extends IdentifiedModel {

    private String content;
    private String author;
    private String date;

    public Comment(int id, String comment, String author, String date) {
        super(id);
        this.content = comment;
        this.author = author;
        this.date = date;
    }

    public String getComment() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }
}
