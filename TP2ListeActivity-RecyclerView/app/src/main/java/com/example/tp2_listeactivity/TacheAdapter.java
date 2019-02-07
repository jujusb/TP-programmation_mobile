package com.example.tp2_listeactivity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.tp2_listeactivity.Tache.Categorie.Courses;

public class TacheAdapter extends RecyclerView.Adapter<TacheAdapter.MyViewHolder> {

    private List<Tache> mesDonnees;

    public TacheAdapter(Context context, List<Tache> mesDonnees) {
        this.mesDonnees = mesDonnees;
    }
    /*
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

        ImageView image = (ImageView) res.findViewById(R.id.image_tache);
        switch (mesDonnees.get(i).getCategorie()) {

            case Travail:
                image.setImageResource(R.drawable.travail);
                break;
            case Sport:
                image.setImageResource(R.drawable.sport);
                break;
            case Menage:
                image.setImageResource(R.drawable.menage);
                break;
            case Lecture:
                image.setImageResource(R.drawable.lecture);
                break;
            case Enfants:
                image.setImageResource(R.drawable.enfant);
                break;
            case Courses:
                image.setImageResource(R.drawable.courses);
                break;
            default:
                image.setImageResource(R.drawable.point_interro_);
        }

        return res;
    }
    */
    public class MyViewHolder extends RecyclerView.ViewHolder {
                // mise à jour des champs de la vue
        TextView nomTache;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomTache = (TextView) itemView.findViewById(R.id.texte_tache);
            image = (ImageView) itemView.findViewById(R.id.image_tache);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
            View tacheView= layoutInflater.inflate(R.layout.tache,parent,false);
            return new MyViewHolder(tacheView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Tache tache = mesDonnees.get(position);
            holder.nomTache.setText(tache.getNom());
            switch (tache.getCategorie()) {
                case Travail:
                    image.setImageResource(R.drawable.travail);
                    break;
                case Sport:
                    image.setImageResource(R.drawable.sport);
                    break;
                case Menage:
                    image.setImageResource(R.drawable.menage);
                    break;
                case Lecture:
                    image.setImageResource(R.drawable.lecture);
                    break;
                case Enfants:
                    image.setImageResource(R.drawable.enfant);
                    break;
                case Courses:
                    image.setImageResource(R.drawable.courses);
                    break;
                default:
                    image.setImageResource(R.drawable.point_interro_);
            }
        }

        @Override
        public int getItemCount() {
            return mesDonnees.size();
        }
    }
}
