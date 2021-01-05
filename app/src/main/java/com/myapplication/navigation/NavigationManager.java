package com.myapplication.navigation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationManager {

    private final int[] START_DESTINATIONS = {
            R.id.fragment_bookshelf, R.id.fragment_playlist, R.id.fragment_engagement, R.id.fragment_additional
    };

    private final NavController bookshelfNavController, playlistNavController, engagementNavController, additionalNavController;
    private NavController currentNavController;
    private int currentTabId = R.id.fragment_bookshelf;

    private final View bookshelfTab, playlistTab, engagementTab, additionalTab;
    private View currentTab;

    private final BottomNavigationView bottomNavView;
    private final Activity mainActivity;

    public NavigationManager(Activity activity) {
        bookshelfNavController = Navigation.findNavController(activity, R.id.bookshelf_tab_fragment);
        bookshelfNavController.setGraph(R.navigation.bookshelf_nav_graph);
        playlistNavController = Navigation.findNavController(activity, R.id.playlist_tab_fragment);
        playlistNavController.setGraph(R.navigation.playlist_nav_graph);
        engagementNavController = Navigation.findNavController(activity, R.id.engagement_tab_fragment);
        engagementNavController.setGraph(R.navigation.engagement_nav_graph);
        additionalNavController = Navigation.findNavController(activity, R.id.additional_tab_fragment);
        additionalNavController.setGraph(R.navigation.additional_nav_graph);
        currentNavController = bookshelfNavController;

        bookshelfTab = activity.findViewById(R.id.bookshelf_tab);
        playlistTab = activity.findViewById(R.id.playlist_tab);
        engagementTab = activity.findViewById(R.id.engagement_tab);
        additionalTab = activity.findViewById(R.id.additional_tab);
        currentTab = bookshelfTab;

        bottomNavView = activity.findViewById(R.id.nav_view);
        mainActivity = activity;
    }

    @SuppressLint("NonConstantResourceId")
    public void switchTab(final int itemId) {
        currentTab.setVisibility(View.GONE);

        switch (itemId) {
            case R.id.fragment_playlist:
                currentNavController = playlistNavController;
                currentTab = playlistTab;
                break;

            case R.id.fragment_engagement:
                currentNavController = engagementNavController;
                currentTab = engagementTab;
                break;

            case R.id.fragment_additional:
                currentNavController = additionalNavController;
                currentTab = additionalTab;
                break;

            case R.id.fragment_bookshelf:
            default:
                currentNavController = bookshelfNavController;
                currentTab = bookshelfTab;
                break;
        }
        currentTab.setVisibility(View.VISIBLE);
        currentTabId = itemId;
    }

    public void onBackPressed() {
        if (isEntryDestination()) {
            if (isMyBookshelfTabSelected()) {
                mainActivity.finish();
            } else {
                bottomNavView.setSelectedItemId(R.id.fragment_bookshelf);
            }
        } else {
            currentNavController.popBackStack();
        }
    }

    public void clearCurrentTabStack() {
        while (!isEntryDestination()) {
            currentNavController.popBackStack();
        }
    }

    private boolean isMyBookshelfTabSelected() {
        return currentTabId == R.id.fragment_bookshelf;
    }

    private boolean isEntryDestination() {
        NavBackStackEntry backStackEntry = currentNavController.getCurrentBackStackEntry();
        NavDestination destination = backStackEntry.getDestination();
        int destinationId = destination.getId();

        for (int entryDest : START_DESTINATIONS) {
            if (entryDest == destinationId) {
                return true;
            }
        }
        return false;
    }

}
