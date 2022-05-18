package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.Model.Database.ReviewRepository;
import com.alphaville.coffeeapplication.Model.enums.Process;
import com.alphaville.coffeeapplication.Model.enums.Roast;
import com.alphaville.coffeeapplication.Model.enums.Taste;


import java.util.ArrayList;

/**
 * ReviewDataViewModel is the viewmodel responsible for communicating with the model and create
 * text reviews.
 */
public class ReviewDataViewModel extends AndroidViewModel {

    ReviewRepository repository;

    public ReviewDataViewModel(@NonNull Application application) {
        super(application);
        repository = new ReviewRepository(application);
    }

    /**
     * Creates a review for a specific coffee product and inserts it into database
     * @param cp the {@link CoffeeProduct} that has been reviewed
     * @param textReview the text review
     * @param rating the rating
     * @param location the location where the coffee was drank
     * @param drinkCategory the type of drink the coffee was consumed as
     * @param creationTime the time the review was created
     */
    public void createReview(CoffeeProduct cp, String textReview,
                             double rating, String location, String drinkCategory,
                             long creationTime){
        repository.insert(new Review(cp,textReview, rating, location, drinkCategory, creationTime));
    }
}