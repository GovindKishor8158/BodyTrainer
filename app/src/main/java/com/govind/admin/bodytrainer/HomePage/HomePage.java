package com.govind.admin.bodytrainer.HomePage;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.govind.admin.bodytrainer.ActivityModule.OnlinePayment;
import com.govind.admin.bodytrainer.BeanModule.User;
import com.govind.admin.bodytrainer.LoginModule.LoginActivity;
import com.govind.admin.bodytrainer.LoginModule.RegistrationActivity;
import com.govind.admin.bodytrainer.R;
import com.govind.admin.bodytrainer.Utility.ErpProgress;
import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    TextView txtName;
    TextView txtUserEmail;
    @BindView(R.id.txtUserName)TextView txtUserName;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        if(User.getCurrentUser().getUser_type()!=null) {
            if (User.getCurrentUser().getUser_type().equalsIgnoreCase("admin")) {
                Menu menu = nav_view.getMenu();
                menu.findItem(R.id.txtpayonline).setVisible(false);
            } else {
                Menu menu = nav_view.getMenu();
                menu.findItem(R.id.txtpayonline).setVisible(true);

            }
        }
        View headerView = nav_view.getHeaderView(0);

        txtUserName.setText(User.getCurrentUser().getName());
        txtName = headerView.findViewById(R.id.txtName);
        txtUserEmail = headerView.findViewById(R.id.txtUserEmail);

        txtName.setText(User.getCurrentUser().getName());
        txtUserEmail.setText(User.getCurrentUser().getEmail());

        nav_view.setNavigationItemSelectedListener(this);
        //Tab layout
        final ViewPager viewPager = findViewById(R.id.pager);
        tabLayout.setupWithViewPager(viewPager);
        final PagerAdapter adapter = new HomePagerAdapter
                (getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
           // updateUI();
        }

    }

    private void updateUI() {
        Toast.makeText(getApplicationContext(),"your are logged out",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
        finish();
    }

    @OnClick(R.id.btnToggle)
    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.txtLogout) {

            logoutDialog();
        }
        if (item.getItemId() == R.id.txtpayonline) {
            startActivity(new Intent(HomePage.this, OnlinePayment.class));
        }

        return false;
    }

    public void logoutDialog() {
        final Dialog logoutDialog = new Dialog(this);
        logoutDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        logoutDialog.setCancelable(true);
        logoutDialog.setContentView(R.layout.logout_dialog);
        logoutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Button btnNo = logoutDialog.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutDialog.dismiss();
            }
        });
        Button btnYes = logoutDialog.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutDialog.dismiss();
                ErpProgress.showProgressBar(HomePage.this, "Please wait...");
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                User.getCurrentUser().logout();
                Toast.makeText(HomePage.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                Intent loginpage = new Intent(HomePage.this, LoginActivity.class);
                loginpage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(loginpage);
                ErpProgress.cancelProgressBar();
                finish();
            }
        });
        logoutDialog.show();
    }
}
