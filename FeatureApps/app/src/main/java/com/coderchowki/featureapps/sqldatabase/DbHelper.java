package com.coderchowki.featureapps.sqldatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vishant on 03-08-2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "featuredatabase";
    private static final String TABLE_NAME = "TESTTABLE";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
