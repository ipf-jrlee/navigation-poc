package com.myapplication.navigation;

import android.annotation.SuppressLint;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.myapplication.R;

import java.util.Objects;

public class NavigationManager {

    private final int[] START_DESTINATIONS = {
            R.id.fragment_bookshelf, R.id.fragment_playlist, R.id.fragment_engagement, R.id.fragment_additional
    };

    private final NavHostFragment bookshelfFragment;
    private NavHostFragment playlistFragment, engagementFragment, additionalFragment;
    private NavHostFragment currentFragment;
    private NavController currentNavController;

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
        currentNavController = currentFragment.getNavController();

        bottomNavView = activity.findViewById(R.id.nav_view);
        mainActivity = activity;
    }

    @SuppressLint("NonConstantResourceId")
    public void switchTab(final int itemId) {
        Fragment previousFragment = currentFragment;

        switch (itemId) {
            case R.id.fragment_playlist:
                if (playlistFragment == null) {
                    playlistFragment = NavHostFragment.create(R.navigation.playlist_nav_graph);
                    secureFragment(R.id.fragment_playlist, playlistFragment);
                }
                currentFragment = playlistFragment;
                break;

            case R.id.fragment_engagement:
                if (engagementFragment == null) {
                    engagementFragment = NavHostFragment.create(R.navigation.engagement_nav_graph);
                    secureFragment(R.id.fragment_engagement, engagementFragment);
                }
                currentFragment = engagementFragment;
                break;

            case R.id.fragment_additional:
                if (additionalFragment == null) {
                    additionalFragment = NavHostFragment.create(R.navigation.additional_nav_graph);
                    secureFragment(R.id.fragment_additional, additionalFragment);
                }
                currentFragment = additionalFragment;
                break;

            case R.id.fragment_bookshelf:
            default:
                currentFragment = bookshelfFragment;
                break;
        }

        fragmentManager.beginTransaction()
                .hide(previousFragment)
                .show(currentFragment)
                .commitNow();

        previousFragment.onHiddenChanged(true);
        currentFragment.onHiddenChanged(false);

        currentNavController = currentFragment.getNavController();
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

    private void secureFragment(int fragmentId, NavHostFragment fragment) {
        fragmentManager.beginTransaction()
                .replace(fragmentId, fragment)
                .commitNow();
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
        return Objects.requireNonNull(currentNavController
                .getCurrentBackStackEntry())
                .getDestination();
    }
}
