package com.alphaville.coffeeapplication.model;

/**
 * The Review class is a data class representing a review of a CoffeeProduct
 */
public class Review {

    //CoffeeProduct coffeeProduct;
    /**
     * Free form text review of coffeeProduct
     */
    private String textReview;
    private String location;
    private double rating;
    //TODO Add more attributes



    public Review(String textReview) {
        this.textReview = textReview;
    }

    public Review(String textReview, String location, double rating){
        this.textReview = textReview;
        this.location = location;
        this.rating = rating;
    }

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }
}
