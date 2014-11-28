package com.tsulok.formula1client.helper;

import android.widget.Toast;

import com.tsulok.formula1client.App;

public class UIHelper {
    private static Toast toast;

    public static void showToast(String message){
        if(toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(App.getAppContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showToast(int messageId){
        showToast(Helper.getStringRes(messageId));
    }
}
