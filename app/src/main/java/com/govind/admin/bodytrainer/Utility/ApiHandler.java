package com.govind.admin.bodytrainer.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.govind.admin.bodytrainer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 23-Mar-19.
 */

public class ApiHandler {

    public static final int ERROR_PARSING = 1001;
    public static final int ERROR_VOLLEY = 1002;
    public static final int OBJECT_NOT_FOUND = 401;
    public static final int API_REQUIRED_FIELDS = 400;
    public static final int API_WRONG_METHOD = 405;
    public static final int UNAUTHORIZED_ACCESS = 402;
    public static final int SUCCESS = 200;

    public static interface ApiCallback {
        void onDataFetched(JSONObject jsonObject, ApiHandlerError error);
    }

    public static void apiHit(int requestMethod, String url, final ApiCallback apiCallback, final Context context, final Map<String, String> params) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            StringRequest stringRequest = new StringRequest(requestMethod, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                DebugLog.printLog("api res: ", response);
                                JSONObject jsonObject = new JSONObject(response);
                                String status_code = jsonObject.getString("status_code");
                                if (apiCallback != null) {
                                    if (status_code.equalsIgnoreCase(String.valueOf(SUCCESS))) {
                                        if (apiCallback != null)
                                            apiCallback.onDataFetched(jsonObject, null);
                                    } else {

                                        ApiHandlerError apiHandlerError = new ApiHandlerError(Integer.valueOf(status_code), com.govind.admin.bodytrainer.Utility.Utils.stringWithFirstcap(jsonObject.getString("error_msg")));

                                        apiCallback.onDataFetched(null, apiHandlerError);
                                    }
                                }
                            } catch (JSONException e) {

                                ApiHandlerError apiHandlerError = new ApiHandlerError(ApiHandler.ERROR_PARSING, context.getString(R.string.error_parse));

                                apiCallback.onDataFetched(null, apiHandlerError);
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            if (apiCallback != null) {
                                int errorCode = ApiHandler.ERROR_VOLLEY;
                                if (error.networkResponse != null && error.networkResponse.statusCode == 500) {
                                    errorCode = 500;
                                }
                                ApiHandlerError apiHandlerError = new ApiHandlerError(errorCode, getError(context, error));
                                apiCallback.onDataFetched(null, apiHandlerError);
                            }
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    if (params != null) {
                        return params;
                    } else {
                        return new HashMap<>();
                    }
                }


            };
            if (context != null) {
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 6, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Volley.newRequestQueue(context).add(stringRequest);
            }
        } else {
            if (apiCallback != null) {
                ApiHandlerError error = new ApiHandlerError(ERROR_VOLLEY, "Please check internet connectivity.");
                apiCallback.onDataFetched(null, error);
            }
        }
    }

    private static String getError(Context context, VolleyError error) {
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            return context.getString(R.string.error_network_timeout);
        } else if (error instanceof AuthFailureError) {
            return context.getString(R.string.authfailure);
        } else if (error instanceof ServerError) {
            return context.getString(R.string.error_server);
        } else if (error instanceof NetworkError) {
            return context.getString(R.string.error_network);
        } else if (error instanceof ParseError) {
            return context.getString(R.string.error_parse);
        }
        return "";
    }
}
