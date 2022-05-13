package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

public class CoffeeInfoViewModel extends ViewModelEngine {

    public CoffeeInfoViewModel(Application application) {
        super(application);
    }

    public void changeLikeStatus(boolean value){
        getModel().changeLikeStatus(value);
    }
}
