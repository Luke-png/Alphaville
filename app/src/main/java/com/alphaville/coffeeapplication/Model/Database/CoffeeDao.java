package com.alphaville.coffeeapplication.Model.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;

import java.util.List;

import kotlin.text.UStringsKt;

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

    @Query("SELECT * FROM products WHERE name LIKE '%' || :name || '%' " +
            "AND acidity >= :acidityFloor AND acidity < :acidityRoof " +
            "AND body >= :bodyFloor AND body < :bodyRoof " +
            "AND sweetness >= :sweetnessFloor AND sweetness < :sweetnessRoof " +
            "AND Name = CASE WHEN :taste = '' THEN Name ELSE :taste END " +
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
// 1 2 3 4 5 6


}
