package com.alphaville.coffeeapplication.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ReviewRepository {
    private ReviewDao reviewDao;
    private LiveData<List<Review>> allReviews;

    public ReviewRepository(Application application) {
        CoffeeDatabase database = CoffeeDatabase.getInstance(application);
        reviewDao = database.reviewDao();
        allReviews = reviewDao.getAllReviews();
    }

    public void insert(Review review) {
        CoffeeDatabase.databaseWriteExecutor.execute(()->{
            reviewDao.insert(review);
        });
    }

    public void update(Review review){
        CoffeeDatabase.databaseWriteExecutor.execute(()->{
            reviewDao.update(review);
        });
    }

    public void delete(Review review){
        CoffeeDatabase.databaseWriteExecutor.execute(()->{
            reviewDao.delete(review);
        });
    }

    public LiveData<List<Review>> getAllReviews(){
        return allReviews;
    }


}
