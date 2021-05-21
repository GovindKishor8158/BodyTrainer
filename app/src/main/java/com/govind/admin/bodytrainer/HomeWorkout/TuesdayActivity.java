package com.govind.admin.bodytrainer.HomeWorkout;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.govind.admin.bodytrainer.Utility.Constants;
import com.govind.admin.bodytrainer.R;
import com.govind.admin.bodytrainer.Utility.ApiHandler;
import com.govind.admin.bodytrainer.Utility.ApiHandlerError;
import com.govind.admin.bodytrainer.Utility.ErpProgress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TuesdayActivity extends AppCompatActivity {
    @BindView(R.id.parent)
    RelativeLayout parent;
    String classid = "";
    private TuesWorkoutAdapt adapter;
    ArrayList<TueWorkoutbean> tuesList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;
    private int loaded = 0;
    @BindView(R.id.txtTitle)TextView txtTitle;
    @BindView(R.id.imgBack)ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuesday);
        ButterKnife.bind(this);
        txtTitle.setText("Tuesday");
        layoutManager = new LinearLayoutManager(TuesdayActivity.this);
        recyclerview.setLayoutManager(layoutManager);
        adapter = new TuesWorkoutAdapt(tuesList, TuesdayActivity.this);
        recyclerview.setAdapter(adapter);
        fetchWorkout();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fetchWorkout() {
        if (loaded == 0) {
            ErpProgress.showProgressBar(this, "Please wait...");
        }
        loaded++;

        ApiHandler.apiHit(Request.Method.GET, Constants.getBaseURL() + Constants.TUEWORKOUT_URL, apiCallback, this, null);


    }
    ApiHandler.ApiCallback apiCallback = new ApiHandler.ApiCallback() {
        @Override
        public void onDataFetched(JSONObject jsonObject, ApiHandlerError error) {
            ErpProgress.cancelProgressBar();
            if (error == null) {
                try {
                    if (tuesList != null) {
                        if (tuesList != null) {
                            tuesList.clear();
                        }
                        JSONArray jsonArray = jsonObject.getJSONArray("wrkout_data");
                        tuesList = TueWorkoutbean.parseHWArray(jsonArray);
                        if (tuesList.size() > 0) {
                            adapter.setProfileArrayList(tuesList);
                            adapter.notifyDataSetChanged();

                        } else {
                            adapter.setProfileArrayList(tuesList);
                            adapter.notifyDataSetChanged();

                        }

                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(TuesdayActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    };
}
