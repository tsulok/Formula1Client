package com.tsulok.formula1client;

import retrofit.RestAdapter;

public class Constants {
    public static RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.BASIC;

    public static enum DataType{
        ANNOUNCEMENT,
        NEWS,
        TEAMS,
        SEASON
    }
}
