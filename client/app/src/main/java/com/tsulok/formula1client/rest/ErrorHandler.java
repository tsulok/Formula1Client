package com.tsulok.formula1client.rest;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class ErrorHandler implements retrofit.ErrorHandler{
    @Override
    public Throwable handleError(RetrofitError cause) {
        Response r = cause.getResponse();

        if (r != null) {
            switch (r.getStatus()) {
                // TODO handle errors
                default:
                return cause;
            }
        }
        return cause;
    }
}
