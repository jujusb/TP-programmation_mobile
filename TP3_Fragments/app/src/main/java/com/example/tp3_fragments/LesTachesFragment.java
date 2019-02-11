package com.example.tp3_fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LesTachesFragment extends Fragment {

    private List<Tache> lesDonnes;
    private TacheAdapter adapter;
    private RecyclerView rvTache;

    private LesTachesInterface lesTachesInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_tache, container, false);

        this.lesDonnes = new ArrayList<>();
        this.adapter = new TacheAdapter(lesDonnes);
        this.rvTache = (RecyclerView) v.findViewById(R.id.recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(inflater.getContext());
        rvTache.setLayoutManager(mLayoutManager);
        rvTache.setItemAnimator(new DefaultItemAnimator());
        rvTache.addItemDecoration(
                new DividerItemDecoration(inflater.getContext(), LinearLayoutManager.VERTICAL));

        rvTache.setAdapter(this.adapter);


        return v;
    }

    public void ajoutTache(Tache t) {

        this.lesDonnes.add(t);

    }

    public void onAttach(Context context){
        super.onAttach(context);
        Log.d("fragment","nous venons d'attacher l'activite a LesTachesFragments");
        if(context instanceof LesTachesInterface) lesTachesInterface =
                (LesTachesInterface) context;
    }
}
