package com.example.tp3_fragments;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailTacheActivity extends AppCompatActivity {

    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_tache);

        // Récupération du DetailFragment
        FragmentManager fm = this.getFragmentManager();
        detailFragment = (DetailFragment) fm.findFragmentById(R.id.fragment_detail_tache);


        Intent intent = this.getIntent();
        // On envoie la tache à notre DetailFragment à partir de l'Intent
        detailFragment.setTache(
                intent.getStringExtra("Titre")+"",
                intent.getStringExtra("Desc")+"",
                intent.getIntExtra("Duree", 0)+"",
                intent.getStringExtra("Categorie")+"");

    }

}
