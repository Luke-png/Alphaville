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

    //TODO Move all use for reviewHandler
    // Should not use reviewHandler anymore. Communicate with repository and logic classes instead
    // ReviewHandler reviewHandler = new ReviewHandler();

    public ReviewDataViewModel(@NonNull Application application) {
        super(application);
        repository = new ReviewRepository(application);
    }

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

        //TODO Replace this test coffee product with actual selected coffee product.
        CoffeeProduct testCp = new CoffeeProduct("Skånerost", "Colombia",
                225, Roast.light, Process.dry, new ArrayList<Taste>() {},
                2, 3, 4, "Tastes great", false);

        repository.insert(new Review(testCp,textReview, rating, location, drinkCategory, creationTime));
          }

  /*  public CoffeeProduct getActiveProduct(){
        return getModel().getActive();
    }*/
}
