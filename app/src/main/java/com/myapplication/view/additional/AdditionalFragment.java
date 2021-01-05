package com.myapplication.view.additional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.myapplication.R;

public class AdditionalFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_additional, container, false);
        final TextView textView = root.findViewById(R.id.text_additional);

        AdditionalViewModel viewModel = new ViewModelProvider(this).get(AdditionalViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        root.findViewById(R.id.words).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.additional_tab_fragment);

            navController.navigate(R.id.action_launch_words_from_additional);
        });

        return root;
    }
}