package com.govind.admin.bodytrainer.Utility;

/**
 * Created by Admin on 23-Mar-19.
 */

public class ApiHandlerError {

    int statusCode;
    String message;

    ApiHandlerError(int code, String message) {
        statusCode = code;
        this.message = message;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
