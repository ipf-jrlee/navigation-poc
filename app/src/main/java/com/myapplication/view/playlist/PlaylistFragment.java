package com.myapplication.view.playlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.myapplication.R;

public class PlaylistFragment extends NavHostFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_playlist, container, false);
        final TextView textView = root.findViewById(R.id.text_playlist);

        PlaylistViewModel viewModel = new ViewModelProvider(this).get(PlaylistViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }
}