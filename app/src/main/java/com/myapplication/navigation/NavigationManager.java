package com.myapplication.navigation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.myapplication.R;

import java.util.Objects;

public class NavigationManager implements NavController.OnDestinationChangedListener {

    private final int[] START_DESTINATIONS = {
            R.id.fragment_bookshelf, R.id.fragment_playlist, R.id.fragment_engagement, R.id.fragment_additional
    };

    private final NavHostFragment bookshelfFragment;
    private NavHostFragment playlistFragment, engagementFragment, additionalFragment;
    private NavHostFragment currentFragment;

    private final BottomNavigationView bottomNavView;
    private final FragmentManager fragmentManager;
    private final Activity mainActivity;

    public NavigationManager(AppCompatActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        bookshelfFragment = NavHostFragment.create(R.navigation.bookshelf_nav_graph);

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_bookshelf, bookshelfFragment)
                .commitNow();

        currentFragment = bookshelfFragment;
        currentFragment.getNavController()
                .addOnDestinationChangedListener(this);

        bottomNavView = activity.findViewById(R.id.nav_view);
        mainActivity = activity;
    }

    @SuppressLint("NonConstantResourceId")
    public void switchTab(final int itemId) {
        NavHostFragment previousFragment = currentFragment;

        previousFragment.getNavController()
                .removeOnDestinationChangedListener(this);

        switch (itemId) {
            case R.id.fragment_playlist:
                if (playlistFragment == null) {
                    playlistFragment = secureFragment(R.navigation.playlist_nav_graph, R.id.fragment_playlist);
                }
                currentFragment = playlistFragment;
                break;

            case R.id.fragment_engagement:
                if (engagementFragment == null) {
                    engagementFragment = secureFragment(R.navigation.engagement_nav_graph, R.id.fragment_engagement);
                }
                currentFragment = engagementFragment;
                break;

            case R.id.fragment_additional:
                if (additionalFragment == null) {
                    additionalFragment = secureFragment(R.navigation.additional_nav_graph, R.id.fragment_additional);
                }
                currentFragment = additionalFragment;
                break;

            case R.id.fragment_bookshelf:
            default:
                currentFragment = bookshelfFragment;
                break;
        }

        currentFragment.getNavController()
                .addOnDestinationChangedListener(this);

        fragmentManager.beginTransaction()
                .hide(previousFragment)
                .show(currentFragment)
                .commitNow();
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        Log.e("R&", "[NavigationManager] onDestinationChanged: " + destination.getLabel());
        // TODO : SEND FIREBASE SCREEN EVENT
    }

    public void onBackPressed() {
        if (isEntryDestination()) {
            if (isMyBookshelfTabSelected()) {
                mainActivity.finish();
            } else {
                bottomNavView.setSelectedItemId(R.id.fragment_bookshelf);
            }
        } else {
            NavController navController = currentFragment.getNavController();

            navController.popBackStack();
        }
    }

    public void clearCurrentTabStack() {
        NavController navController = currentFragment.getNavController();

        while (!isEntryDestination()) {
            navController.popBackStack();
        }
    }

    private NavHostFragment secureFragment(int navGraphId, int fragmentId) {
        NavHostFragment fragment = NavHostFragment.create(navGraphId);

        fragmentManager.beginTransaction()
                .replace(fragmentId, fragment)
                .commitNow();

        return fragment;
    }

    private boolean isMyBookshelfTabSelected() {
        return currentFragment.getId() == R.id.fragment_bookshelf;
    }

    private boolean isEntryDestination() {
        NavDestination destination = getCurrentDestination();
        int destinationId = destination.getId();

        for (int entryDest : START_DESTINATIONS) {
            if (entryDest == destinationId) {
                return true;
            }
        }
        return false;
    }

    private NavDestination getCurrentDestination() {
        return Objects.requireNonNull(currentFragment.getNavController()
                .getCurrentBackStackEntry())
                .getDestination();
    }
}
