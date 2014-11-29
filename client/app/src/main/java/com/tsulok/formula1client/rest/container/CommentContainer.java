package com.tsulok.formula1client.rest.container;

import com.google.gson.annotations.SerializedName;
import com.tsulok.formula1client.model.BasicAnswer;
import com.tsulok.formula1client.model.Comment;

import java.util.ArrayList;

public class CommentContainer extends BasicAnswer{

    @SerializedName("announcementId")
    private int announcementId;

    @SerializedName("comments")
    private ArrayList<Comment> comments;

    public int getAnnouncementId() {
        return announcementId;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
