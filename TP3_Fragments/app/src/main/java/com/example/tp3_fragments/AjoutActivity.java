package com.example.tp3_fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AjoutActivity extends AppCompatActivity {
    TextView text_nom;
    TextView text_duree;
    TextView text_categorie;
    TextView text_description;
    Button ok_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_tache);

        // Association des vues du layout
        text_nom=(TextView) findViewById(R.id.text_nom);
        text_duree=(TextView) findViewById(R.id.text_duree);
        text_categorie=(TextView) findViewById(R.id.text_categorie);
        text_description=(TextView) findViewById(R.id.text_description);
        ok_button=(Button) findViewById(R.id.button_ok);

    }

    /**
     * Méthode appelée lors de la validation de la création d'une tâche
     * Le résultat est envoyé à l'activité 'MainActivity'
     */
    public void finish() {
        Intent data = new Intent();
        data.putExtra("nom",text_nom.getText().toString());
        data.putExtra("categorie",text_categorie.getText().toString());
        data.putExtra("duree",text_duree.getText().toString());
        data.putExtra("description",text_description.getText().toString());
        setResult(MainActivity.RESULT_OK, data);
        super.finish();
    }

    /**
     * Méthode associée au bouton de validation de la création d'une tâche
     * @param view La vue associée
     */
    public void button_ok_clicked(View view) {
        finish();
    }
}
