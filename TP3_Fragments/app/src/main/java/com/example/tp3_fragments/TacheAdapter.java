package com.example.tp3_fragments;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TacheAdapter extends RecyclerView.Adapter<TacheAdapter.MyViewHolder> {

    private List<Tache> mesDonnees;
    private LesTachesInterface lesTachesInterface;

    public TacheAdapter(List<Tache> mesDonnees,LesTachesInterface lesTachesInterface) {
        this.mesDonnees = mesDonnees;
        this.lesTachesInterface=lesTachesInterface;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View tacheView = layoutInflater.inflate(R.layout.tache,parent,false);

        return new MyViewHolder(tacheView);
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
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


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lesTachesInterface.tacheSelectionnee(mesDonnees.get(position));

                /*
                FragmentManager fm = this.getFragmentManager();
                DetailFragment detailFragment;
                detailFragment = (DetailFragment) fm.findFragmentById(R.id.fragment_list_tache);

                lesTachesFragment.ajoutTache(new Tache("Ping", "Sport", "90", "Entrainement de tennis de table"));
                */
                /*
                Intent intent = new Intent(v.getContext(), DetailTacheActivity.class);

                intent.putExtra("Titre", mesDonnees.get(position).getNom());
                intent.putExtra("Duree", mesDonnees.get(position).getDuree());
                intent.putExtra("Categorie", mesDonnees.get(position).getCategorie().toString());
                intent.putExtra("Desc", mesDonnees.get(position).getDescription());

                v.getContext().startActivity(intent);
                */
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogFragment newFragment = new SupprimerDialogFragment(mesDonnees, TacheAdapter.this, position);
                //newFragment.setTargetFragment(this, RESULT_OK);
                newFragment.show( ((AppCompatActivity) v.getContext()).getSupportFragmentManager() , "suppression");

                return true;
            }
        });


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
