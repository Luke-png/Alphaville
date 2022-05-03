package com.alphaville.coffeeapplication.viewModels;

import android.content.Intent;

import com.alphaville.coffeeapplication.model.CoffeeProduct;
import com.alphaville.coffeeapplication.model.ModelFacade;
import com.alphaville.coffeeapplication.views.ReviewActivity;

public class CoffeeInfoViewModel extends ViewModelEngine {

    public void changeLikeStatus(boolean value){
        getModel().changeLikeStatus(value);
    }
}
