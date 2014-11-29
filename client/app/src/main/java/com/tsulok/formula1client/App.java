package com.tsulok.formula1client;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;

import com.tsulok.formula1client.rest.ApiHelper;
import com.tsulok.formula1client.rest.Forma1Service;

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

    public static Forma1Service getService(){
        return ApiHelper.getApi().getService();
    }

    public static void initializeDummy(){
//        ArrayList<Announcement> announcements = new ArrayList<Announcement>();
//        for(int i = 0; i < 15; i++){
//            announcements.add(new Announcement(i, "Lead " + i, "Content " + i, "Author " + i, "Date " + i));
//        }
//
//        ArrayList<Season> seasons = new ArrayList<Season>();
//        for(int i = 0; i < 3; i++){
//            seasons.add(new Season(i, 1991 + i));
//        }
//
//        LinkedHashMap<Integer, Driver> drivers = new LinkedHashMap<Integer, Driver>();
//        final Random random = new Random();
//        for(int i = 0; i < 6; i++){
//            drivers.put(i, new Driver(i, "Driver name " + i, 13 + i, random.nextInt(8)));
//        }
//
//        LinkedHashMap<Integer, Team> teams = new LinkedHashMap<Integer, Team>();
//        for (int i = 0; i < 8; i++) {
//            teams.put(i, new Team(i, "Team Name " + i,
//                    new HashSet<Integer>() {
//                        {
//                            add(random.nextInt(6));
//                        }
//                    }));
//        }
//
//        DataManager dataManager = DataManager.getInstance();
//        dataManager.setAnnouncements(announcements);
//        dataManager.setDrivers(drivers);
//        dataManager.setSeasons(seasons);
//        dataManager.setTeams(teams);
    }
}
