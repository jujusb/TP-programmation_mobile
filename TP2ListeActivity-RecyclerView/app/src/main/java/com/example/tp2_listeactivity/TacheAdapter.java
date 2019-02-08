package com.example.tp2_listeactivity;

import android.content.Context;
import android.content.Intent;
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

    public TacheAdapter(List<Tache> mesDonnees) {
        this.mesDonnees = mesDonnees;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View tacheView = layoutInflater.inflate(R.layout.tache,parent,false);

        return new MyViewHolder(tacheView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tache tache = mesDonnees.get(position);
        holder.nomTache.setText(tache.getNom());
        switch (tache.getCategorie()) {
            case Travail:
                holder.image.setImageResource(R.drawable.travail);
                break;
            case Sport:
                holder.image.setImageResource(R.drawable.sport);
                break;
            case Menage:
                holder.image.setImageResource(R.drawable.menage);
                break;
            case Lecture:
                holder.image.setImageResource(R.drawable.lecture);
                break;
            case Enfants:
                holder.image.setImageResource(R.drawable.enfant);
                break;
            case Courses:
                holder.image.setImageResource(R.drawable.courses);
                break;
            default:
                holder.image.setImageResource(R.drawable.point_interro_);
        }
    }

    public int getItemCount() {
        return mesDonnees.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
                // mise à jour des champs de la vue
        public TextView nomTache;
        public ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomTache = (TextView) itemView.findViewById(R.id.texte_tache);
            image = (ImageView) itemView.findViewById(R.id.image_tache);
        }

    }
}
