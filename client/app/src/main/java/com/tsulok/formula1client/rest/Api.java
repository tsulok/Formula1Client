package com.tsulok.formula1client.rest;

import com.tsulok.formula1client.App;
import com.tsulok.formula1client.Constants;
import com.tsulok.formula1client.R;
import com.tsulok.formula1client.helper.Helper;

import retrofit.RestAdapter;

public class Api {
    private static Api instance;
    private Forma1Service forma1Service;

    protected Api () {
        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(Constants.logLevel)
                .setEndpoint(Helper.getStringRes(R.string.base_url))
                .setErrorHandler(new ErrorHandler())
                .build();
        forma1Service = adapter.create(Forma1Service.class);
    }

    public static Api getApi(){
        if(instance == null){
            instance = new Api();
        }
        return instance;
    }

    public Forma1Service getService(){
        return forma1Service;
    }

}
