package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import com.alphaville.coffeeapplication.Model.Review;

import java.util.List;

public class RecTabViewModel extends ViewModelEngine{

    public RecTabViewModel(Application application) {
        super(application);
    }

    public List<Review> searchInReviews(String s){
        return getModel().searchInReviews(s);
    }

    public List<Review> getReviews(){
        return getModel().getReviews();
    }

}