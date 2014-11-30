package com.tsulok.formula1client.rest;

import com.tsulok.formula1client.model.BasicAnswer;
import com.tsulok.formula1client.rest.container.AnnouncementContainer;
import com.tsulok.formula1client.rest.container.CommentContainer;
import com.tsulok.formula1client.rest.container.DriverContainer;
import com.tsulok.formula1client.rest.container.SeasonContainer;
import com.tsulok.formula1client.rest.container.TeamContainer;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface Forma1Service {

    @GET("/register")
    void register(@Query("username")String username, @Query("password")String password, Callback<BasicAnswer> cb);

    @GET("/login")
    void login(@Query("username")String username, @Query("password")String password, Callback<BasicAnswer> cb);

    @GET("/getAnnouncements")
    void announcements(Callback<AnnouncementContainer> cb);

//    @GET("/getComment?announcementId={id}")
//    void getComment(@Path("id")String announcementId, Callback<CommentContainer> cb);

    @GET("/getComment")
    void getComment(@Query("announcementId")int announcementId, Callback<CommentContainer> cb);

    @POST("/addComment")
    void comment(@Field("comment") String comment,
                 @Field("username") String username,
                 @Field("announcementId") String announcementId,
                 Callback<BasicAnswer> cb);

    @GET("/getTeams")
    void teams(Callback<TeamContainer> cb);

    @GET("/getDrivers")
    void drivers(Callback<DriverContainer> cb);

    @GET("/getSeason")
    void season(@Query("year") int year, Callback<SeasonContainer> cb);
}
