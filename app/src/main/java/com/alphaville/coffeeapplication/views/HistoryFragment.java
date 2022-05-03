package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.alphaville.coffeeapplication.databinding.FragmentHistoryBinding;
import com.alphaville.coffeeapplication.viewModels.HistoryTabViewModel;



public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private HistoryTabViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        binding.searchHistory.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                model.searchInReviews(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                model.searchInReviews(s);
                return true;
            }

        });
        return view;
    }
}
