package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.alphaville.coffeeapplication.databinding.ReviewDataFragmentBinding;

import com.alphaville.coffeeapplication.databinding.ReviewDataFragmentBinding;

import com.alphaville.coffeeapplication.viewModels.ReviewDataViewModel;

/**
 * ReviewDataFragment is the fragment for inputting and saving a text review
 */
public class ReviewDataFragment extends Fragment {

    //TODO Try to implement databinding for MVVM
    private ReviewDataFragmentBinding binding;
    private ReviewDataViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ReviewDataFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ReviewDataViewModel();

        //initRatingbar();
        initInputBox();
        initLocationBox();
        initSaveButton();

    return view;
    }
    /**
     * Initiates the input box for inputting location of where the coffe drink was bought
     */
    private void initLocationBox() {binding.locationBox.setHint("Enter location");
    }

    /**
     * Initiates the input box for inputting text review
     */
    public void initInputBox() {
        binding.inputBox.setHint("Enter your review");
    }

   /* public void initRatingbar(View view){
        ratingBar = view.findViewById(R.id.ratingBar);
   }*/

    /**
     * Initiates save button for text review
     */
    public void initSaveButton() {
        binding.textSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.createReview(binding.inputBox.getText().toString(), binding.locationBox.getText().toString(),
                        binding.ratingBar.getRating());
            }
        });
    }

}
