package com.alphaville.coffeeapplication.viewModels;

import androidx.lifecycle.ViewModel;

import com.alphaville.coffeeapplication.model.ReviewHandler;

/**
 * ReviewDataViewModel is the viewmodel responsible for communicating with the model and create
 * text reviews.
 */
public class ReviewDataViewModel extends ViewModel {

    // Initialize review handler in main activity or implement separate viewmodel
    // that gives access to model
    ReviewHandler reviewHandler = new ReviewHandler();

    public void createTextReview(String reviewText) {
       reviewHandler.createTextReview(reviewText);
    }
    public void createTextAndRatingReview(String reviewText, double rating){
        reviewHandler.createTextAndRatingReview(reviewText,rating);
    }
}
