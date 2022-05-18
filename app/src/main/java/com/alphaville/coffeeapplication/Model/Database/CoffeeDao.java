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
     *
     * @param product to be inserted
     */
    @Insert
    void insert(CoffeeProduct product);

    /**
     * Update CoffeeProduct in database
     *
     * @param product to be updated
     */
    @Update
    void update(CoffeeProduct product);

    /**
     * Query for all CoffeeProducts in the database.
     *
     * @return a list of all CoffeeProducts
     */
    @Query("SELECT * FROM products ORDER BY name DESC")
    LiveData<List<CoffeeProduct>> getAllProducts();

    /**
     * Method to filter coffee
     *
     * @param name
     * @param acidityRoof
     * @param acidityFloor
     * @param bodyRoof
     * @param bodyFloor
     * @param sweetnessRoof
     * @param sweetnessFloor
     * @param taste
     * @param country
     * @param isLiked
     * @param minElevation
     * @param maxElevation
     * @param process
     * @return
     */
    @Query("SELECT * FROM products WHERE name LIKE '%' || :name || '%' " +
            "AND acidity >= :acidityFloor AND acidity <= :acidityRoof " +
            "AND body >= :bodyFloor AND body <= :bodyRoof " +
            "AND sweetness >= :sweetnessFloor AND sweetness <= :sweetnessRoof " +
            "AND Taste = CASE WHEN :taste = '' THEN Taste ELSE :taste END " +
            "AND Country = CASE WHEN :country = '' THEN Country ELSE :country END " +
            "AND isLiked = :isLiked " +
            "AND elevation >= :minElevation AND elevation <= :maxElevation " +
            "AND Process = CASE WHEN :process = '' THEN Process ELSE :process END ")
    LiveData<List<CoffeeProduct>> filter(String name, float acidityRoof, float acidityFloor,
                                         float bodyRoof, float bodyFloor,
                                         float sweetnessRoof, float sweetnessFloor,
                                         String taste, String country, boolean isLiked,
                                         int minElevation, int maxElevation,
                                         String process);

    /**
     * Returns a LiveData object containing a list of all values in taste column
     *
     * @return the LiveData object
     */
    @Query("SELECT taste FROM products")
    LiveData<List<String>> getTasteList();

    /**
     * Returns a LiveData object containing a list of all values in country column
     *
     * @return the LiveData object
     */
    @Query("SELECT country FROM products")
    LiveData<List<String>> getCountryList();

    /**
     * Returns a LiveData object containing a list of all values in process column
     *
     * @return the LiveData object
     */
    @Query("SELECT process FROM products")
    LiveData<List<String>> getProcessList();


}
