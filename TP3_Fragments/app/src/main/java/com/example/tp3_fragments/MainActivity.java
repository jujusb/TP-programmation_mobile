package com.example.tp3_fragments;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements LesTachesInterface {

    DetailFragment detailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        FragmentManager fm = this.getFragmentManager();

        LesTachesFragment lesTachesFragment;
        lesTachesFragment = (LesTachesFragment) fm.findFragmentById(R.id.fragment_list_tache);

        lesTachesFragment.ajoutTache(new Tache("Ping", "Sport", "90", "Entrainement de tennis de table"));
        lesTachesFragment.ajoutTache(new Tache("Volley", "Sport", "90", "Entrainement de volley"));
        lesTachesFragment.ajoutTache(new Tache("Proba", "Travail", "120", "Les probas, c'est cool"));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void tacheSelectionnee(Tache t) {

        Log.d("notreFragment", "tacheSelectionnee: " + t.getNom() + " a été selectionnée ");

        //detailFragment.setTache(t);

        // Création d'une activité DetailTacheActivity avec les détails de la tache selectionnée
        Intent intent = new Intent(this, DetailTacheActivity.class);

        intent.putExtra("Titre", t.getNom());
        intent.putExtra("Duree", t.getDuree());
        intent.putExtra("Categorie", t.getCategorie().toString());
        intent.putExtra("Desc", t.getDescription());

        startActivity(intent);

    }
}
