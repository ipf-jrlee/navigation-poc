package com.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.myapplication.navigation.NavigationManager;

public class MainActivity extends AppCompatActivity {

    private NavigationManager tabManager;

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