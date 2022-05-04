package com.alphaville.coffeeapplication.viewModels;

public class CoffeeInfoViewModel extends ViewModelEngine {

    public void changeLikeStatus(boolean value){
        getModel().changeLikeStatus(value);
    }
}
