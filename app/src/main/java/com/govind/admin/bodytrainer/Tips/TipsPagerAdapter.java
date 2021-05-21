package com.govind.admin.bodytrainer.Tips;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Admin on 20-Mar-19.
 */

public class TipsPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    private String[] tabTitles = new String[]{"WeightLoss men/women", "WeightGain men/women"};

    public TipsPagerAdapter(FragmentManager fm) {
        super(fm);
        this.tabCount = 2;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                WeightLossfrag tab1 = new WeightLossfrag();
                return tab1;

            case 1:
                WeightGainfrag tab2 = new WeightGainfrag();
                return tab2;
//            case 2:
//                MenFragment tab3 = new MenFragment();
//                return tab3;
//            case 3:
//                WomenFragment tab4 = new WomenFragment();
//                return tab4;
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
