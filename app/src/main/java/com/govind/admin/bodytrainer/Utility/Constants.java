package com.govind.admin.bodytrainer.Utility;

import com.govind.admin.bodytrainer.BuildConfig;

/**
 * Created by Admin on 15-Mar-19.
 */

public class Constants {
    public static final String PREF_NAME ="BodyTrainerApp_pref";
    public static final String OPENED_STATUS ="opened";
    public static final String DEVICE_TOKEN ="device_token";
    public static final String API_BASE_EXT ="includes/";
    public static String tricep = "Triceps";
    public static String biceps = "Biceps";
    public static String chest = "Chest";
    public static String shoulder = "Shoulder";
    public static String abs = "Abs";
    public static String back = "Back";
    public static String forearm = "ForeArm";
    public static String uperleg = "UpperLeg";
    public static String lowerleg = "LowerLeg";
    public static String gluts = "Glutes";
    public static String cardio = "Cardio";
    public static String seeall = "SeeAll";
    public static String TUEWORKOUT_URL="tueworkout.php";
    public static String USER_LOGIN="Userlogingym.php";
    public static final String REGISTER_URL ="userregistrationgym.php";

    public static String Prod_Base_Url="http://www.justbathinda.com/";
    public static String Dev_Base_Url="http://www.justbathinda.com/";


    public static String getBaseURL() {
        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("release")) {
            return Prod_Base_Url+API_BASE_EXT;
        } else {
            return Dev_Base_Url+API_BASE_EXT;
        }
    }
    public static String getImageBaseURL() {
        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("release")) {
            return Prod_Base_Url;
        } else {
            return Dev_Base_Url;
        }
    }
}
