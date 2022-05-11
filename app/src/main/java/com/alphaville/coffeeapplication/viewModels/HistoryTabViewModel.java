package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.Model.ReviewRepository;

import java.util.List;

public class HistoryTabViewModel extends AndroidViewModel {

    private ReviewRepository repository;
    private LiveData<List<Review>> allReviews;

    public HistoryTabViewModel(@NonNull Application application) {
        super(application);
        repository = new ReviewRepository(application);
        allReviews = repository.getAllReviews();
    }

   /*public List<Review> searchInReviews(String s){
        return getModel().searchInReviews(s);
    }*/

    public LiveData<List<Review>> getAllReviews() {
        return allReviews;
    }

}
