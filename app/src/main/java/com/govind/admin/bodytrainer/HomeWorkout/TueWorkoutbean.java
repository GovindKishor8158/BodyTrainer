package com.govind.admin.bodytrainer.HomeWorkout;

import com.govind.admin.bodytrainer.Utility.Constants;
import com.govind.admin.bodytrainer.Utility.DebugLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 23-Mar-19.
 */

public class TueWorkoutbean {
    private static String ID="es_id";
    private static String IMAGE="es_gifimage";
    private static String WORKNAME="es_workname";
    private static String SETSNO="es_sets";

    /**
     * es_id : 1
     * es_workname : tue1
     * es_sets : tue1
     * es_gifimage : closepushup.gif
     */

    private String es_id;
    private String es_workname;
    private String es_sets;
    private String es_gifimage;

    public String getEs_id() {
        return es_id;
    }

    public void setEs_id(String es_id) {
        this.es_id = es_id;
    }

    public String getEs_workname() {
        return es_workname;
    }

    public void setEs_workname(String es_workname) {
        this.es_workname = es_workname;
    }

    public String getEs_sets() {
        return es_sets;
    }

    public void setEs_sets(String es_sets) {
        this.es_sets = es_sets;
    }

    public String getEs_gifimage() {
        return es_gifimage;
    }

    public void setEs_gifimage(String es_gifimage) {
        this.es_gifimage = es_gifimage;
    }

    public static ArrayList<TueWorkoutbean> parseHWArray(JSONArray arrayObj) {
        ArrayList<TueWorkoutbean> list = new ArrayList<TueWorkoutbean>();
        try {

            for (int i = 0; i < arrayObj.length(); i++) {
                TueWorkoutbean p = parseHWObject(arrayObj.getJSONObject(i));
                if (p != null) {
                    list.add(p);
                }
            }
        } catch (Exception e) {
            DebugLog.printLog("exp",e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public static TueWorkoutbean parseHWObject(JSONObject jsonObject) {
        TueWorkoutbean casteObj = new TueWorkoutbean();
        try {
            if (jsonObject.has(ID)) {
                casteObj.setEs_id(jsonObject.getString(ID));
            }
            if (jsonObject.has(IMAGE) && !jsonObject.getString(IMAGE).isEmpty() && !jsonObject.getString(IMAGE).equalsIgnoreCase("null")) {
                casteObj.setEs_gifimage(Constants.getImageBaseURL()+"gifimages/"+jsonObject.getString(IMAGE));
            }
            if (jsonObject.has(WORKNAME) && !jsonObject.getString(WORKNAME).isEmpty() && !jsonObject.getString(WORKNAME).equalsIgnoreCase("null")) {
                casteObj.setEs_workname(jsonObject.getString(WORKNAME));
            }
            if (jsonObject.has(SETSNO) && !jsonObject.getString(SETSNO).isEmpty() && !jsonObject.getString(SETSNO).equalsIgnoreCase("null")) {
                casteObj.setEs_sets(jsonObject.getString(SETSNO));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return casteObj;
    }


}
