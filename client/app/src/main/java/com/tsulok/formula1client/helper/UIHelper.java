package com.tsulok.formula1client.helper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import com.tsulok.formula1client.App;

public class UIHelper {
    private static Toast toast;
    private static View actualProgress;
    private static View actualContainer;

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

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static void showProgress(final boolean show, final View progressBar, final View container) {
        actualContainer = container;
        actualProgress = progressBar;
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = App.getAppContext().getResources().getInteger(android.R.integer.config_shortAnimTime);

            container.setVisibility(show ? View.GONE : View.VISIBLE);
            container.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    container.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            container.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public static void hideProgress(){
        showProgress(false, actualProgress, actualContainer);
        actualContainer = null;
        actualProgress = null;
    }
}
