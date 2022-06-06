 package com.restart.telephony;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
//import android.os.Build;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.content.pm.PackageManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;


 public class MainActivity extends AppCompatActivity {

     //--------------------------------------------------
     // Constants
     //--------------------------------------------------

     private static final String[] PERMISSIONS = {
             Manifest.permission.READ_PHONE_STATE
            // Manifest.permission.ACCESS_COARSE_LOCATION
             };
     private static final int PERMISSION_REQUEST = 100;

     //--------------------------------------------------
     // Attributes
     //--------------------------------------------------

     TelephonyManager mTelephonyManager;

     //--------------------------------------------------
     // Activity Life Cycle
     //--------------------------------------------------

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         Log.d("On Create","Call for check permission");
         checkPermissions();
     }

     //--------------------------------------------------
     // Permissions
     //--------------------------------------------------

     @Override
     public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
         if (requestCode == PERMISSION_REQUEST)
                 isPermissionGranted(grantResults);

     }




    private void isPermissionGranted(int[] grantResults) {
         Log.d("Permission","in permission method");
         if (grantResults.length > 0) {
             boolean permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
             if (permissionGranted) {
                 Log.d("Permission","in permission already granted");
                 callPhoneManager();
                 }
             else {
                 Log.d("Permission","call for permission");
                 PermissionUtils.alertAndFinish(this);
             }
         }
     }

     private void checkPermissions() {
         Log.d("IN checkPermissions","Check for the Build version");
         // Checks the Android version of the device.

             Log.d("Version Comparison checked","Build version is OK");

             Boolean canWriteExternalStorage = PermissionUtils.canReadPhoneState(this);
             //Boolean canReadExternalStorage = PermissionUtils.canAccessCoarseLocation(this);
             if (!canWriteExternalStorage) {
                 Log.d("Permission REQ","permission request sent");
                 requestPermissions(PERMISSIONS, PERMISSION_REQUEST);
                 Log.d("Permission REQ","permission request get response");
             } else {
                 // Permission was granted.
                 Log.d("Permission REQ","canWriteExternalStorage already true");
                 callPhoneManager();
                 Log.d("In check permission","Call for callPhoneManager completed");
             }

//         else {
//             // Version is below Marshmallow.
//             Log.d("Version Comparison checked","version is Wrong(LOW)");
//             Log.d("In check permission","Call for callPhoneManager");
//             callPhoneManager();
//             Log.d("In check permission","Call for callPhoneManager completed");
//         }
     }

     private void callPhoneManager() {
         Log.d("In callPhoneManager","___");
         TextView textView = findViewById(R.id.id_text_view);
         mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

         Log.d("In callPhoneManager","call for the mTelephonyManager.listen");

         mTelephonyManager.listen(
                 new PhoneCallback(textView),
                 PhoneStateListener.LISTEN_CALL_STATE
//                 | PhoneStateListener.LISTEN_CELL_INFO // Requires API 17
//                 | PhoneStateListener.LISTEN_CELL_LOCATION
//                 | PhoneStateListener.LISTEN_DATA_ACTIVITY
//                 | PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
//                 | PhoneStateListener.LISTEN_SERVICE_STATE
//                 | PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
//                 | PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR
//                 | PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR
                         );

         Log.d("In callPhoneManager","After the mTelephonyManager.listen");
     }
 }
