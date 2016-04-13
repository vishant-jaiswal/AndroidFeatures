package com.coderchowki.featureapps;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.SignInButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {


    private boolean mLoggingIn;
    private SignInButton mGoogleSignInButton;
    private OnLoginListener mListener;
    private View mProgressSpinner;
    private View mLoginForm;

    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoggingIn = false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login,container,false);

        mProgressSpinner = rootView.findViewById(R.id.login_progress);
        mLoginForm = rootView.findViewById(R.id.login_form);
        mGoogleSignInButton = (SignInButton)rootView.findViewById(R.id.google_sign_in_button);
        mGoogleSignInButton.setColorScheme(SignInButton.COLOR_LIGHT);
        mGoogleSignInButton.setSize(SignInButton.SIZE_WIDE);
        mGoogleSignInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loginWithGoogle();
            }
        });
        return rootView;
    }

    private void loginWithGoogle() {
        if (mLoggingIn) {
            return;
        }
        showProgress(true);
        mLoggingIn = true;
        mListener.onGoogleLogin();
        //hideKeyboard();
    }

    @Override
    public void onAttach(Context activity) {
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


    private void showProgress(boolean show) {
        mProgressSpinner.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        mGoogleSignInButton.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    public void onLoginError(String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle(getActivity().getString(R.string.login_error))
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();

        showProgress(false);
        mLoggingIn = false;
    }


    public interface OnLoginListener {
        //void onLogin(String email, String password);

        void onGoogleLogin();


    }

}
