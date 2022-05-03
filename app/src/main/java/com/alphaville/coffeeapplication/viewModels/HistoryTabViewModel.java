package com.alphaville.coffeeapplication.viewModels;

import com.alphaville.coffeeapplication.Model.Review;

import java.util.List;

public class HistoryTabViewModel extends ViewModelEngine{

    public void searchInReviews(String s){
        List<Review> reviewList = getModel().searchInReviews(s);
        //call to adapter or whoever updates the list.
    }

}
