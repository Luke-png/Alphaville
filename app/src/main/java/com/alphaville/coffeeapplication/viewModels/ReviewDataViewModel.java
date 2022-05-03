package com.alphaville.coffeeapplication.viewModels;

import androidx.lifecycle.ViewModel;

import com.alphaville.coffeeapplication.model.CoffeeProduct;
import com.alphaville.coffeeapplication.model.ReviewHandler;

/**
 * ReviewDataViewModel is the viewmodel responsible for communicating with the model and create
 * text reviews.
 */
public class ReviewDataViewModel extends ViewModelEngine {

    // Initialize review handler in main activity or implement separate viewmodel
    // that gives access to model
    ReviewHandler reviewHandler = new ReviewHandler();

    public void createTextReview(String reviewText) {
       reviewHandler.createTextReview(reviewText);
    }
    public void createReview(String reviewText, String location, double rating){
        reviewHandler.createReview(reviewText, location,rating);
    }
    public CoffeeProduct getActiveProduct(){
        return getModel().getActive();
    }
}
