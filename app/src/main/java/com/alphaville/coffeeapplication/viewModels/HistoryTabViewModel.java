package com.alphaville.coffeeapplication.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.Model.Database.ReviewRepository;

import java.util.List;

public class HistoryTabViewModel extends ViewModelEngine{
    private final MutableLiveData<Review> selected = new MutableLiveData<Review>();
    private ReviewRepository repository;
    private LiveData<List<Review>> allReviews;

    public void selectItem(Review review) {
        selected.setValue(review);
    }
    public LiveData<Review> getSelected() {
        return selected;
    }

    public HistoryTabViewModel(@NonNull Application application) {
        super(application);
        repository = new ReviewRepository(application);
        allReviews = repository.getAllReviews();
    }

    //maybe not relevant anymore?
    public List<Review> searchInReviews(String s){
        return getModel().searchInReviews(s);
    }
    public List<Review> getReviews(){
        return getModel().getReviews();
    }*/

    public LiveData<List<Review>> getAllReviews() {
        return allReviews;
    }
}
