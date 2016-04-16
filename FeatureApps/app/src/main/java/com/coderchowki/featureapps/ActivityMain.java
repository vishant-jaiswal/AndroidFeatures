package com.coderchowki.featureapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addObjects();
    }

    private void addObjects() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        Realm realm = Realm.getDefaultInstance();
        DatabaseModel databaseModel = new DatabaseModel("Id1","vishant","RDS");
        realm.beginTransaction();
        realm.copyToRealm(databaseModel);
        realm.commitTransaction();
        realm.close();
    }

}
