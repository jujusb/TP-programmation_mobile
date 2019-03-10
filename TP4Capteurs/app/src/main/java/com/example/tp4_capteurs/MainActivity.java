package com.example.tp4_capteurs;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * TP réalisé par Steeve Doppler et Julio Santilario-Berthilier
 */

public class MainActivity extends AppCompatActivity {


    private SensorManager sensorManager;

    private ListView listeCapteurs;
    //private ArrayAdapter<Sensor> adapter;
    private SensorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        final List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        listeCapteurs = (ListView) findViewById(R.id.listeCapteurs);
        //adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_capteurs,  R.id.capteurs,deviceSensors);
        adapter = new SensorAdapter(getApplicationContext(), deviceSensors);
        listeCapteurs.setAdapter(adapter);

        // Si on clique sur un item, on ouvre une nouvelle activité
        listeCapteurs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SensorActivity.class);
                intent.putExtra("nom_capteur", deviceSensors.get(position).getName());
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
