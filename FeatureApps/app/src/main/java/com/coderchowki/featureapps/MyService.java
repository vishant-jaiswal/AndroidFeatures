package com.coderchowki.featureapps;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MyService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private long lastUpdate;
    private boolean color = false;
    Boolean is_alarm_enabled = false;

    public MyService() {
        Log.i("bhencho","MyService");
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("bhencho","onBind");

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // List of Sensors Available
        List<Sensor> msensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        lastUpdate = System.currentTimeMillis();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
            setHelpAlarm();
        }
    }

    private void setHelpAlarm() {
        if(!is_alarm_enabled) {
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, NetworkService.class);
            intent.putExtra("ONE_TIME", Boolean.FALSE);
            PendingIntent pi = PendingIntent.getService(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //After after 5 seconds
            am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis() + 1000, pi);
            Log.i("bhencho", "setHelpAlarm");
            is_alarm_enabled = true;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
