package com.coderchowki.featureapps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ActivityMain extends AppCompatActivity {

    public static final String EXTRA_MESSAGE ="EXTRA_MESSAGE";
    public static final String EXTRA_COLOR ="EXTRA_COLOR";
    private static final int REQUEST_CODE_INPUT_ACTIVITY=1;

    private RelativeLayout mLayout;
    private TextView mTextView;
    private String mMessage = "This is your phone. Please rescue me!";
    private int mBackgroundColor = Color.GREEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send an email with the message field as the body
            }
        });

        // Capture
        mLayout = (RelativeLayout) findViewById(R.id.content_main_layout);
        mTextView = (TextView) findViewById(R.id.content_main_message);

        // Set color and text
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_info:
                // TODO: Launch a new Info Activity that is a ScrollingActivity.
                Intent intent = new Intent(this,ScrollingActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_change_color:
                // TODO: Launch the InputActivity to get a result
                Intent intent_InputActivity = new Intent(this,InputActivity.class);
                intent_InputActivity.putExtra(EXTRA_MESSAGE,mMessage);
                intent_InputActivity.putExtra(EXTRA_COLOR,mBackgroundColor);
                startActivityForResult(intent_InputActivity,REQUEST_CODE_INPUT_ACTIVITY);

                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_INPUT_ACTIVITY && resultCode == Activity.RESULT_OK){
            //TODO:get the message from Input Activity Here
            mMessage = data.getStringExtra(EXTRA_MESSAGE);
            mBackgroundColor = data.getIntExtra(EXTRA_COLOR,Color.GRAY);
            updateUI();
        }
    }

    private void updateUI() {
        mTextView.setText(mMessage);
        mLayout.setBackgroundColor(mBackgroundColor);
    }


}
