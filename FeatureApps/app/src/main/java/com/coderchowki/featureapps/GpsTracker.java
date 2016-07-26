/*
package com.coderchowki.featureapps;

import android.util.Log;

*/
/*
  Created by Vishant on 26-07-2016.
*//*


import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GpsTracker extends Service{

    private static final String TAG = "GpsTracker";

    protected boolean isRunning = false;
    public static String userId = null;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10
    private static final long MIN_TIME_BW_UPDATES = 1000 *  10 * 1; // 1 minute
    private static final long CHECK_GPS_TIME = 1000 * 1 * 30; // 5 minute
    double longitude = 0.0;
    double latitude = 0.0;
    private String phone_imei_number = null;
    private String phone_number = null;
    private String gps_state = "OFF";
    private TelephonyManager telephonyManager = null;
    private LocationListener locationListener = null;
    private LocationManager locationManager = null;
    private Handler handler;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        Toast.makeText(this, "Service Create", Toast.LENGTH_LONG).show();
        isRunning = true;
        telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        phone_imei_number = telephonyManager.getDeviceId();
        phone_number = telephonyManager.getLine1Number();
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {}
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}
            @Override
            public void onProviderEnabled(String s) {}
            @Override
            public void onProviderDisabled(String s) {}
        };

        getLocation();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        handler = new Handler();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("onDestroy", "here");
        isRunning = false;
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(),this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartPendingIntentService = PendingIntent.getService(getApplicationContext(),1,restartServiceIntent,PendingIntent.FLAG_NO_CREATE);
        AlarmManager alarmManager = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(
                AlarmManager.ELAPSED_REALTIME
                , SystemClock.elapsedRealtime() +1000,
                restartPendingIntentService);
        super.onTaskRemoved(rootIntent);
        onDestroy();
        //stopSelf();
    }


    public void setLocationManager (){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    public boolean getGpsState(){
        setLocationManager();
        // getting GPS status
        boolean gpsState = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // getting NET status
        boolean netState = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (netState || gpsState) {
            gps_state = "ON";
            return true;
        }
        else {
            gps_state = "OFF";
            Intent intent = new Intent(getApplicationContext(),ActivityAlertDlg.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return false;
    }

    public Location getLocation() {
        Location location = null;
        try {
            setLocationManager();
            if (!getGpsState()) {
                // no network provider is enabled
            } else {
                boolean gpsState = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                // if GPS Enabled get lat/long using GPS Services
                if (gpsState) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return null;
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
                boolean netState = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                // First get location from Network Provider
                if (netState) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public String getDateTime(){
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String newtime =  sdfDateTime.format(new Date(System.currentTimeMillis()));
        return newtime;
    }

   */
/* public GPSTrackingModel.GOTracking makeJsonObject(){
        Location location = getLocation();
        double longitude = 0.0;
        double latitude = 0.0;
        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        goTracking = new GPSTrackingModel.GOTracking();
        return goTracking;
    }

    public Map<String,String> JSONData() throws JSONException {
        Map<String,String> loc = new HashMap<>();
        Location location = getLocation();
        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            loc.put("lat",String.format("%s",latitude));
            loc.put("lng",String.format("%s",longitude));
        }
        return loc;
    }*//*

}
*/
