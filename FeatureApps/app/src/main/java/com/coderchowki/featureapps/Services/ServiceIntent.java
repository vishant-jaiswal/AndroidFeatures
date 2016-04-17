package com.coderchowki.featureapps.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class ServiceIntent extends IntentService {


    private static final String TAG = "service";

    public ServiceIntent() {

        super("ServiceIntent");
        Log.i(TAG,"Service Constructor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"Service onHandle Intent");

    }


}
