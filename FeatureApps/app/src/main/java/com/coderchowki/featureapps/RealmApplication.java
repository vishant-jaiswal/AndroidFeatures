package com.coderchowki.featureapps;

import android.app.Application;

import io.realm.RealmConfiguration;

/**
 * Created by Vishant on 15-04-2016.
 */
public class RealmApplication extends Application {
    private static RealmConfiguration configuration;
    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static RealmConfiguration getConfiguration() {
        return configuration;
    }
}
