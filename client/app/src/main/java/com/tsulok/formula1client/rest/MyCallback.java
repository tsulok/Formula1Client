package com.tsulok.formula1client.rest;

import com.tsulok.formula1client.R;
import com.tsulok.formula1client.helper.UIHelper;

import retrofit.Callback;
import retrofit.RetrofitError;

public abstract class MyCallback<T> implements Callback<T>{

    @Override
    public void failure(RetrofitError error) {
        UIHelper.showToast(R.string.alert_general_error);
        UIHelper.hideProgress();
    }
}
