package com.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ActivityNavigator;

public class RoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road);
    }

    @Override
    public void finish() {
        super.finish();

        // https://developer.android.com/guide/navigation/navigation-animate-transitions#activity
        // Pop Animation for Activity is not applied automatically.
        // We should explicitly call ActivityNavigator.applyPopAnimationsToPendingTransition().
        ActivityNavigator.applyPopAnimationsToPendingTransition(this);
    }
}