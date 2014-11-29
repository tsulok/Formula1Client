package com.tsulok.formula1client.rest;

import com.tsulok.formula1client.Constants;
import com.tsulok.formula1client.R;
import com.tsulok.formula1client.helper.Helper;

import retrofit.RestAdapter;

public class ApiHelper {
    private static ApiHelper instance;
    private Forma1Service forma1Service;

    protected ApiHelper() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(Constants.logLevel)
                .setEndpoint(Helper.getStringRes(R.string.base_url))
                .setErrorHandler(new ErrorHandler())
                .build();
        forma1Service = adapter.create(Forma1Service.class);
    }

    public static ApiHelper getApi(){
        if(instance == null){
            instance = new ApiHelper();
        }
        return instance;
    }

    public Forma1Service getService(){
        return forma1Service;
    }

}
