package com.govind.admin.bodytrainer.HomePage;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.govind.admin.bodytrainer.R;
import com.govind.admin.bodytrainer.Utility.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 15-Mar-19.
 */

public class ExerciseFragment extends Fragment implements DashBoardAdapter.OpenDahsboardActivity{


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private DashBoardAdapter adapter;
    private ArrayList<DashboardBean> dataList;

    public ExerciseFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        ButterKnife.bind(this, view);
        dataList = new ArrayList<>();
        dataList = addList();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new DashBoardAdapter(getActivity(), dataList, this);
        recyclerView.setAdapter(adapter);
        return view;

    }

    private ArrayList<DashboardBean> addList() {

        ArrayList<DashboardBean> list = new ArrayList<>();

        DashboardBean triObj = new DashboardBean();
        triObj.setTitle(Constants.tricep);
        triObj.setMIcon(getResources().getDrawable(R.drawable.triceppng));
        list.add(triObj);

        DashboardBean bicepObj = new DashboardBean();
        bicepObj.setTitle(Constants.biceps);
        bicepObj.setMIcon(getResources().getDrawable(R.drawable.bicepsnews));
        list.add(bicepObj);

        DashboardBean chestObj = new DashboardBean();
        chestObj.setTitle(Constants.chest);
        chestObj.setMIcon(getResources().getDrawable(R.drawable.chestnew));
        list.add(chestObj);


        DashboardBean shoulderObj = new DashboardBean();
        shoulderObj.setTitle(Constants.shoulder);
        shoulderObj.setMIcon(getResources().getDrawable(R.drawable.sholdernews));
        list.add(shoulderObj);

        DashboardBean absObj = new DashboardBean();
        absObj.setTitle(Constants.abs);
        absObj.setMIcon(getResources().getDrawable(R.drawable.absnew));
        list.add(absObj);


        DashboardBean backObj = new DashboardBean();
        backObj.setTitle(Constants.back);
        backObj.setMIcon(getResources().getDrawable(R.drawable.backnew));
        list.add(backObj);

        DashboardBean forearmObj = new DashboardBean();
        forearmObj.setTitle(Constants.forearm);
        forearmObj.setMIcon(getResources().getDrawable(R.drawable.forearmnew));
        list.add(forearmObj);

        DashboardBean uprlegObj = new DashboardBean();
        uprlegObj.setTitle(Constants.uperleg);
        uprlegObj.setMIcon(getResources().getDrawable(R.drawable.upperlrgnew));
        list.add(uprlegObj);

        DashboardBean lwrlegObj = new DashboardBean();
        lwrlegObj.setTitle(Constants.lowerleg);
        lwrlegObj.setMIcon(getResources().getDrawable(R.drawable.lowerlegnew));
        list.add(lwrlegObj);

        DashboardBean glutsObj = new DashboardBean();
        glutsObj.setTitle(Constants.gluts);
        glutsObj.setMIcon(getResources().getDrawable(R.drawable.glutsnews));
        list.add(glutsObj);

        DashboardBean cardioObj = new DashboardBean();
        cardioObj.setTitle(Constants.cardio);
        cardioObj.setMIcon(getResources().getDrawable(R.drawable.cardio));
        list.add(cardioObj);

        DashboardBean seeallObj = new DashboardBean();
        seeallObj.setTitle(Constants.seeall);
        seeallObj.setMIcon(getResources().getDrawable(R.drawable.seeall));
        list.add(seeallObj);

        return list;
    }

    @Override
    public void openDahsboardActivity(DashboardBean dashboard) {

        if (dashboard.getTitle().equals(Constants.tricep)) {
            Toast.makeText(getActivity(),"Tricep Exercise",Toast.LENGTH_LONG).show();
        }

    }
}
