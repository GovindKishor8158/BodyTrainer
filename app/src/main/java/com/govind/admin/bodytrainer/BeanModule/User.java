package com.govind.admin.bodytrainer.BeanModule;

import android.content.Context;
import android.content.SharedPreferences;

import com.govind.admin.bodytrainer.BodyTrainerApp;
import com.govind.admin.bodytrainer.Utility.Constants;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 19-Mar-19.
 */

public class User {

    private static User sInstance;
    private static String PREFRENCES_USER = "user";
    private static String ID = "id";
    private static String NAME = "name";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";
    private static String MOBILE_NO = "mobileno";
    private static String LOCATION = "location";
    private static String HEIGHT = "height";
    private static String DEVICE_TOKEN = "device_token";
    private static String WEIGHT = "weight";
    private static String GENDER = "gender";
    private static String BIRTHDATE = "birth_date";
    private static String TYPE = "type";
    private static String USER_TYPE = "user_type";

    private String id;
    private String name;
    private String email;
    private String password;
    private String mobileno;
    private String user_type;

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    private String location;
    private int type;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    private String height;
    private String gender;
    private String device_token;
    private String weight;
    private String birth_date;

    public static void setCurrentUser(JSONObject jsonObject) {


        User user = getCurrentUser();
        try {

            if (user == null) {
                user = new User();
            }
            if (jsonObject.has(ID)) {
                user.setId(jsonObject.getString(ID));
            }

            if (jsonObject.has(PASSWORD) && !jsonObject.getString(PASSWORD).isEmpty() && !jsonObject.getString(PASSWORD).equalsIgnoreCase("null")) {
                user.setPassword(jsonObject.getString(PASSWORD));
            }
            if (jsonObject.has(MOBILE_NO) && !jsonObject.getString(MOBILE_NO).isEmpty() && !jsonObject.getString(MOBILE_NO).equalsIgnoreCase("null")) {
                user.setMobileno(jsonObject.getString(MOBILE_NO));
            }
            if (jsonObject.has(HEIGHT) && !jsonObject.getString(HEIGHT).isEmpty() && !jsonObject.getString(HEIGHT).equalsIgnoreCase("null")) {
                user.setHeight(jsonObject.getString(HEIGHT));
            }

            if (jsonObject.has(EMAIL) && !jsonObject.getString(EMAIL).isEmpty() && !jsonObject.getString(EMAIL).equalsIgnoreCase("null")) {
                user.setEmail(jsonObject.getString(EMAIL));
            }
            if (jsonObject.has(LOCATION) && !jsonObject.getString(LOCATION).isEmpty() && !jsonObject.getString(LOCATION).equalsIgnoreCase("null")) {
                user.setLocation(jsonObject.getString(LOCATION));
            }
            if (jsonObject.has(NAME) && !jsonObject.getString(NAME).isEmpty() && !jsonObject.getString(NAME).equalsIgnoreCase("null")) {
                user.setName(jsonObject.getString(NAME));
            }
            if (jsonObject.has(DEVICE_TOKEN) && !jsonObject.getString(DEVICE_TOKEN).isEmpty() && !jsonObject.getString(DEVICE_TOKEN).equalsIgnoreCase("null")) {
                user.setDevice_token(jsonObject.getString(DEVICE_TOKEN));
            }
            if (jsonObject.has(WEIGHT) && !jsonObject.getString(WEIGHT).isEmpty() && !jsonObject.getString(WEIGHT).equalsIgnoreCase("null")) {
                user.setWeight((jsonObject.getString(WEIGHT)));
            }
            if (jsonObject.has(GENDER) && !jsonObject.getString(GENDER).isEmpty() && !jsonObject.getString(GENDER).equalsIgnoreCase("null")) {
                user.setGender(jsonObject.getString(GENDER));
            }
            if (jsonObject.has(BIRTHDATE) && !jsonObject.getString(BIRTHDATE).isEmpty() && !jsonObject.getString(BIRTHDATE).equalsIgnoreCase("null")) {
                user.setBirth_date(jsonObject.getString(BIRTHDATE));
            }
            if (jsonObject.has(TYPE) && !jsonObject.getString(TYPE).isEmpty() && !jsonObject.getString(TYPE).equalsIgnoreCase("null")) {
                user.setType((jsonObject.getInt(TYPE)));
            }
            if (jsonObject.has(USER_TYPE) && !jsonObject.getString(USER_TYPE).isEmpty() && !jsonObject.getString(USER_TYPE).equalsIgnoreCase("null")) {
                user.setUser_type((jsonObject.getString(USER_TYPE)));
            }

            Gson gson = new Gson();
            String userString = gson.toJson(user, User.class);
            SharedPreferences preferences = BodyTrainerApp.getInstance().getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
            preferences.edit().putString(PREFRENCES_USER, userString).commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static User getCurrentUser() {
        if (sInstance == null) {
            SharedPreferences preferences = BodyTrainerApp.getInstance().getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
            String userString = preferences.getString(PREFRENCES_USER, null);
            if (userString != null) {
                Gson gson = new Gson();
                sInstance = gson.fromJson(userString, User.class);
                return sInstance;
            } else {
                return null;
            }
        } else {
            return sInstance;
        }
    }
    public static void logout() {
        sInstance = null;
        SharedPreferences pref = BodyTrainerApp.getInstance().getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(PREFRENCES_USER);
        editor.commit();


    }
}
