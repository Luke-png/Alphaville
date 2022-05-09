package com.alphaville.coffeeapplication.Model;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMatcher {

    /** For each of sweetness, bitternss and fullness, the algorithm checks all previous review with
     * the same value and calculates the average rating for all those reviews. Then an average is takes for
     * all three parameters to ge the match score.
     * @param product The product to get match for
     * @param reviews List of all reviews
     * @return Match score
     */
    public static double getMatchPercentage(CoffeeProduct product, List<Review> reviews){
        int sweetness_ref = product.getSweetness();
        int bitterness_ref = product.getBitterness();
        int fullness_ref = product.getFullness();
        ArrayList<Double> s_match = new ArrayList<>();
        ArrayList<Double> b_match = new ArrayList<>();
        ArrayList<Double> f_match = new ArrayList<>();
        for(Review r : reviews) {
            if(r.getCoffeeProduct().getSweetness() == sweetness_ref) s_match.add(r.getRating());
            if(r.getCoffeeProduct().getSweetness() == bitterness_ref) b_match.add(r.getRating());
            if(r.getCoffeeProduct().getSweetness() == fullness_ref) f_match.add(r.getRating());
        }
        return (Mean(s_match) + Mean(b_match) + Mean(f_match))/3;

    }
    /** Calculates the mean (average) value from a list of numeric values.
     * @param values List of numeric values
     * @return Mean (average) value
     */
    public static double Mean(ArrayList<Double> values) {
        if (values.size() == 0)
            return 0;

        return Sum(values) / (double) values.size();
    }

    /** Calculates the sum of a list of numeric values.
     *
     * @param values List of numeric values
     * @return Summed value
     */
    public static double Sum(ArrayList<Double> values) {
        double sum = 0.0;

        for (double value : values)
            sum += value;

        return sum;
    }

}
