package com.example.tp4_capteurs;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.Nullable;
import android.widget.TextView;

public class FragmentAccelerometre extends Fragment implements SensorEventListener {

    private View view;

    private TextView forceX;
    private TextView forceY;
    private TextView forceZ;

    float x, y, z;
    float last_x, last_y, last_z;

    long lastUpdate;
    final float SHAKE_THRESHOLD = 100;

    private SensorManager sensorManager;
    private MediaPlayer mediaPlayer;

    public FragmentAccelerometre(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_accelerometre, container, false);

        forceX = (TextView) view.findViewById(R.id.forceX);
        forceY = (TextView) view.findViewById(R.id.forceY);
        forceZ = (TextView) view.findViewById(R.id.forceZ);

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.lightsabre);

        lastUpdate = System.currentTimeMillis();
        return view;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        forceX.setText("ForceX : "+ event.values[0]);
        forceY.setText("ForceY : "+ event.values[1]);
        forceZ.setText("ForceZ : "+ event.values[2]);

        long curTime = System.currentTimeMillis();


        if ((curTime - lastUpdate) > 100l) {

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            long diffTime = (curTime -lastUpdate);
            lastUpdate = curTime;
            float speed = Math.abs(x + y + z -last_x -last_y -last_z) / diffTime * 10000f;
            if (speed > SHAKE_THRESHOLD) {
                Log.d("Sound", "onSensorChanged: Sound");
                mediaPlayer.start();
            }
            else {
                Log.d("Sound", "onSensorChanged: Speed : " + speed);
            }
            last_x = x;
            last_y = y;
            last_z = z;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d("Accelerometer", "onAccuracyChanged: "+accuracy);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sensorManager.unregisterListener(this);
        mediaPlayer.release();
    }
}
