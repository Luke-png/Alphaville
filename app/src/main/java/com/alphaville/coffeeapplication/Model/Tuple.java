package com.alphaville.coffeeapplication.Model;

public class Tuple implements Comparable<Tuple> {
        private CoffeeProduct coffeeProduct;
        private Double matchScore;

        public Tuple(CoffeeProduct coffeeProduct, Double matchScore) {
            this.coffeeProduct = coffeeProduct;
            this.matchScore = matchScore;
        }
        @Override
        public int compareTo(Tuple other) {
            return other.matchScore.compareTo(matchScore); //rätt??
        }

        public CoffeeProduct getCoffeeProduct(){
            return this.coffeeProduct;
        }

        public Double getMatchScore(){
            return this.matchScore;
        }

}
