package com.alphaville.coffeeapplication.Model;

import com.alphaville.coffeeapplication.Model.enums.Process;
import com.alphaville.coffeeapplication.Model.enums.Roast;
import com.alphaville.coffeeapplication.Model.enums.Taste;

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
                                                      List<Roast> roasts,
                                                      List<Process> processes,
                                                      List<Taste> tastes
                                                      ) {

        List<CoffeeProduct> itemsToRemove = new ArrayList<>();
        List<CoffeeProduct> newList = new ArrayList<>(products);

        for (CoffeeProduct p : products) {
            if (countries != null) {
                if (!(countries.contains(p.getCountry()))) itemsToRemove.add(p);
            }

            if (!(minElevation == 0 && maxElevation == 0)) {
                if (!(p.getElevation() >= minElevation && p.getElevation() <= maxElevation)) itemsToRemove.add(p);
            }

            //if (roasts != null) {
            //    if (!(roasts.contains(p.getRoast()))) itemsToRemove.add(p);
            //}

            if (processes != null) {
                if (!(processes.contains(p.getProcess()))) itemsToRemove.add(p);
            }
            // t har 2 1 (CoffeeProduct.Taste)
            // vi söker med 3, 1 (tastes)
            /*if (!(tastes == null)) {
                int deleteP = 0;
                for (Taste t : p.getTastes()) {
                    if(tastes.contains(t)) {
                        break;
                    }
                    else {
                        deleteP++;
                    }
                }
                if (deleteP >= p.getTastes().size()) itemsToRemove.add(p);
            }*/
        }

        // Makes new list from the filter
        newList.removeAll(itemsToRemove);
        List<CoffeeProduct> newListSearch = new ArrayList<>(products);

        // Takes filtered list, then do text string based search
        for (CoffeeProduct p : newList) {
            if (validProductStringSearch(p, searchString)) newListSearch.add(p);
        }

        return newList;
    }

    /**
     * Returns a list of found coffee products depending on search text only
     * @param products list of coffee products to search in
     * @param s searchbar search (name of coffee)
     * @return the active coffeeProduct
     */
    public static List<CoffeeProduct> searchProducts(List<CoffeeProduct> products, String s) {
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
