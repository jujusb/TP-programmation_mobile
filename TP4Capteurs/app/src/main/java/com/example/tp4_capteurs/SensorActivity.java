package com.example.tp4_capteurs;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;

    private LinearLayout layoutDetails;
    private TextView nomCapteur;
    private ListView listeInformations;

    private DetailAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sensor);

        layoutDetails = (LinearLayout) findViewById(R.id.layoutDetails);
        nomCapteur = (TextView) findViewById(R.id.nomCapteur);
        listeInformations = (ListView) findViewById(R.id.listeInformations);

        Intent intent = getIntent();
        nomCapteur.setText(intent.getStringExtra("nom_capteur"));

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        final List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s : deviceSensors) {
            if (s.getName().equals(nomCapteur.getText().toString()))
                sensor = s;
        }

        List<String> details = new ArrayList<>();
        details.add("Vendor : " + sensor.getVendor());
        details.add("Power : " + sensor.getPower());
        details.add("Version : " + sensor.getVersion());
        details.add("Resolution : " + sensor.getResolution());

        if ( sensor.isWakeUpSensor() )
            details.add("Wake up ? true");
        else
            details.add("Wake up ? false");

        this.detailAdapter = new DetailAdapter(this, details);

        listeInformations.setAdapter(this.detailAdapter);

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            FragmentAccelerometre fragment = new FragmentAccelerometre(sensorManager);
            sensorManager.registerListener(fragment, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            fragmentTransaction.add(R.id.layoutDetails, fragment);
            fragmentTransaction.commit();

        } else if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            FragmentProximite fragment = new FragmentProximite(sensorManager);
            sensorManager.registerListener(fragment, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            fragmentTransaction.add(R.id.layoutDetails, fragment);
            fragmentTransaction.commit();
        }

    }


}
