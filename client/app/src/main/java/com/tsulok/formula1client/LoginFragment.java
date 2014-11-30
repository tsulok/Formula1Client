package com.tsulok.formula1client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.tsulok.formula1client.helper.DataManager;
import com.tsulok.formula1client.helper.UIHelper;
import com.tsulok.formula1client.model.BasicAnswer;
import com.tsulok.formula1client.model.User;
import com.tsulok.formula1client.rest.MyCallback;

import java.util.Arrays;

public class LoginFragment extends NamedFragment {

    public static String TAG = "LoginFagment";

    public interface  OnLoginListener {
        public void onLoginSucceeded();
    }

    private OnLoginListener mListener;
    private UiLifecycleHelper uiHelper;


    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mEmailSignInButton;
    private Button mEmailRegisterButton;
    private LoginButton facebookButton;

    @Override
    public int getTitleId() {
        return R.string.title_login;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void inflateObjects(View v) {
        mEmailSignInButton = (Button) v.findViewById(R.id.email_sign_in_button);
        mEmailRegisterButton = (Button) v.findViewById(R.id.email_register_button);
        mUsernameView = (EditText) v.findViewById(R.id.email);
        mPasswordView = (EditText) v.findViewById(R.id.password);
        mLoginFormView = v.findViewById(R.id.login_form);
        mProgressView = v.findViewById(R.id.login_progress);
        facebookButton = (LoginButton) v.findViewById(R.id.facebookBtn);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), statusCallback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    protected void initObjects() {
        facebookButton.setFragment(this);
        facebookButton.setReadPermissions(Arrays.asList("public_profile", "user_about_me"));
    }

    @Override
    protected void initEventHandlers() {
        mEmailRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameView.getText().toString().trim();
                String password = mPasswordView.getText().toString().trim();

                if(!validateErrors(username, password)){
                    attemptRegister(username, password);
                }
            }
        });
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameView.getText().toString().trim();
                String password = mPasswordView.getText().toString().trim();

                if(!validateErrors(username, password)){
                    attemptLogin(username, password);
                }
            }
        });
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session session = Session.getActiveSession();
                if (!session.isOpened() && !session.isClosed()) {
                    session.openForRead(new Session.OpenRequest(LoginFragment.this)
                            .setPermissions(Arrays.asList("public_profile"))
                            .setCallback(statusCallback));
                } else {
                    Session.openActiveSession(getActivity(), LoginFragment.this, true, LoginFragment.this.statusCallback);
                }
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLoginListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Attempts to sign in the account specified by the login form.
     */
    private void attemptLogin(final String username, final String password) {
        UIHelper.showProgress(true, mProgressView, mLoginFormView);
        Api.login(username, password, new IAction() {
            @Override
            public void doAction(Object... args) {
                if(!password.equals(getString(R.string.facebook_pswd))) {
                    DataManager.getInstance().setCurrentUser(new User(username));
                }
                UIHelper.showToast(R.string.success_login);
                UIHelper.hideProgress();
                ((DrawerMainActivity)(getActivity())).updateUser();
                mListener.onLoginSucceeded();
            }
        });
    }

    /**
     * Attempts to register the account specified by the login form.
     */
    private void attemptRegister(String username, String password) {
        UIHelper.showProgress(true, mProgressView, mLoginFormView);
        Api.register(username, password, new IAction() {
            @Override
            public void doAction(Object... args) {
                UIHelper.showToast(R.string.succes_register);
                UIHelper.hideProgress();
            }
        });
    }

    /**
     * Validates the input fields
     * @param username
     * @param password
     * @return True if no error found, false otherwise
     */
    private boolean validateErrors(String username, String password){
        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isEmailValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_email));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        return cancel;
    }

    private boolean isEmailValid(String email) {
        return email.length() >= 3;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    /* Facebook login */

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            UIHelper.showProgress(true, mLoginFormView);
            Log.i(TAG, "Logged in...");
            facebookButton.setVisibility(View.GONE);
            Request.newMeRequest(session, new Request.GraphUserCallback() {
                @Override
                public void onCompleted(GraphUser user, Response response) {



                    if (user != null) {
                        final String  userId = user.getId();
                        User myUser = new User(user.getName());
                        DataManager.getInstance().setCurrentUser(myUser);
                        // Login the user
                        App.getService().login(userId, getString(R.string.facebook_pswd), new MyCallback<BasicAnswer>() {
                            @Override
                            public void success(BasicAnswer basicAnswer, retrofit.client.Response response) {
                                // If login can not be found, then register it
                                if(basicAnswer == null || !basicAnswer.isSuccess()){
                                    Api.register(userId, getString(R.string.facebook_pswd), new IAction() {
                                        @Override
                                        public void doAction(Object... args) {
                                            attemptLogin(userId, getString(R.string.facebook_pswd));
                                        }
                                    });
                                    return;
                                } else {
                                    UIHelper.showToast(R.string.success_login);
                                    UIHelper.hideProgress();
                                    ((DrawerMainActivity)(getActivity())).updateUser();
                                    mListener.onLoginSucceeded();
                                }
                            }
                        });
                    }



                }
            }).executeAsync();
//            Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
//
//                @Override
//                public void onCompleted(final GraphUser user, Response response) {
//                    if (user != null) {
//                        final String  userId = user.getId();
//                        User myUser = new User(user.getUsername());
//                        DataManager.getInstance().setCurrentUser(myUser);
//                        // Login the user
//                        App.getService().login(userId, getString(R.string.facebook_pswd), new MyCallback<BasicAnswer>() {
//                            @Override
//                            public void success(BasicAnswer basicAnswer, retrofit.client.Response response) {
//                                // If login can not be found, then register it
//                                if(basicAnswer == null || !basicAnswer.isSuccess()){
//                                    Api.register(userId, getString(R.string.facebook_pswd), new IAction() {
//                                        @Override
//                                        public void doAction(Object... args) {
//                                            attemptLogin(userId, getString(R.string.facebook_pswd));
//                                        }
//                                    });
//                                    return;
//                                } else {
//                                    UIHelper.showToast(R.string.success_login);
//                                    UIHelper.hideProgress();
//                                    ((DrawerMainActivity)(getActivity())).updateUser();
//                                    mListener.onLoginSucceeded();
//                                }
//                            }
//                        });
//                    }
//                }
//            });
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }

    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }
}
