package com.tsulok.formula1client.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IdentifiedModel implements Serializable{

    @SerializedName("id")
    private int id;

    protected IdentifiedModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
