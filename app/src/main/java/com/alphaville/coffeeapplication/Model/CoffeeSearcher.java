package com.alphaville.coffeeapplication.Model;

import java.util.ArrayList;
import java.util.List;

import kotlin.Pair;

/**
 * CoffeeSearcher contains static methods used as a helper class to search for specified coffee product(s)
 */

public class CoffeeSearcher {

    /**
     * Used to set the active product when clicking on a card
     * @param products list to search
     * @param searchString from the search bar
     * @param countries a list of countries, empty or null gives all
     * @param minElevation minimum required elevation
     * @param maxElevation maximum required elevation
     */
    public static List<CoffeeProduct> searchProducts (List<CoffeeProduct> products,
                                                      String searchString,
                                                      List<String> countries,
                                                      int minElevation, int maxElevation
                                                      ) {

        List<CoffeeProduct> itemsToRemove = new ArrayList<>();


        for (CoffeeProduct p : products) {

            if (!(countries.contains(p.getCountry()))) itemsToRemove.add(p);
            if (!(p.getElevation() >= minElevation && p.getElevation() <= maxElevation)) itemsToRemove.add(p);
            // Fortsätt kolla paramterar
        }

        products.removeAll(itemsToRemove);
        
        return null;
    }


    /**
     * Returns a list of found reviews depending on
     * @param reviews list of reviews to search in
     * @param s searchbar search (name of coffee)
     * @return the active coffeeProduct
     */
    public static List<Review> searchReviews(List<Review> reviews, String s){
        List<Review> newList = new ArrayList<>();

        for (Review r : reviews) {
            if (validReviewStringSearch(r, s)) newList.add(r);
        }
        return newList;
    }

    /**
     * Helper method to alphabetically validate if a review should be returned in the new list
     * of a review search
     * @param r review
     * @param s string search
     * @return true if it should appear
     */
    private static boolean validReviewStringSearch(Review r, String s){
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
    /**
     * Helper method to alphabetically validate if a coffee product should be returned in the new list
     * of a coffee product search
     * @param p coffee product
     * @param s string search
     * @return true if it should appear
     */
    private static boolean validProductStringSearch(CoffeeProduct p, String s){
        char coffeeLetter, stringLetter;
        for(int i = 0; i < s.length() && i < p.getName().length(); i++){
            coffeeLetter = p.getName().charAt(i);
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
