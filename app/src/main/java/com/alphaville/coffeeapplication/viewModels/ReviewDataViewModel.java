package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.Model.ReviewHandler;
import com.alphaville.coffeeapplication.Model.ReviewRepository;


import java.sql.Timestamp;
import java.util.List;

/**
 * ReviewDataViewModel is the viewmodel responsible for communicating with the model and create
 * text reviews.
 */
public class ReviewDataViewModel extends AndroidViewModel {

    private ReviewRepository repository;
    private LiveData<List<Review>> allReviews;

    public ReviewDataViewModel(@NonNull Application application) {
        super(application);
        repository = new ReviewRepository(application);
        allReviews = repository.getAllReviews();
    }

    public void insert(Review review) {
        repository.insert(review);
    }

    public void update(Review review) {
        repository.update(review);
    }

    public void delete(Review review) {
        repository.delete(review);
    }

    public LiveData<List<Review>> getAllReviews() {
        return allReviews;
    }
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
                             double rating, String location, String drinkCategory,
                             long creationTime){
        reviewHandler.createReview(cp, textReview, rating, location, drinkCategory, creationTime);
    }

  /*  public CoffeeProduct getActiveProduct(){
        return getModel().getActive();
    }*/
}
