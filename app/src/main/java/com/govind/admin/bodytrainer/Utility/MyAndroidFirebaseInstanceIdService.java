package com.govind.admin.bodytrainer.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.govind.admin.bodytrainer.BodyTrainerApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 23-Mar-19.
 */

public class MyAndroidFirebaseInstanceIdService extends FirebaseInstanceIdService  {
    String deviceToken;
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.

        deviceToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + deviceToken);
        saveInSharedPref(deviceToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

    }
    public static void saveInSharedPref(String deviceToken) {
        SharedPreferences preferences = BodyTrainerApp.getInstance().getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(Constants.DEVICE_TOKEN, deviceToken).commit();
    }
}
