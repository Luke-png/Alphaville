package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.databinding.FragmentReviewInfoTabBinding;

public class ReviewInfoTabFragment extends Fragment {

    private Review review;
    private FragmentReviewInfoTabBinding binding;

    public ReviewInfoTabFragment(Review review) {
        this.review = review;
    }

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

        initValues(review);
        return view;
    }

    private void initValues(Review review) {
        binding.nameText.setText(review.getCoffeeProduct().getName());
        binding.reviewText.setText(review.getTextReview());
        binding.locationText.setText(review.getLocation());
        binding.ratingBar2.setRating((float) review.getRating());
    }
}