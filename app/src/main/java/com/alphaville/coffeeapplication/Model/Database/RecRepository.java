package com.alphaville.coffeeapplication.Model.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.alphaville.coffeeapplication.Model.Review;

import java.util.List;

public class RecRepository {

    private ReviewDao reviewDao;
    private LiveData<List<Review>> allReviews;

    public RecRepository(Application application){
        CoffeeDatabase database = CoffeeDatabase.getInstance(application);
        reviewDao = database.reviewDao();
        allReviews = reviewDao.getAllReviews();
    }

    public LiveData<List<Review>> getAllReviews(){
        return allReviews;
    }

}
