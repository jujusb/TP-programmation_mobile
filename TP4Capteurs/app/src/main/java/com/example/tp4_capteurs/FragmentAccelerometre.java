package com.example.tp4_capteurs;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

    private SensorManager sensorManager;

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


        return view;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        forceX.setText("ForceX : "+ event.values[0]);
        forceY.setText("ForceY : "+ event.values[1]);
        forceZ.setText("ForceZ : "+ event.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d("Accelerometer", "onAccuracyChanged: "+accuracy);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sensorManager.unregisterListener(this);
    }
}
