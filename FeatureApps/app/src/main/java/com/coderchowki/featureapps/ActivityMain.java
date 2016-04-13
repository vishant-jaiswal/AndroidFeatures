package com.coderchowki.featureapps;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

public class ActivityMain extends AppCompatActivity implements FragmentLogin.OnLoginListener, GoogleApiClient.OnConnectionFailedListener {

    private static final int REQUEST_CODE_GOOGLE_LOGIN = 1;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Firebase.setAndroidContext(this);
        }

        //TODO 1:GoogleSignINOption object builder
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        Firebase firebase = new Firebase(Constants.FIREBASE_URL);

        if(firebase.getAuth() == null || isExpired(firebase.getAuth())){
            switchToFragmentLogin();
        }
        else{
            switchToFragmentHome(Constants.FIREBASE_URL + "/users/" + firebase.getAuth().getUid());
        }




    }

    private boolean isExpired(AuthData auth) {
        return (System.currentTimeMillis()/1000) >= auth.getExpires();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // All providers are optional! Remove any you don't want.
        //setEnabledAuthProvider(AuthProviderType.FACEBOOK);
        //setEnabledAuthProvider(AuthProviderType.TWITTER);
        //setEnabledAuthProvider(AuthProviderType.GOOGLE);
       // setEnabledAuthProvider(AuthProviderType.PASSWORD);
    }

  
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(Constants.TAG, "onConnection failed by gmail auth" + connectionResult.getErrorMessage());
    }

    private void switchToFragmentLogin() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, new FragmentLogin(), "Login");
        ft.commit();
    }

    @Override
    public void onGoogleLogin() {
        //TODO: Log user in with Google Account
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, REQUEST_CODE_GOOGLE_LOGIN);
    }

    private void onGoogleLoginWithToken(String oAuthToken) {
        //TODO: Log user in with Google OAuth Token
        Firebase firebase = new Firebase(Constants.FIREBASE_URL);
        firebase.authWithOAuthToken("google", oAuthToken, new MyAuthResultHandler());
    }

    private void showLoginError(String message) {
        FragmentLogin loginFragment = (FragmentLogin) getSupportFragmentManager().findFragmentByTag("Login");
        loginFragment.onLoginError(message);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GOOGLE_LOGIN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                //TODO:Extract Information from google account
                String email = account.getEmail();
                Log.i(Constants.TAG,"get email" + email);
                getGoogleOAuthTocken(email);
            }
        }
    }

    private void getGoogleOAuthTocken(final String email) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            String errorMessage = null;

            @Override
            protected String doInBackground(Void... params) {
                String token = null;
                try {
                    String scope = "oauth2:profile email";
                    token = GoogleAuthUtil.getToken(ActivityMain.this, email, scope);
                } catch (IOException transientEx) {
                /* Network or server error */
                    errorMessage = "Network error: " + transientEx.getMessage();
                } catch (UserRecoverableAuthException e) {
                /* We probably need to ask for permissions, so start the intent if there is none pending */
                    Intent recover = e.getIntent();
                    startActivityForResult(recover, ActivityMain.REQUEST_CODE_GOOGLE_LOGIN);
                } catch (GoogleAuthException authEx) {
                    errorMessage = "Error authenticating with Google: " + authEx.getMessage();
                }
                return token;
            }
            @Override
            protected void onPostExecute(String token) {
                Log.i("FPK", "onPostExecute" + token);
                //TODO
                if(token != null){
                    onGoogleLoginWithToken(token);
                }else{
                    showLoginError(errorMessage);
                }
            }
        };
        task.execute();
    }

    class MyAuthResultHandler implements Firebase.AuthResultHandler{

        @Override
        public void onAuthenticated(AuthData authData) {
            //TODO:Switch to home screen after successful authentication
            Log.i(Constants.TAG,"successful login");
            switchToFragmentHome(Constants.FIREBASE_URL + "/users/" + authData.getUid());
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {

        }
    }

    private void switchToFragmentHome(String repoUrl) {
        Log.i(Constants.TAG,"" + repoUrl);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment passwordFragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(Constants.FIREBASE, repoUrl);
        passwordFragment.setArguments(args);
        ft.replace(R.id.fragment, passwordFragment, "Passwords");
        ft.commit();
    }
}
