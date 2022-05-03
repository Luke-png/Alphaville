package com.alphaville.coffeeapplication.Model;

/**
 * The Review class is a data class representing a review of a CoffeeProduct
 */
public class Review {

    //CoffeeProduct coffeeProduct;
    /**
     * Free form text review of coffeeProduct
     */
    private String textReview;
    private double rating;
    //TODO Add more attributes



    public Review(String textReview) {
        this.textReview = textReview;
    }

    public Review(String textReview, double rating){
        this.textReview = textReview;
        this.rating = rating;
    }

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public double getRating() {
        return rating;
    }
}
