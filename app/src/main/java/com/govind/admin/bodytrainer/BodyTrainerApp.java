package com.govind.admin.bodytrainer;

import android.content.Context;
import android.content.SharedPreferences;


import androidx.multidex.MultiDexApplication;

import com.govind.admin.bodytrainer.Utility.Constants;
import com.govind.admin.bodytrainer.Utility.Utils;

/**
 * Created by Admin on 19-Mar-19.
 */

public class BodyTrainerApp extends MultiDexApplication {

    private static BodyTrainerApp mInstance;
    private static final String TAG = BodyTrainerApp.class.getName();
    private SharedPreferences preferences;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = BodyTrainerApp.this;
        preferences = BodyTrainerApp.this.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        setOpenedDialog(false);


    }

    public static synchronized BodyTrainerApp getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        mInstance = null;
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        Utils.deleteCache(this);
        super.onLowMemory();
    }

    public void setOpenedDialog(boolean value){
        preferences.edit().putBoolean(Constants.OPENED_STATUS,value).commit();
    }
    public boolean getOpenedDialog(){
        return preferences.getBoolean(Constants.OPENED_STATUS,true);
    }
}
