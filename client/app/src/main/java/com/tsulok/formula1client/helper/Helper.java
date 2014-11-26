package com.tsulok.formula1client.helper;

import android.content.res.Resources;

import com.tsulok.formula1client.App;

public class Helper {

    public static String getStringRes(int resId){
        return App.getRes().getString(resId);
    }
}
