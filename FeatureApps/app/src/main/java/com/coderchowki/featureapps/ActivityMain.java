package com.coderchowki.featureapps;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ActivityMain extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private boolean color = false;
    private TextView x_view;
    private TextView y_view;
    private TextView z_view;
    private long lastUpdate;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x_view = (TextView) findViewById(R.id.x_textView);
        y_view = (TextView) findViewById(R.id.y_textView);
        z_view = (TextView) findViewById(R.id.z_textView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // List of Sensors Available
        List<Sensor> msensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        x_view.setText(Float.toString(x));
        y_view.setText(Float.toString(y));
        z_view.setText(Float.toString(z));

        if (accelationSquareRoot >= 8) //
        {
            Log.i("bhencho","\n accl squar root : " + Float.toString(accelationSquareRoot));
            Log.i("bhencho","\n x value : " + Float.toString(x));
            Log.i("bhencho","\n y value : " + Float.toString(y));
            Log.i("bhencho","\n z value : " + Float.toString(z));

            float timediff = actualTime - lastUpdate;
            Log.i("bhencho","\n timediff :" + Float.toString(timediff) );

            if (actualTime - lastUpdate < 600) {
                Log.i("bhencho","\n actualTime - lastUpdate > 500" + (actualTime - lastUpdate) );
                return;
            }
            lastUpdate = actualTime;
            Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
                    .show();
            /*if (color) {
                view.setBackgroundColor(Color.GREEN);
            } else {
                view.setBackgroundColor(Color.RED);
            }*/
            color = !color;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}