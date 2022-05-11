package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.databinding.FragmentHistoryBinding;
import com.alphaville.coffeeapplication.viewModels.HistoryTabViewModel;
import com.alphaville.coffeeapplication.viewModels.ReviewDataViewModel;
import com.alphaville.coffeeapplication.views.adapters.HistoryResultAdapter;
import com.alphaville.coffeeapplication.views.util.SpacingItemDecorator;

import java.util.List;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private HistoryTabViewModel viewModel;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(15);
        binding.reviewList.addItemDecoration(itemDecorator);

        /**
         * This should initiate the adapter and recyclerview
         */
        final HistoryResultAdapter adapter = new HistoryResultAdapter();
        binding.reviewList.setAdapter(adapter);

        //Connects with HistoryTabViewModel
        viewModel = new ViewModelProvider(this).get(HistoryTabViewModel.class);
        viewModel.getAllReviews().observe(getViewLifecycleOwner(), new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                adapter.setReviews(reviews);
            }
        });

        binding.reviewList.setLayoutManager(new LinearLayoutManager(getActivity()));


        /**
         * Listener that should be triggered everytime the user changes anything in the search-field.
         * This is if we want continuous updates while writing.
         */
        //TODO fix search functionality
        binding.searchHistory.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                binding.reviewList.setAdapter(new HistoryResultAdapter());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                binding.reviewList.setAdapter(new HistoryResultAdapter());
                return true;
            }
        });
        return view;
    }

}
