package com.govind.admin.bodytrainer.HomePage;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.govind.admin.bodytrainer.DietChart.Pager;
import com.govind.admin.bodytrainer.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 15-Mar-19.
 */

public class DietChartFrag extends Fragment {
    @BindView(R.id.pager)
    ViewPager viewpager;
    @BindView(R.id.tab_layout)
    TabLayout tablayout;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dietchart, container, false);
        ButterKnife.bind(this, view);
        tablayout.setupWithViewPager(viewpager);
        final PagerAdapter adapter = new Pager
                (getChildFragmentManager());
        viewpager.setAdapter(adapter);

        return view;
    }
}
