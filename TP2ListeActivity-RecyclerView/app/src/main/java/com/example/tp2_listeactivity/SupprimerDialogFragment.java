package com.example.tp2_listeactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.List;

public class SupprimerDialogFragment extends DialogFragment {

    private List<Tache> list;
    private TacheAdapter adapter;
    private int index;

    public SupprimerDialogFragment(List<Tache> list, TacheAdapter adapter, int index) {
        this.list = list;
        this.adapter=adapter;
        this.index = index;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_suppression)
                .setPositiveButton(R.string.ok_suppression, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        list.remove(index);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel_suppression, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

}
