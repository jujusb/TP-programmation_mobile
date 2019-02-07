package com.example.tp2_listeactivity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //public final String NOM_ACTIVITE;
    //public final String DUREE_ACTIVITE;
    //public final String DESC_ACTIVITE;
    //public final String CATEGORIE_ACTIVITE;
    public final int CODE_AJOUT_ACTIVITE = 1;
    public static final int RESULT_OK = 0;
    //public final int RESULT_KO;
    public static int supprimerTache=0;

    private List<Tache> mesDonnees;
    private TacheAdapter adapter;

    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Création et ajout d'élément à la liste de tâches
        this.mesDonnees = new ArrayList<>();

        this.mesDonnees.add(new Tache("Ping", "Sport", "90", "Entrainement de tennis de table"));
        this.mesDonnees.add(new Tache("Volley", "Sport", "90", "Entrainement de volley"));
        this.mesDonnees.add(new Tache("Proba", "Travail", "120", "Les probas, c'est cool"));

        // Création de l'adapter pour la liste de tâches
        this.adapter = new TacheAdapter(getApplicationContext(), this.mesDonnees);

        // Association de l'adapter à notre vue ListView
        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(this.adapter);

        // Permet de lancer une nouvelle activité qui montre toutes les informations relatives
        // à la tâche sélectionnée
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailTacheActivity.class);

                intent.putExtra("Titre", mesDonnees.get(position).getNom());
                intent.putExtra("Duree", mesDonnees.get(position).getDuree());
                intent.putExtra("Categorie", mesDonnees.get(position).getCategorie().toString());
                intent.putExtra("Desc", mesDonnees.get(position).getDescription());

                startActivity(intent);
            }
        });

        // Permet la suppression d'un élément de la liste en restant appuyé dessus
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogFragment newFragment = new SupprimerDialogFragment(mesDonnees, adapter, position);
                //newFragment.setTargetFragment(this, RESULT_OK);
                newFragment.show(getSupportFragmentManager(), "suppression");

                return true;
            }
        });

        // Création du bouton '+' qui permet de créer une nouvelle tâche
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AjoutActivity.class);
                startActivityForResult(intent,CODE_AJOUT_ACTIVITE);
            }
        });
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
            mesDonnees.add(t);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


}
