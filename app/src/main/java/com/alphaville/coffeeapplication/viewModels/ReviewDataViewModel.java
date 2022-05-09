package com.alphaville.coffeeapplication.viewModels;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.ReviewHandler;


import java.sql.Timestamp;

/**
 * ReviewDataViewModel is the viewmodel responsible for communicating with the model and create
 * text reviews.
 */
public class ReviewDataViewModel extends ViewModelEngine {

    //TODO Initialize review handler in main activity or implement separate viewmodel
    // that gives access to model etc.
    ReviewHandler reviewHandler = new ReviewHandler();

    /**
     * Creates a review for a specific coffee product
     * @param cp the {@link CoffeeProduct} that has been reviewed
     * @param textReview the text review
     * @param rating the rating
     * @param location the location where the coffee was drank
     * @param drinkCategory the type of drink the coffee was consumed as
     * @param creationTime the time the review was created
     */
    public void createReview(CoffeeProduct cp, String textReview,
                             double rating, String location, String drinkCategory, Timestamp creationTime){


        reviewHandler.createReview(getActiveProduct(), textReview, rating, location,
                "testCategory", System.currentTimeMillis());

    }
    public CoffeeProduct getActiveProduct(){
        return getModel().getActive();
    }
}
