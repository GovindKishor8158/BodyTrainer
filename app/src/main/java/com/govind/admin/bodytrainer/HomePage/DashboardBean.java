package com.govind.admin.bodytrainer.HomePage;

import android.graphics.drawable.Drawable;

/**
 * Created by Admin on 15-Mar-19.
 */

public class DashboardBean {
    private String title;
    private Drawable mIcon;
    private int mColor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getMIcon() {
        return mIcon;
    }

    public void setMIcon(Drawable mIcon) {
        this.mIcon = mIcon;
    }

    public int getTxtColor() {
        return mColor;
    }

    public void setTxtColor(int mColor) {
        this.mColor = mColor;
    }
}


