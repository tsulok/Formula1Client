package com.tsulok.formula1client;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

public class App extends Application{

    public static final String TAG = "Forma1Client";
    private static Context appContext;
    private static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        resources = appContext.getResources();
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static Resources getRes(){
        return resources;
    }

    public static Locale getLocale() {
        return appContext.getResources().getConfiguration().locale;
    }

    public static String getAppVersion() {
        try {
            return appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static int getAndroidVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    public static String getAndroidVersionName(){
        return Build.VERSION.RELEASE;
    }
}
