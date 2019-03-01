package com.example.tp3_fragments;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements LesTachesInterface {

    LesTachesFragment lesTachesFragment;
    DetailFragment detailFragment;

    public static final int CODE_AJOUT_ACTIVITE = 1;
    public static final int RESULT_OK = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bouton '+' pour l'ajout de tâches
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AjoutActivity.class);
                startActivityForResult(intent, CODE_AJOUT_ACTIVITE);
            }
        });


        // Gestion des fragments
        FragmentManager fm = this.getFragmentManager();

        lesTachesFragment = (LesTachesFragment) fm.findFragmentById(R.id.fragment_list_tache);
        if (savedInstanceState == null) {
            lesTachesFragment.ajoutTache(new Tache("TP3", "Travail", "240", "TP3 Fragments réalisé par Steeve Doppler et Julio Santilario-Berthilier"));
            lesTachesFragment.ajoutTache(new Tache("Ping", "Sport", "90", "Entrainement de tennis de table"));
            lesTachesFragment.ajoutTache(new Tache("Volley", "Sport", "90", "Entrainement de volley"));
            lesTachesFragment.ajoutTache(new Tache("Proba", "Travail", "120", "Les probas, c'est cool"));
        }

        detailFragment = (DetailFragment) fm.findFragmentById(R.id.fragment_detail_tache);

        boolean twoPanes = getResources().getBoolean(R.bool.twoPane);
        if (twoPanes)
            tacheSelectionnee(lesTachesFragment.getTache(0));

    }

    /**
     * Méthode qui envoie les informations d'une tâche au DetailFragment
     * @param t La tâche selectionnée
     */
    @Override
    public void tacheSelectionnee(Tache t) {

        Log.d("notreFragment", "tacheSelectionnee: " + t.getNom() + " a été selectionnée ");


        // Si twoPanes est vrai, alors on utilise deux fragments dans MainActivity
        boolean twoPanes = getResources().getBoolean(R.bool.twoPane);

        if (twoPanes) {
            detailFragment.setTache(t);
        } else {
            // Création d'une activité DetailTacheActivity avec les détails de la tache selectionnée
            Intent intent = new Intent(this, DetailTacheActivity.class);

            intent.putExtra("Titre", t.getNom());
            intent.putExtra("Duree", t.getDuree());
            intent.putExtra("Categorie", t.getCategorie().toString());
            intent.putExtra("Desc", t.getDescription());

            startActivity(intent);
        }

    }


    /**
     * Methode qui permet de récupérer les résultats pour la création d'une nouvelle tâche
     * @param requestCode Code de l'activité
     * @param resultCode Code de résultat (RESULT_OK si tout c'est bien passé)
     * @param data Intent qui contient le résultat de l'activité de création d'une tâche
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if((resultCode==RESULT_OK)&&(requestCode==CODE_AJOUT_ACTIVITE)) {
            Tache t = new Tache(
                    data.getStringExtra("nom"),
                    data.getStringExtra("categorie"),
                    data.getStringExtra("duree"),
                    data.getStringExtra("description")
            );
            lesTachesFragment.ajoutTache(t);
        }
    }


}
