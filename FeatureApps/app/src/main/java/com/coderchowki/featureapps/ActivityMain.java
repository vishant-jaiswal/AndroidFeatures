package com.coderchowki.featureapps;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

public class ActivityMain extends AppCompatActivity {

    private static final int PROGRESS = 0x1;
    private static final String TAG = "PRBAR" ;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus += 5;
                    try {
                        // Sleep for 1/20th sec
                        Thread.sleep(1000/20);
                        Log.d(TAG, "sleep success" + mProgressStatus);
                    } catch (InterruptedException e) {
                        Log.d(TAG, "sleep failure");
                    }
                    mProgress.setProgress(mProgressStatus);


                }
            }
        }).start();


    }

    private int doWork() {
        return 0;
    }
}
