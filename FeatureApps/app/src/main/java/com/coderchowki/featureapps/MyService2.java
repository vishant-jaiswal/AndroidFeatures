package com.coderchowki.featureapps;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService2 extends Service {
    public MyService2() {
        Log.i("bhencho","MyService2 constructor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("bhencho","MyService2 onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
}
