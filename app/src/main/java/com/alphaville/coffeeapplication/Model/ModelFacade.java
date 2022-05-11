package com.alphaville.coffeeapplication.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * A facade-class for the applications model. It is used by the ViewModels to handle the model.
 */
public class ModelFacade {

    ReviewHandler reviewHandler = new ReviewHandler();
    CoffeeProduct active;

    /**
     * Changes the boolean attribute "like" in the CoffeeProduct
     * @param value the new value
     */
    public void changeLikeStatus(boolean value){
        //pass on call to coffee-object to change like-status
    }

    /**
     * Returns the active product
     * @return the active coffeeProduct
     */
    public CoffeeProduct getActive() {

        //OBS hårdkodning för att slippa NullPointerException. Kommentera bort denna rad
        active = new CoffeeProduct("Skånerost", "Colombia", 225, CoffeeProduct.Roast.light, CoffeeProduct.Process.dry, new ArrayList<Taste>() {}, "Tastes great", false);

        return active;
    }

    /**
     * Used to set the active product when clicking on a card
     * @param active the active product
     */

    public void setActive(CoffeeProduct active) {
        this.active = active;
    }

    public List<Review> searchInReviews(String s){
        return reviewHandler.searchInReviews(s);
    }

    public List<Review> getReviews(){
        return reviewHandler.getReviews();
    }
}
