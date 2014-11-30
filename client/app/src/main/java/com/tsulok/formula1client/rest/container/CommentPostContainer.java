package com.tsulok.formula1client.rest.container;

import com.google.gson.annotations.SerializedName;

public class CommentPostContainer {

    @SerializedName("comment")
    private String comment;

    @SerializedName("username")
    private String username;

    @SerializedName("announcementId")
    private int announcementId;

    public CommentPostContainer(String username, String comment, int announcementId) {
        this.username = username;
        this.comment = comment;
        this.announcementId = announcementId;
    }
}
