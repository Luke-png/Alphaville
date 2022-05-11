package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.databinding.FragmentReviewInfoTabBinding;
import com.alphaville.coffeeapplication.viewModels.HistoryTabViewModel;

public class ReviewInfoTabFragment extends Fragment {

    private FragmentReviewInfoTabBinding binding;
    private HistoryTabViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentReviewInfoTabBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        model = new ViewModelProvider(requireActivity()).get(HistoryTabViewModel.class);

        model.getSelected().observe(getViewLifecycleOwner(), new Observer<Review>() {
            @Override
            public void onChanged(Review review) {
                initValues(review);
            }
        });
        return view;
    }

    /**
     * Method for setting all values in the view
     * @param review the object that will be displayed
     */
    private void initValues(Review review) {
        binding.nameText.setText(review.getCoffeeProduct().getName());
        binding.reviewText.setText(review.getTextReview());
        binding.locationText.setText(review.getLocation());
        binding.ratingBar2.setRating((float) review.getRating());
    }
}