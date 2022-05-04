package com.alphaville.coffeeapplication.Model;

import java.sql.Timestamp;

/**
 * The Review class is a data class representing a review of a CoffeeProduct
 */
public class Review {

    private final CoffeeProduct coffeeProduct;
    /**
     * Free form text review of coffeeProduct
     */
    private final String textReview;
    /**
     * The rating of the review
     */
    private final double rating;
    /**
     * The location where the coffee was drank
     */
    private final String location;
    /**
     * The type of drink the coffee was consumed as e.g. Latte, Cappuccino etc.
     */
    private final String drinkCategory;
    /**
     * The time that the review was created
     */
    private final Timestamp creationTime;

    //TODO possibly add taste clock attributes


    public Review(CoffeeProduct coffeeProduct, String textReview, double rating, String location, String drinkCategory, Timestamp creationTime) {
        this.coffeeProduct = coffeeProduct;
        this.textReview = textReview;
        this.rating = rating;
        this.location = location;
        this.drinkCategory = drinkCategory;
        this.creationTime = creationTime;
    }

    public CoffeeProduct getCoffeeProduct() {
        return coffeeProduct;
    }

    public String getTextReview() {
        return textReview;
    }

    public double getRating() {
        return rating;
    }

    public String getLocation() {
        return location;
    }

    public String getDrinkCategory() {
        return drinkCategory;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }


}