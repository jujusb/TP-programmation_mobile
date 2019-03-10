package com.example.tp4_capteurs;

import android.annotation.SuppressLint;
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

    private SensorManager sensorManager;
    private MediaPlayer mediaPlayer;

    @SuppressLint("ValidFragment")
    public FragmentProximite(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_proximite, container, false);

        // Association de la vue
        distance = (TextView) view.findViewById(R.id.distance);

        // Initialisation d'un son
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.sabreclash);

        lastUpdate = System.currentTimeMillis();
        return view;
    }

    /**
     * Méthode qui met à jour les zones de textes du fragment
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

        distance.setText("Distance : " + event.values[0]);


        long curTime = System.currentTimeMillis();

        if ((curTime - lastUpdate) > 100L) {

            if (event.values[0] == 0)
                mediaPlayer.start();

            lastUpdate = curTime;

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d("Accelerometer", "onAccuracyChanged: "+accuracy);
    }

    /**
     * Méthode qui libère les ressources
     */
    @Override
    public void onDetach() {
        super.onDetach();
        sensorManager.unregisterListener(this);
        mediaPlayer.release();
    }
}
