package com.alphaville.coffeeapplication.Model;

import java.util.List;

/**
 * A facade-class for the applications model. It is used by the ViewModels to handle the model.
 */
public class ModelFacade {

    /**
     * Changes the boolean attribute "like" in the CoffeeProduct
     * @param value the new value
     */
    public void changeLikeStatus(boolean value){
        //pass on call to coffee-object to change like-status
    }
    public List<Review> searchInReviews(String s){
        //pass on call to ReviewHandler
        return null;
    }

}
