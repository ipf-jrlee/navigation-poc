package com.myapplication.view.additional;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.myapplication.R;
import com.myapplication.view.additional.viewmodel.WordViewModel;

public class WordFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_word, container, false);
        NavController navController = Navigation.findNavController(getActivity(), R.id.additional_tab_fragment);

        root.findViewById(R.id.words).setOnClickListener(v -> {
            WordFragmentDirections.ActionLaunchWordDetailFromWords action
                    = WordFragmentDirections.actionLaunchWordDetailFromWords();

            action.setWord("Apple");

            navController.navigate(action);
        });

        ViewModelStoreOwner owner = navController.getViewModelStoreOwner(R.id.additional_nav_graph);
        WordViewModel viewModel = new ViewModelProvider(owner).get(WordViewModel.class);

        viewModel.getCount().observe(getActivity(), count -> {
            Log.e("R&", "[WordFragment] count: " + count);
        });

        return root;
    }
}