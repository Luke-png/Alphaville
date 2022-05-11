package com.alphaville.coffeeapplication.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.R;

public class ReviewInfoTab extends Fragment {

    private Review review;

    public ReviewInfoTab(Review review) {
        this.review = review;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_info_tab, container, false);
    }
}