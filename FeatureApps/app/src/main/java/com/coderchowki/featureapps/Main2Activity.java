package com.coderchowki.featureapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(Main2Activity.this, "Activity is working", Toast.LENGTH_SHORT).show();
        Log.i("bhencho","activity works with alarm");
        super.onCreate(savedInstanceState);
    }
}
