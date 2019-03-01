package com.example.tp3_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    private View view;
    private Tache tache;
    private TextView text_titre;
    private TextView text_duree;
    private ImageView image;
    private TextView text_desc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // Association des vues au layout
        view = inflater.inflate(R.layout.fragment_detail_tache, container, false);
        text_titre = (TextView) view.findViewById(R.id.text_titre);
        text_duree = (TextView) view.findViewById(R.id.text_duree);
        image = (ImageView) view.findViewById(R.id.image_tache);
        text_desc = (TextView) view.findViewById(R.id.text_desc);

        return view;
    }

    public void setTache(Tache t) {
        tache = t;
        text_titre.setText(tache.getNom());
        text_desc.setText(tache.getDescription());
        text_duree.setText(tache.getDuree()+"");
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

    public void setTache(String nom, String description, String duree, String categorie) {
        text_titre.setText(nom);
        text_desc.setText(description);
        text_duree.setText(duree);
        switch (categorie) {
            case "Travail":
                image.setImageResource(R.drawable.travail);
                break;
            case "Sport":
                image.setImageResource(R.drawable.sport);
                break;
            case "Menage":
                image.setImageResource(R.drawable.menage);
                break;
            case "Lecture":
                image.setImageResource(R.drawable.lecture);
                break;
            case "Enfants":
                image.setImageResource(R.drawable.enfant);
                break;
            case "Courses":
                image.setImageResource(R.drawable.courses);
                break;
            default:
                image.setImageResource(R.drawable.point_interro_);
        }
    }

}
