package com.example.tp1_nombremystere;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rand;

    private int nombreADeviner;
    private int nbCoupsRestants;
    private int nbCoupsTotal;
    private int nombreMax;

    private EditText edit_nombre;
    private TextView texte_historique;
    private TextView texte_coups_restants;
    private Button button_ok;
    private Button button_rejouer;
    private TextView texte_perdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        rand = new Random();
        nombreMax = 10000;
        nombreADeviner = rand.nextInt(nombreMax);
        nbCoupsTotal = 15;
        nbCoupsRestants = nbCoupsTotal;

        edit_nombre = (EditText) findViewById(R.id.edit_nombre);
        texte_historique = (TextView) findViewById(R.id.texte_historique);
        texte_coups_restants = (TextView) findViewById(R.id.texte_coups_restants);
        button_ok = (Button) findViewById(R.id.button_ok);
        button_rejouer = (Button) findViewById(R.id.button_rejouer);

        texte_perdu = (TextView) findViewById(R.id.texte_perdu);

        texte_coups_restants.setText(""+nbCoupsRestants);

    }

    public void ok_clicked(View view) {
        String str = edit_nombre.getText().toString();
        if ( !str.equals("") ) {

            nbCoupsRestants--;
            texte_coups_restants.setText(""+nbCoupsRestants);

            if (nbCoupsRestants == 0) {
                button_ok.setEnabled(false);
                texte_perdu.setVisibility(View.VISIBLE);
                texte_perdu.append(""+nombreADeviner);
            }

            int nb_entre = Integer.parseInt(edit_nombre.getText().toString());
            if (nb_entre < nombreADeviner) {
                texte_historique.append(nb_entre + " est trop petit\n");
            } else if (nb_entre > nombreADeviner) {
                texte_historique.append(nb_entre + " est trop grand\n");
            } else {
                texte_historique.append(nb_entre + " était le nombre à deviner, et vous l'avez deviné en "+(nbCoupsTotal-nbCoupsRestants)+" coups\n");
                texte_perdu.setVisibility(View.VISIBLE);
                texte_perdu.setText("Bravo!!! Le nombre était bien "+nombreADeviner+", et vous l'avez deviné en "+(nbCoupsTotal-nbCoupsRestants)+ " coups");

            }
        }
    }

    public void rejouer_clicked(View view) {
        nombreADeviner = rand.nextInt(nombreMax);
        nbCoupsRestants = nbCoupsTotal;
        texte_perdu.setVisibility(View.INVISIBLE);
        button_ok.setEnabled(true);
        texte_perdu.setText(R.string.texte_perdu);
        texte_coups_restants.setText(""+nbCoupsRestants);
        texte_historique.setText("");
        edit_nombre.setText("");
    }

    public void quitter_clicked(View view) {
        finish();
        moveTaskToBack(true);
    }

}
