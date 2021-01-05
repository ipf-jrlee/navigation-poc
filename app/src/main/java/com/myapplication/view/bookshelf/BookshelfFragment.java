package com.myapplication.view.bookshelf;

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

public class BookshelfFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_bookshelf, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        BookshelfViewModel viewModel = new ViewModelProvider(this).get(BookshelfViewModel.class);

        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        NavController navController = Navigation.findNavController(getActivity(), R.id.fragment_bookshelf);

        root.findViewById(R.id.search_book).setOnClickListener(v ->
                navController.navigate(R.id.action_launch_search_book_from_bookshelf));

        root.findViewById(R.id.note_book).setOnClickListener(v ->
                navController.navigate(R.id.action_launch_note_book_from_bookshelf));

        root.findViewById(R.id.reading_road).setOnClickListener(v ->
                navController.navigate(R.id.action_launch_reading_road_from_bookshelf));

        return root;
    }
}