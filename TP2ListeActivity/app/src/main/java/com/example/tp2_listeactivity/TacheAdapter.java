package com.example.tp2_listeactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TacheAdapter extends BaseAdapter {

    private List<Tache> mesDonnees;
    private LayoutInflater inflater;

    public TacheAdapter(Context context, List<Tache> mesDonnees) {
        inflater = LayoutInflater.from(context);
        this.mesDonnees = mesDonnees;
    }

    @Override
    public int getCount() {
        return this.mesDonnees.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mesDonnees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View res = inflater.inflate(R.layout.tache,null);
        // mise à jour des champs de la vue
        TextView texte_tache   = (TextView) res.findViewById(R.id.texte_tache);
        texte_tache.setText(mesDonnees.get(i).getNom());


        return res;
    }
}
