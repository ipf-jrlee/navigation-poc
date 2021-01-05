package com.myapplication.view.playlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myapplication.R;

public class PlaylistFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_playlist, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);

        PlaylistViewModel viewModel = new ViewModelProvider(this).get(PlaylistViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("R&", "[PlaylistFragment] onResume: " + isVisible() + ", " + isHidden());
    }
}