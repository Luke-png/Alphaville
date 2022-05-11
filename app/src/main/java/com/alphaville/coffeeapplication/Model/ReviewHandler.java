package com.alphaville.coffeeapplication.Model;

import static com.alphaville.coffeeapplication.Model.CoffeeProduct.Taste.floral;
import static com.alphaville.coffeeapplication.Model.CoffeeProduct.Taste.fruity;

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

        //testobject for checking recyclerview in history-tab.
        List<CoffeeProduct.Taste> s = new ArrayList<>();
        s.add(fruity);
        reviews.add(new Review(new CoffeeProduct("something", "Sweden", 1900,
                CoffeeProduct.Roast.light, CoffeeProduct.Process.dry, s, 1,1,1,"from the best beans", false),
                "this tastes good", 3, "Johanneberg", "cappuccino", new Timestamp(2000)));
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
        //TODO remove test
        //Test review
        createReview(new CoffeeProduct("Skånerost", "Colombia", 225,
                CoffeeProduct.Roast.light, CoffeeProduct.Process.fermented,
                new ArrayList<CoffeeProduct.Taste>() {},1,1,1, "Tastes great", false)
        ,"testReview", 3.5, "testLocation", "Cappuccino",
                new Timestamp(System.currentTimeMillis()));
        return new ArrayList<>(reviews);
    }

    /**
     * Method for picking out reviews that match the text-string the user is typing in history-tab.
     * @param s the text-string the user has typed
     * @return a list with matching reviews
     */
    public List<Review> searchInReviews(String s){
        List<Review> newList = new ArrayList<>();
        for(int i = 0; i < reviews.size(); i++){
            if(validProduct(reviews.get(i), s)){
                newList.add(reviews.get(i));
            }
        }

        return newList;
    }

    /**
     * Method for comparing a single coffeeproduct with a text-string.
     * @param r the coffeeproduct that will be compared
     * @param s the text-string to match with the coffeeproduct
     * @return true if coffeeproduct matches the text-string, false if not
     */
    private boolean validProduct(Review r, String s){
        char coffeeLetter, stringLetter;
        for(int i = 0; i < s.length() && i < r.getCoffeeProduct().getName().length(); i++){
            coffeeLetter = r.getCoffeeProduct().getName().charAt(i);
            stringLetter = s.charAt(i);
            coffeeLetter = Character.toLowerCase(coffeeLetter);
            stringLetter = Character.toLowerCase(stringLetter);
            if(Character.compare(coffeeLetter, stringLetter) != 0){
                return false;
            }
        }
        return true;
    }
}
