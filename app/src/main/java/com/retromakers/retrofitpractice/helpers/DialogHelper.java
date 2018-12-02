package com.retromakers.retrofitpractice.helpers;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogHelper {
    public static Dialog getProgressDialog(Context context) {
        final Dialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setTitle("Loading");
        ((ProgressDialog) dialog).setMessage("Please Wait...");
        return dialog;
    }
}
