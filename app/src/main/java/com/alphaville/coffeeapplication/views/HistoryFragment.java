package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

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
        model = new HistoryTabViewModel();
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(15);
        binding.reviewList.addItemDecoration(itemDecorator);

        binding.reviewList.setLayoutManager(new LinearLayoutManager(getActivity()));
        /**
         * This should initiate the adapter and recyclerview
         */
        binding.reviewList.setAdapter(new HistoryResultAdapter(model.getReviews()));
        /**
         * Listener that should be triggered everytime the user changes anything in the search-field.
         * This is if we want continuous updates while writing.
         */
        binding.searchHistory.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                binding.reviewList.setAdapter(new HistoryResultAdapter(model.searchInReviews(s)));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                binding.reviewList.setAdapter(new HistoryResultAdapter(model.searchInReviews(s)));
                return true;
            }
        });
        return view;
    }
}
