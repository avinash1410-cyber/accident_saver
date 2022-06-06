package com.restart.telephony;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.app.AlertDialog;
import android.util.Log;


public class PermissionUtils {

    //--------------------------------------------------
    // Permissions Methods
    //--------------------------------------------------

    public static boolean hasPermission(Context activity, String permission) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d("in has permission","check for version");
            return (PackageManager.PERMISSION_GRANTED == activity.checkSelfPermission(permission));
        }
        return false;
    }

    public static void alertAndFinish(final Activity activity) {
        Log.d("in has Alert","AlertBuilding");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.app_name).setMessage(activity.getString(R.string.permissions_denial));

        // Add the buttons.
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static Boolean canAccessCoarseLocation(Activity activity) {
        return (PermissionUtils.hasPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION));
    }

    public static Boolean canReadPhoneState(Activity activity) {
        Log.d("in canReadPhoneState","getting permission");
        return (PermissionUtils.hasPermission(activity, Manifest.permission.READ_PHONE_STATE));
    }
}
