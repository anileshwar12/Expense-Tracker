package com.acdevs.expensetracker;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.fragment.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(new ProgressBar(context));
        builder.setMessage("Processing...");
        builder.setCancelable(false);

        return builder.create();
    }
}
