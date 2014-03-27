package com.example.myapplication.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.myapplication.R;

/**
 * Created by adibox on 3/27/14.
 */
public class TestDialog extends android.support.v4.app.DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Chalut");

        builder.setPositiveButton("ouiiiii", null);

        builder.setNegativeButton("nooooon", null);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.modal, null));

        return builder.create();
    }
}
