package com.alphaville.coffeeapplication.model;

import java.nio.charset.CoderMalfunctionError;
import java.util.ArrayList;

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

    /**
     * Returns the active product
     * @return the active coffeeProduct
     */
    public CoffeeProduct getActive() {

        //OBS hårdkodning för att slippa NullPointerException. Kommentera bort denna rad
        active = new CoffeeProduct("Skånerost", "Colombia", 225, CoffeeProduct.Roast.light, CoffeeProduct.Process.dry, new ArrayList<CoffeeProduct.Taste>() {}, "Tastes great", false);

        return active;
    }

    /**
     * Used to set the active product when clicking on a card
     * @param active the active product
     */

    public void setActive(CoffeeProduct active) {
        this.active = active;
    }
}
