package com.govind.admin.bodytrainer.HomeWorkout;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.govind.admin.bodytrainer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MondayActivity extends AppCompatActivity {
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.imgBack)ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);
        ButterKnife.bind(this);
        txtTitle.setText("Monday");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
