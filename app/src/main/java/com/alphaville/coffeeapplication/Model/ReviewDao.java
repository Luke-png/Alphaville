package com.alphaville.coffeeapplication.Model;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * A Data Access Object (DAO) interface for saving {@link Review} objects to database.
 */
@Dao
public interface ReviewDao {

    /**
     * Inserts a review into the database
     * @param review the review to insert into the database
     */
    @Insert
    void insert(Review review);

    /**
     * Updates a review object if it exists in the database, based on primary key.
     * If no object with that primary key exists, no changes are made to the database.
     * @param review the review object to update
     */
    @Update
    void update(Review review);

    /**
     * Deletes a review object from the database
     * @param review the review object to delete
     */
    @Delete
    void delete(Review review);

    /**
     * Returns an observable LiveData object containing all {@link Review} objects in the database
     * @return the observable LiveData object
     */
    @Query("SELECT * FROM reviews")
    LiveData<List<Review>> getAllReviews();
}
