package com.govind.admin.bodytrainer.ActivityModule;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.govind.admin.bodytrainer.R;

import butterknife.ButterKnife;

public class OnlinePayment extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_payment);
        ButterKnife.bind(this);



    }


}
