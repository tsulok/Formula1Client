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
import retrofit.http.Path;

public interface Forma1Service {

    @GET("/register?username={username}&password={password}")
    void register(@Path("username")String username, @Path("password")String password, Callback<BasicAnswer> cb);

    @GET("/login?username={username}&password={password}")
    void login(@Path("username")String username, @Path("password")String password, Callback<BasicAnswer> cb);

    @GET("/getAnnouncements")
    void announcements(Callback<AnnouncementContainer> cb);

    @GET("/getComment?announcementId={id}")
    void getComment(@Path("id")String announcementId, Callback<CommentContainer> cb);

    @POST("/addComment")
    void comment(@Field("comment") String comment,
                 @Field("username") String username,
                 @Field("announcementId") String announcementId,
                 Callback<BasicAnswer> cb);

    @GET("/getTeams")
    void teams(Callback<TeamContainer> cb);

    @GET("/getDrivers")
    void drivers(Callback<DriverContainer> cb);

    @GET("/getSeason?year={year}")
    void season(@Path("year") int year, Callback<SeasonContainer> cb);
}
