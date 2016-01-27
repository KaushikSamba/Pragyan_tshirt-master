package org.pragyan.pragyantshirtapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;

public class CouponDialogFragment extends DialogFragment {
    Context context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_layout, null))
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("Go to Freecharge now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openFreechargeApp();
                    }
                });
        return builder.create();
    }

    private void openFreechargeApp() {
        Intent i;
        String packageName = "com.freecharge.android";
        PackageManager manager = context.getPackageManager();
        try {
            i = manager.getLaunchIntentForPackage(packageName);     //Launches app if it is installed on the device
            if (i == null) {
                throw new PackageManager.NameNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));    //Takes user to Play Store to download app
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }
}
