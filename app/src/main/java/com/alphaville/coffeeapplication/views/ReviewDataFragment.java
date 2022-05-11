package com.alphaville.coffeeapplication.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.ReviewDataFragmentBinding;

import com.alphaville.coffeeapplication.viewModels.ReviewDataViewModel;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;

import java.sql.Timestamp;

/**
 * ReviewDataFragment is the fragment for inputting and saving a text review
 */
public class ReviewDataFragment extends Fragment {

    private ReviewDataFragmentBinding binding;

    /**
     * ViewModel reference for handling a new review creation
     */
    private ReviewDataViewModel viewModel;

    /**
     * ViewModel for populating the review-tab with the right information
     */
    private SearchListViewModel viewModel2;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ReviewDataFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ReviewDataViewModel();
        viewModel2 = new SearchListViewModel();

        initInputBox();
        initLocationBox();
        initSaveButton();
        initProductText();

    return view;
    }

    @Override
    public void onResume() {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);
        super.onResume();
    }

    @Override
    public void onPause() {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        super.onPause();
    }

    /**
     * Initiates the text of which coffeeproduct is being reviewed
     */
    @SuppressLint("SetTextI18n")
    private void initProductText() {binding.currentProduct.setText(
            "Reviewing " + viewModel2.getSelected().getValue().getName());
    }

    /**
     * Initiates the input box for inputting location of where the coffee drink was bought
     */
    private void initLocationBox() {binding.locationBox.setHint("Enter location");
    }

    /**
     * Initiates the input box for inputting text review
     */
    public void initInputBox() {
        binding.inputBox.setHint("Enter your review");
    }

    /**
     * Initiates save button for text review
     */
    public void initSaveButton() {
        binding.textSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveReview();
                showToastMessage("Review has been saved");
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    /**
     * Method for showing a toast-message
     */
    private void showToastMessage(String message){
        Context context = getContext();
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method for saving a review. When pressing the save-button, the input information is sent to
     * the ViewModel for handling.
     */
    //TODO:Fix category input box (of some kind) so the user can provide this.
    private void saveReview(){
        double rating = binding.ratingBar.getRating();
        String reviewText = binding.inputBox.getText().toString();
        String location = binding.locationBox.getText().toString();
        //String category = binding.categoryBox.getValue().toString();

        viewModel.createReview(viewModel2.getSelected().getValue(), reviewText, rating, location,
                "randomCategory", new Timestamp(System.currentTimeMillis()));
    }
}
