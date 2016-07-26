package com.coderchowki.featureapps;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NetworkService extends IntentService {

    public NetworkService() {

        super("NetworkService");
        Log.i("bhencho","NetworkService constructor");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i("bhencho","NetworkService onHandleIntent");

    }

}
