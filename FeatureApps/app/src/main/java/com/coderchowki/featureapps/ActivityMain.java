package com.coderchowki.featureapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class ActivityMain extends AppCompatActivity {

    //TODO: 2> create an object of recyclerview

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: 3> get the refernce of an object of recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }
}
