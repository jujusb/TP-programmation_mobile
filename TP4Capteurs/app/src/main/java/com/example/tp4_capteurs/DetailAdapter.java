package com.example.tp4_capteurs;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DetailAdapter extends BaseAdapter {

    private List<String> deviceDetails;
    private LayoutInflater inflater;

    public DetailAdapter(Context context, List<String> deviceDetails) {
        this.deviceDetails = deviceDetails;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return deviceDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View res = inflater.inflate(R.layout.item_details,null);
        TextView nomCapteurs = (TextView) res.findViewById(R.id.detailCapteur);

        nomCapteurs.setText( (String) getItem(position) );

        return res;
    }
}
