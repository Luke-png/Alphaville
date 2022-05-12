package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.databinding.FragmentHistoryBinding;
import com.alphaville.coffeeapplication.viewModels.HistoryTabViewModel;
import com.alphaville.coffeeapplication.viewModels.ReviewDataViewModel;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;
import com.alphaville.coffeeapplication.views.adapters.HistoryResultAdapter;
import com.alphaville.coffeeapplication.views.util.SpacingItemDecorator;

import java.util.List;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private HistoryTabViewModel model;
    private FragmentContainerView fcv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        fcv = binding.fragmentContainerView2;
        fcv.setVisibility(View.INVISIBLE);
        model = new ViewModelProvider(this).get(HistoryTabViewModel.class);

        itemPressed();
        setItemSpacing(15);
        onSearch();
        detailedViewClosed();

        /**
         * This should initiate the adapter and recyclerview
         */
        final HistoryResultAdapter adapter = new HistoryResultAdapter(model);
        binding.reviewList.setAdapter(adapter);

        model.getAllReviews().observe(getViewLifecycleOwner(), new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                adapter.setReviews(reviews);
            }
        });

        binding.reviewList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    /**
     * Method for setting the space between objects in the history-tab recyclerview
     * @param spacing amount of spacing
     */
    private void setItemSpacing(int spacing){
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(spacing);
        binding.reviewList.addItemDecoration(itemDecorator);
    }

    /**
     * Method that should be triggered everytime the user changes anything in the search-field.
     * This is if we want continuous updates while writing.
     */
    //TODO: fix search functionality with newly implemented classes.
    private void onSearch(){
        binding.searchHistory.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                binding.reviewList.setAdapter(new HistoryResultAdapter(model));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                binding.reviewList.setAdapter(new HistoryResultAdapter(model));
                return true;
            }
        });
    }

    /**
     * Listener for when an object in the recyclerview is pressed. Then setting the
     * FragmentContainerView to visible
     */
    private void itemPressed(){
        model.getSelected().observe(getViewLifecycleOwner(), new Observer<Review>() {
            @Override
            public void onChanged(Review review) {
                fcv.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     *
     */
    private void detailedViewClosed(){
        getActivity().getSupportFragmentManager().setFragmentResultListener(
                "requestkey", this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        fcv.setVisibility(View.INVISIBLE);
                    }
                });
    }
}
