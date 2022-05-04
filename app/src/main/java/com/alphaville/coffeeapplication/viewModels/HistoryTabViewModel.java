package com.alphaville.coffeeapplication.viewModels;

import com.alphaville.coffeeapplication.Model.Review;

import java.util.List;

public class HistoryTabViewModel extends ViewModelEngine{

    public List<Review> searchInReviews(String s){
        return getModel().searchInReviews(s);
    }

    public List<Review> getReviews(){
        return getModel().getReviews();
    }

}
