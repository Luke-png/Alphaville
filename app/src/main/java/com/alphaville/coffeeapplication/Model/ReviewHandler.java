package com.alphaville.coffeeapplication.Model;
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
     * Creates a review object with a text review
     * @param text The text review
     */
    public void createTextReview(String text) {
        Review newReview = new Review(text);
        reviews.add(newReview);
    }
    public void createTextAndRatingReview(String text, double rating){
        Review newReview = new Review(text, rating);
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
