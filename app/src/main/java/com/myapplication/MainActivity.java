package com.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.navigation.NavigationManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NavigationManager tabManager;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener((item) -> {
            tabManager.switchTab(item.getItemId());
            return true;
        });

        navView.setOnNavigationItemReselectedListener((item) -> {
            tabManager.clearCurrentTabStack();
        });

        tabManager = new NavigationManager(this);
    }

    @Override
    public void onBackPressed() {
        tabManager.onBackPressed();
    }
}