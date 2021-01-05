package com.myapplication.view.engagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myapplication.R;

public class EngagementFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_engagement, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        EngagementViewModel viewModel = new ViewModelProvider(this).get(EngagementViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}