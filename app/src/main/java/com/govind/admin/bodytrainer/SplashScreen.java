package com.govind.admin.bodytrainer;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.govind.admin.bodytrainer.BeanModule.User;
import com.govind.admin.bodytrainer.HomePage.HomePage;

public class SplashScreen extends AppCompatActivity {
    private long SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                if (User.getCurrentUser() == null ) {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                   // Intent i = new Intent(SplashScreen.this, LoginActivity.class); //Login screen
                    Intent i = new Intent(SplashScreen.this, HomePage.class);
                    startActivity(i);
                }

                else {
                    Intent i = new Intent(SplashScreen.this, HomePage.class); //Login screen
                    startActivity(i);
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
