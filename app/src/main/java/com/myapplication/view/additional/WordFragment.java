package com.myapplication.view.additional;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.myapplication.R;
import com.myapplication.view.additional.viewmodel.WordViewModel;

public class WordFragment extends NavHostFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_word, container, false);
        NavController navController = Navigation.findNavController(getActivity(), R.id.fragment_additional);

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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.e("R&", "[WordFragment] onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.e("R&", "[WordFragment] onDetach");
    }
}