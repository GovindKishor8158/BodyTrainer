package com.govind.admin.bodytrainer.HomePage;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.govind.admin.bodytrainer.HomeWorkout.MondayActivity;
import com.govind.admin.bodytrainer.HomeWorkout.TuesdayActivity;
import com.govind.admin.bodytrainer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 15-Mar-19.
 */

public class HomeWorkoutFragment extends Fragment {
    @BindView(R.id.mond)Button mond;
    @BindView(R.id.tuesd)Button tuesd;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homeworkout, container, false);
        ButterKnife.bind(this, view);
        mond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MondayActivity.class));
            }
        });

        tuesd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TuesdayActivity.class));
            }
        });
        //fetchdailytips();
        return view;
    }
}
