package com.alphaville.coffeeapplication.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class ReviewRepository {
    private ReviewDao reviewDao;
    private LiveData<List<Review>> allReviews;
/*
    public ReviewRepository(Application application) {
        CoffeeDatabase database = CoffeeDatabase.getInstance(application);
        reviewDao = database.reviewDao();
        allReviews = reviewDao.getAllReviews();
    }

    public void insert(Review review) {
        CoffeeDatabase.dataBaseWriteExecutor.execute(()->{
            reviewDao.insert(review);
        });
    }

    public void update(Review review){
        new UpdateReviewAsyncTask(reviewDao).execute(review);

    }

    public void delete(Review review){
        new DeleteReviewAsyncTask(reviewDao).execute(review);

    }

    public LiveData<List<Review>> getAllReviews(){
        return allReviews;
    }

    private static class InsertReviewAsyncTask extends AsyncTask<Review, Void, Void> {
        private ReviewDao reviewDao;

        private InsertReviewAsyncTask(ReviewDao reviewDao){
            this.reviewDao = reviewDao;
        }

        @Override
        protected Void doInBackground(Review... reviews) {
            reviewDao.insert(reviews[0]);
            return null;
        }
    }

    private static class UpdateReviewAsyncTask extends AsyncTask<Review, Void, Void> {
        private ReviewDao reviewDao;

        private UpdateReviewAsyncTask(ReviewDao reviewDao){
            this.reviewDao = reviewDao;
        }

        @Override
        protected Void doInBackground(Review... reviews) {
            reviewDao.update(reviews[0]);
            return null;
        }
    }
    private static class DeleteReviewAsyncTask extends AsyncTask<Review, Void, Void> {
        private ReviewDao reviewDao;

        private DeleteReviewAsyncTask(ReviewDao reviewDao){
            this.reviewDao = reviewDao;
        }

        @Override
        protected Void doInBackground(Review... reviews) {
            reviewDao.delete(reviews[0]);
            return null;
        }
    }*/
}
