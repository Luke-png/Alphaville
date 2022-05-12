package com.alphaville.coffeeapplication.Model.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;

import java.util.List;

/**
 * Data access object for CoffeeProducts in the RoomDatabase.
 */
@Dao
public interface CoffeeDao {

    /**
     * Insert CoffeeProduct into database.
     * @param product to be inserted
     */
    @Insert
    void insert(CoffeeProduct product);

    /**
     * Update CoffeeProduct in database
     * @param product to be updated
     */
    @Update
    void update(CoffeeProduct product);

    /**
     * Query for all CoffeeProducts in the database.
     * @return a list of all CoffeeProducts
     */
    @Query("SELECT * FROM products ORDER BY name DESC")
    LiveData<List<CoffeeProduct>> getAllProducts();
}
