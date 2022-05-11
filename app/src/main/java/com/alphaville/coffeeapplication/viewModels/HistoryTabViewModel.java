package com.alphaville.coffeeapplication.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Review;

import java.util.List;

public class HistoryTabViewModel extends ViewModelEngine{
    private final MutableLiveData<Review> selected = new MutableLiveData<Review>();

    public void selectItem(Review review) {
        selected.setValue(review);
    }
    public LiveData<Review> getSelected() {
        return selected;
    }

    //maybe not relevant anymore?
    public List<Review> searchInReviews(String s){
        return getModel().searchInReviews(s);
    }
    public List<Review> getReviews(){
        return getModel().getReviews();
    }
}
