package com.coderchowki.featureapps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ActivityFAB extends AppCompatActivity {

    private TextView mName;
    private TextView mQuantity;
    private TextView mDeliveryDate;
    private Item mCurrentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //get Text view
        mName = (TextView) findViewById(R.id.name_text);
        mQuantity = (TextView) findViewById(R.id.quantity_text);
        mDeliveryDate = (TextView) findViewById(R.id.date_text );



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentItem = Item.getDefaultItem();
                showCurrentItem();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void showCurrentItem() {
        mName.setText(mCurrentItem.getName());
        mQuantity.setText(getString(R.string.quantity_format,mCurrentItem.getQuantity()));
        mDeliveryDate.setText(getString(R.string.date_format, mCurrentItem.getDeliveryDateString()));

    }

}
