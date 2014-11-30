package com.tsulok.formula1client;

import com.tsulok.formula1client.helper.DataManager;
import com.tsulok.formula1client.helper.UIHelper;
import com.tsulok.formula1client.model.BasicAnswer;
import com.tsulok.formula1client.model.User;
import com.tsulok.formula1client.rest.MyCallback;
import com.tsulok.formula1client.rest.container.AnnouncementContainer;
import com.tsulok.formula1client.rest.container.CommentContainer;
import com.tsulok.formula1client.rest.container.DriverContainer;
import com.tsulok.formula1client.rest.container.TeamContainer;

import retrofit.client.Response;

public class Api {

    public static void login(final String username, String password, final IAction action){
        App.getService().login(username, password, new MyCallback<BasicAnswer>() {
            @Override
            public void success(BasicAnswer basicAnswer, Response response) {
                if (!isAnswerValid(basicAnswer)) {
                    return;
                }

                DataManager.getInstance().setCurrentUser(new User(username));
                action.doAction();
            }
        });
    }

    public static void register(String username, String password, final IAction action){
        App.getService().register(username, password, new MyCallback<BasicAnswer>() {
            @Override
            public void success(BasicAnswer basicAnswer, Response response) {
                if(!isAnswerValid(basicAnswer)){
                    return;
                }

                action.doAction();
            }
        });
    }

    public static void getAnnouncements(final IAction action){
        App.getService().announcements(new MyCallback<AnnouncementContainer>() {
            @Override
            public void success(AnnouncementContainer announcementContainer, Response response) {
                if(!isAnswerValid(announcementContainer)){
                    return;
                }

                DataManager.getInstance().initAnnouncements(announcementContainer);
                action.doAction();
            }
        });
    }

    public static void getComments(final int announcementId, final IAction action){
        App.getService().getComment(announcementId, new MyCallback<CommentContainer>() {
            @Override
            public void success(CommentContainer commentContainer, Response response) {
                if(!isAnswerValid(commentContainer)){
                    return;
                }

                if(!DataManager.getInstance().getAnnouncements().containsKey(announcementId)){
                    UIHelper.showToast(R.string.alert_announcement_not_found);
                } else {
                    DataManager.getInstance().initComments(commentContainer);
                }
                action.doAction(commentContainer);
            }
        });
    }

    public static void getTeams(final IAction action){
        App.getService().teams(new MyCallback<TeamContainer>() {
            @Override
            public void success(TeamContainer teamContainer, Response response) {
                if(!isAnswerValid(teamContainer)){
                    return;
                }

                DataManager.getInstance().initTeams(teamContainer);
                action.doAction();
            }
        });
    }

    public static void getDrivers(final IAction action){
        App.getService().drivers(new MyCallback<DriverContainer>() {
            @Override
            public void success(DriverContainer driverContainer, Response response) {
                if(!isAnswerValid(driverContainer)){
                    return;
                }

                DataManager.getInstance().initDrivers(driverContainer);
                action.doAction();
            }
        });
    }

    public static boolean isAnswerValid(BasicAnswer answer){
        if(answer == null || !answer.isSuccess()){
            UIHelper.showToast(R.string.alert_success_but_error);
            return false;
        } else {
            return true;
        }
    }
}
