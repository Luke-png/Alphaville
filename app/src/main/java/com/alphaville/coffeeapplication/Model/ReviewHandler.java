package com.alphaville.coffeeapplication.Model;

import static com.alphaville.coffeeapplication.CoffeeProduct.Process.dry;
import static com.alphaville.coffeeapplication.CoffeeProduct.Roast.light;

import com.alphaville.coffeeapplication.CoffeeProduct;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The ReviewHandler class is the class responsible for creating reviews as well as saving them,
 * updating them and retrieving them from persistence storage.
 */
public class ReviewHandler {

    /**
     * The list of reviews in the application
     */
    private final List<Review> reviews = new ArrayList<>();

    public ReviewHandler() {
    }

    /**
     * Creates a review object, saves it to the list of reviews and saves it to the database.
     * @param cp the {@link CoffeeProduct} that has been reviewed
     * @param textReview the text review
     * @param rating the rating
     * @param location the location where the coffee was drank
     * @param drinkCategory the type of drink the coffee was consumed as
     * @param creationTime the time the review was created
     */
    //TODO save to database when implemented.
    public void createReview(CoffeeProduct cp, String textReview, double rating, String location,
                            String drinkCategory, Timestamp creationTime){
        Review newReview = new Review(cp, textReview, rating, location, drinkCategory, creationTime);
        reviews.add(newReview);
    }

    /**
     * Returns a copy of the list of reviews
     * @return a copy of the list of reviews
     */
    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
    }
}
