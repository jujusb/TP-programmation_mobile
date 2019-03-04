package com.example.tp4_capteurs;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentProximite extends Fragment implements SensorEventListener {

    private View view;

    private TextView distance;

    long lastUpdate;
    final float DISTANCE_THRESHOLD = 0;

    private SensorManager sensorManager;
    private MediaPlayer mediaPlayer;

    public FragmentProximite(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_proximite, container, false);

        distance = (TextView) view.findViewById(R.id.distance);


        mediaPlayer = MediaPlayer.create(getContext(), R.raw.sabreclash);

        lastUpdate = System.currentTimeMillis();
        return view;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        distance.setText("Distance : " + event.values[0]);


        long curTime = System.currentTimeMillis();

        if ((curTime - lastUpdate) > 100l) {

            if (event.values[0] == 0)
                mediaPlayer.start();

            long diffTime = (curTime -lastUpdate);
            lastUpdate = curTime;

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
