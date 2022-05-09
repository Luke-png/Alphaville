package com.alphaville.coffeeapplication.Model;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReviewDao {

    @Insert
    void insert(Review review);

    @Update
    void update(Review review);

    @Delete
    void delete(Review review);

    @Query("SELECT * FROM reviews")
    LiveData<List<Review>> getAllReviews();
}
