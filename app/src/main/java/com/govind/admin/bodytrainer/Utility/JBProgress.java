package com.govind.admin.bodytrainer.Utility;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Admin on 23-Mar-19.
 */

public class JBProgress {
    private static ProgressDialog progressDialog;

    public static void showProgressBar(Context context, String msg) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    public static void cancelProgressBar() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
