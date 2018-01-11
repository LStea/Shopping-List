package com.example.lesze.myapplication.tools;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.widget.Toast;

/**
 * Created by lesze on 25.11.2017.
 */

public class MyAlertDialog extends AlertDialog {

    protected MyAlertDialog(@NonNull Context context) {
        super(context);
    }

    private void displayDialog(final int selected){
        AlertDialog.Builder  builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Alert");
        builder.setMessage("Do you really want to delete this entry?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // proceed delete

                Toast.makeText(null, "Successfully deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // proceed cancel
            }
        });
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Tekst zapisujemy do zmiennej globalnej i w metodzie OnCreate()

    // przypisujemy do odpowiedniej kontrolki EditText: editText.setText(txt);
}

