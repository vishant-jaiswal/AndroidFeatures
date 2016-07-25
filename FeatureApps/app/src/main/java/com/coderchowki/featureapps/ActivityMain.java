package com.coderchowki.featureapps;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity  {

    private Location mLastLocation;
    private TextView mLatitudeText;
    private TextView mLongitudeText;
    private TextView mProvider;

    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLatitudeText = (TextView) findViewById(R.id.tv_latitude);
        mLongitudeText = (TextView) findViewById(R.id.tv_longitude);
        mProvider = (TextView) findViewById(R.id.tv_provider);

        MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
            @Override
            public void gotLocation(Location location){
                //Got the location!
                Double lat = (Double) (location.getLatitude());
                Double lng = (Double) (location.getLongitude());
                mLatitudeText.setText(String.valueOf(lat));
                mLongitudeText.setText(String.valueOf(lng));
            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);



    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



}
