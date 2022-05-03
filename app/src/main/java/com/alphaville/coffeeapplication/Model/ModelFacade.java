package com.alphaville.coffeeapplication.model;

/**
 * A facade-class for the applications model. It is used by the ViewModels to handle the model.
 */
public class ModelFacade {

    CoffeeProduct active;
    /**
     * Changes the boolean attribute "like" in the CoffeeProduct
     * @param value the new value
     */
    public void changeLikeStatus(boolean value){
        //pass on call to coffee-object to change like-status
    }

    public CoffeeProduct getActive() {
        return active;
    }
    public void setActive(CoffeeProduct active) {
        this.active = active;
    }
}
