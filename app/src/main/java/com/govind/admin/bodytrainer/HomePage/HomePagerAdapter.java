package com.govind.admin.bodytrainer.HomePage;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Admin on 15-Mar-19.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    // tab titles
    private String[] tabTitles = new String[]{"Home", "HomeWorkout", "Exercise","Tips", "DietChart"};

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);

        this.tabCount = 5;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeFragment tab1 = new HomeFragment();
                return tab1;
            case 1:
                HomeWorkoutFragment tab2 = new HomeWorkoutFragment();
                return tab2;
            case 2:
                ExerciseFragment tab = new ExerciseFragment();
                return tab;
            case 3:
                TipsFragment tab3 = new TipsFragment();
                return tab3;
            case 4:
                DietChartFrag tab4 = new DietChartFrag();
                return tab4;

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
