package com.alphaville.coffeeapplication.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * The ReviewRepository class is responsible for accessing the database related to Reviews
 */
public class ReviewRepository {
    /**
     * The Data Access Object for {@link Review} objects
     */
    private ReviewDao reviewDao;

    /**
     * An observable LiveData object containing all {@link Review} objects in the database
     */
    private LiveData<List<Review>> allReviews;

    public ReviewRepository(Application application) {
        CoffeeDatabase database = CoffeeDatabase.getInstance(application);
        reviewDao = database.reviewDao();
        allReviews = reviewDao.getAllReviews();
    }

    /**
     * Inserts a review into the database
     * @param review the review to insert into the database
     */
    public void insert(Review review) {
        CoffeeDatabase.databaseWriteExecutor.execute(()->{
            reviewDao.insert(review);
        });
    }

    /**
     * Updates a review object if it exists in the database, based on primary key.
     * If no object with that primary key exists, no changes are made to the database.
     * @param review the review object to update
     */
    public void update(Review review){
        CoffeeDatabase.databaseWriteExecutor.execute(()->{
            reviewDao.update(review);
        });
    }

    /**
     * Deletes a review object from the database
     * @param review the review object to delete
     */
    public void delete(Review review){
        CoffeeDatabase.databaseWriteExecutor.execute(()->{
            reviewDao.delete(review);
        });
    }

    /**
     * Returns an observable LiveData object containing all {@link Review} objects in the database
     * @return the observable LiveData object
     */
    public LiveData<List<Review>> getAllReviews(){
        return allReviews;
    }


}
