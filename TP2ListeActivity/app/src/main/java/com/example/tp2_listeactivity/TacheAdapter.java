package com.example.tp2_listeactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class TacheAdapter extends BaseAdapter {

    private List<Tache> mesDonnees;
    private LayoutInflater inflater;

    public TacheAdapter(Context context, List<Tache> mesDonnees) {
        this.mesDonnees = mesDonnees;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
