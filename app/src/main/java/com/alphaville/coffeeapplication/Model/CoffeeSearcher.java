package com.alphaville.coffeeapplication.Model;

import java.util.ArrayList;
import java.util.List;

import kotlin.Pair;

/**
 * CoffeeSearcher contains static methods used as a helper class to search for specified coffee product(s)
 */

public class CoffeeSearcher {

    /**
     * Returns a list of found coffee products depending on search text AND filter
     * @param products list to search
     * @param searchString from the search bar
     * @param countries a list of countries, empty or null gives all
     * @param minElevation minimum required elevation
     * @param maxElevation maximum required elevation
     * @param roasts list of roasts that should be listed
     * @param tastes list of tastes that should be listed
     * @param processes list of processes used that should be listed
     */
    public static List<CoffeeProduct> searchProducts (List<CoffeeProduct> products,
                                                      String searchString,
                                                      List<String> countries,
                                                      int minElevation, int maxElevation,
                                                      List<CoffeeProduct.Roast> roasts,
                                                      List<CoffeeProduct.Taste> tastes,
                                                      List<CoffeeProduct.Process> processes
                                                      ) {

        List<CoffeeProduct> itemsToRemove = new ArrayList<>();
        List<CoffeeProduct> newList = new ArrayList<>(products);

        for (CoffeeProduct p : products) {
    // kaffe: 1,2
            // sök efter 0,1,3
            if (!(countries.contains(p.getCountry()))) itemsToRemove.add(p);
            if (!(p.getElevation() >= minElevation && p.getElevation() <= maxElevation)) itemsToRemove.add(p);
            if (!(roasts.contains(p.getRoast()))) itemsToRemove.add(p);
            /*
            for (CoffeeProduct.Taste t : p.getTastes()) {
                if(!())
            }
            */
            if (!(processes.contains(p.getProcess()))) itemsToRemove.add(p);
        }

        newList.removeAll(itemsToRemove);
        return newList;
    }

    /**
     * Returns a list of found coffee products depending on search text only
     * @param products list of coffee products to search in
     * @param s searchbar search (name of coffee)
     * @return the active coffeeProduct
     */
    public static List<CoffeeProduct> searchProductsNoFilter(List<CoffeeProduct> products, String s) {
        List<CoffeeProduct> newList = new ArrayList<>();

        for (CoffeeProduct p : products) {
            if (validProductStringSearch(p, s)) newList.add(p);
        }
        return newList;
    }


    /**
     * Returns a list of found reviews depending on search text only
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
