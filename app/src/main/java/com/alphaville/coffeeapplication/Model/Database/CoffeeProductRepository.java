package com.alphaville.coffeeapplication.Model.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Database.CoffeeDao;
import com.alphaville.coffeeapplication.Model.Database.CoffeeDatabase;

import java.util.List;

/**
 * Repository for CoffeeProducts stored in the database.
 */
public class CoffeeProductRepository {

    /**
     * Data Access Object for CoffeeProducts.
     */
    private CoffeeDao coffeeDao;
    /**
     * LiveData object for all CoffeeProducts in the database.
     */
    private LiveData<List<CoffeeProduct>> allProducts;

    public CoffeeProductRepository(Application application){
        CoffeeDatabase database = CoffeeDatabase.getInstance(application);
        coffeeDao = database.coffeeDao();
        allProducts = coffeeDao.getAllProducts();
    }

    /**
     * Basic insertion of CoffeeProduct.
     * @param product to insert
     */
    public void insert(CoffeeProduct product){
        CoffeeDatabase.databaseWriteExecutor.execute(()-> coffeeDao.insert(product));
    }

    /**
     * Updates product with specified id with information in product object
     * @param product contains id and new information of the product
     */
    public void update(CoffeeProduct product){
        CoffeeDatabase.databaseWriteExecutor.execute(()-> coffeeDao.update(product));
    }

    /**
     * Gets all CoffeeProducts (LiveData object)
     * @return the LiveData object containing all CoffeeProducts
     */
    public LiveData<List<CoffeeProduct>> getAllProducts(){
        return allProducts;
    }
}
