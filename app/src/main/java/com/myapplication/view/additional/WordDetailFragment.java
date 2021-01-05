package com.myapplication.view.additional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.myapplication.R;
import com.myapplication.view.additional.viewmodel.WordViewModel;

public class WordDetailFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_word_detail, container, false);
        TextView details = root.findViewById(R.id.word_details);
        String word = WordDetailFragmentArgs.fromBundle(getArguments()).getWord();

        details.setText("단어 상세\n\n" + word);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(getActivity(), R.id.additional_tab_fragment);
        ViewModelStoreOwner owner = navController.getViewModelStoreOwner(R.id.additional_nav_graph);
        WordViewModel viewModel = new ViewModelProvider(owner).get(WordViewModel.class);

        viewModel.increment();
    }
}