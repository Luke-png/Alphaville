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
     * @param name the searched name
     * @param acidityRoof the upper bound for acidity
     * @param acidityFloor the lower bound for acidity
     * @param bodyRoof the upper bound for body
     * @param bodyFloor the lower bound for body
     * @param sweetnessRoof the upper bound for sweetness
     * @param sweetnessFloor the lower bound for sweetness
     * @param taste the filtered taste
     * @param country the filtered country
     * @param isLiked if true, return only liked products. If false, return all regardless of like status
     * @param minElevation the lower bound for elevation
     * @param maxElevation the upper bound for elevation
     * @param process the filtered process
     * @return a livedata object containing coffee products matching filter
     */
    @Query("SELECT * FROM products WHERE name LIKE '%' || :name || '%' " +
            "AND acidity >= :acidityFloor AND acidity <= :acidityRoof " +
            "AND body >= :bodyFloor AND body <= :bodyRoof " +
            "AND sweetness >= :sweetnessFloor AND sweetness <= :sweetnessRoof " +
            "AND Taste = CASE WHEN :taste = '' THEN Taste ELSE :taste END " +
            "AND Country = CASE WHEN :country = '' THEN Country ELSE :country END " +
            "AND isLiked = CASE WHEN :isLiked = 0 THEN isLIked ELSE :isLiked END " +
            "AND elevation >= :minElevation AND elevation <= :maxElevation " +
            "AND Process = CASE WHEN :process = '' THEN Process ELSE :process END ")
    LiveData<List<CoffeeProduct>> filter(String name, float acidityRoof, float acidityFloor,
                                         float bodyRoof, float bodyFloor,
                                         float sweetnessRoof, float sweetnessFloor,
                                         String taste, String country, boolean isLiked,
                                         int minElevation, int maxElevation,
                                         String process);

    /**
     * Returns a LiveData object containing a list of distinct values in taste column
     *
     * @return the LiveData object
     */
    @Query("SELECT DISTINCT taste FROM products")
    LiveData<List<String>> getNoDupesTasteList();

    /**
     * Returns a LiveData object containing a list of distinct values in country column
     *
     * @return the LiveData object
     */
    @Query("SELECT DISTINCT country FROM products")
    LiveData<List<String>> getNoDupesCountryList();

    /**
     * Returns a LiveData object containing a list of distinct values in process column
     *
     * @return the LiveData object
     */
    @Query("SELECT DISTINCT process FROM products")
    LiveData<List<String>> getNoDupesProcessList();


}
