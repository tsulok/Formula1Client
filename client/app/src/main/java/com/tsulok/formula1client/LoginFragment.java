package com.tsulok.formula1client;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tsulok.formula1client.helper.DataManager;
import com.tsulok.formula1client.helper.UIHelper;
import com.tsulok.formula1client.model.User;

public class LoginFragment extends NamedFragment {

    public static String TAG = "LoginFagment";

    public interface  OnLoginListener {
        public void onLoginSucceeded(String username);
    }

    private OnLoginListener mListener;

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mEmailSignInButton;
    private Button mEmailRegisterButton;

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
    }

    @Override
    protected void initObjects() {

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
    private void attemptLogin(final String username, String password) {
        UIHelper.showProgress(true, mProgressView, mLoginFormView);
        Api.login(username, password, new IAction() {
            @Override
            public void doAction(Object... args) {
                DataManager.getInstance().setCurrentUser(new User(username));
                UIHelper.showToast(R.string.success_login);
                UIHelper.hideProgress();
                ((DrawerMainActivity)(getActivity())).updateUser();
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
}
