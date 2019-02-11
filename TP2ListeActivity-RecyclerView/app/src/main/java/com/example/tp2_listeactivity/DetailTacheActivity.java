package com.example.tp2_listeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailTacheActivity extends AppCompatActivity {

    private TextView text_titre;
    private TextView text_duree;
    private ImageView image;
    private TextView text_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tache);

        Intent intent = this.getIntent();

        // Association des vues du Layout
        text_titre = (TextView) findViewById(R.id.text_titre);
        text_titre.setText(intent.getStringExtra("Titre"));

        text_duree = (TextView) findViewById(R.id.text_duree);
        text_duree.setText(intent.getIntExtra("Duree", 0)+"");

        // Association de l'image selon la catégorie
        image = (ImageView) findViewById(R.id.image_tache);

        switch ( intent.getStringExtra("Categorie") ) {
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



        text_desc = (TextView) findViewById(R.id.text_desc);
        text_desc.setText(intent.getStringExtra("Desc"));

    }
}
