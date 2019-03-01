package com.example.tp4_capteurs;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SensorAdapter extends BaseAdapter {

    private List<Sensor> deviceSensors;
    private LayoutInflater inflater;

    public SensorAdapter(Context context, List<Sensor> deviceSensors) {
        this.deviceSensors = deviceSensors;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return deviceSensors.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceSensors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View res = inflater.inflate(R.layout.item_capteurs,null);
        TextView nomCapteurs = (TextView) res.findViewById(R.id.nomCapteurs);

        nomCapteurs.setText( ((Sensor) getItem(position)).getName() );

        return res;
    }
}
