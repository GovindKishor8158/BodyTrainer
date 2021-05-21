package com.govind.admin.bodytrainer.Tips;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.govind.admin.bodytrainer.R;

import butterknife.ButterKnife;

/**
 * Created by Admin on 20-Mar-19.
 */

public class MenFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_men, container, false);
        ButterKnife.bind(this, view);

        //fetchdailytips();
        return view;
    }
}
