package com.govind.admin.bodytrainer.DietChart;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Admin on 19-Mar-19.
 */

public class Pager extends FragmentPagerAdapter {

    int tabCount;

    private String[] tabTitles = new String[]{"Mon", "Tue","Wed","Thu","Fri","Sat","Sun"};

    public Pager(FragmentManager fm) {
        super(fm);
        this.tabCount = 7;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Monfrag tab1 = new Monfrag();
                return tab1;

            case 1:
                Tuefrag tab2 = new Tuefrag();
                return tab2;

            case 2:
                Wedfrag tab3 = new Wedfrag();
                return tab3;

            case 3:
                Thufrag tab4 = new Thufrag();
                return tab4;

            case 4:
                Fridfrag tab5 = new Fridfrag();
                return tab5;

            case 5:
                Satufrag tab6 = new Satufrag();
                return tab6;

            case 6:
                Sundfrag tab7 = new Sundfrag();
                return tab7;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
