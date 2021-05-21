package com.govind.admin.bodytrainer.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.govind.admin.bodytrainer.BodyTrainerApp;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Admin on 26-Mar-19.
 */

public class FirebaseGetDeviceToken {
    private static final String TAG = FirebaseGetDeviceToken.class.getName();
    static String deviceToken;

    public static String getDeviceToken() {
        deviceToken = FirebaseInstanceId.getInstance().getToken();
//        Log.e("devicetok",deviceToken);
        saveInSharedPref(deviceToken);

        return (deviceToken);
    }


    public static void saveInSharedPref(String deviceToken) {
        if(deviceToken!=null) {
            SharedPreferences   preferences = BodyTrainerApp.getInstance().getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
            preferences.edit().putString(Constants.DEVICE_TOKEN, deviceToken).apply();

        }
    }
}
