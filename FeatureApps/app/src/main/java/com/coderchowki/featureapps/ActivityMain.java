package com.coderchowki.featureapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coderchowki.featureapps.Widget.DatePickerView;

public class ActivityMain extends AppCompatActivity {

    private DatePickerView datePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePickerView = (DatePickerView) findViewById(R.id.dpv_date);
    }
}
