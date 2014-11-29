package com.tsulok.formula1client.model;

import com.google.gson.annotations.SerializedName;

public class BasicAnswer {

    @SerializedName("success")
    private boolean success;

    @SerializedName("errorMessage")
    private String errorMessage;

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
