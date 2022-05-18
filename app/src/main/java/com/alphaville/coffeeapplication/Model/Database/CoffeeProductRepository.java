package com.alphaville.coffeeapplication.Model.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Database.CoffeeDao;
import com.alphaville.coffeeapplication.Model.Database.CoffeeDatabase;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
    private LiveData<List<CoffeeProduct>> filteredProducts;

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

    /**
     *
     * @param input object which contains all the filters
     * @return
     */
    public LiveData<List<CoffeeProduct>> filter(SearchListViewModel.Filter input) {
        float acidityRoof, acidityFloor, bodyRoof, bodyFloor, sweetnessRoof, sweetnessFloor;
        String name = input.getString();
        String taste = input.getTaste();
        String country = input.getCountry();
        boolean isLiked = input.getIsLiked();
        int minElevation = input.getMinElevation();
        int maxElevation = input.getMaxElevation();
        String process = input.getProcess();

        acidityFloor = input.getAcidityLower();
        acidityRoof = input.getAcidityUpper();

        bodyFloor = input.getBodyLower();
        bodyRoof = input.getBodyUpper();

        sweetnessFloor = input.getSweetnessLower();
        sweetnessRoof = input.getSweetnessUpper();

        return coffeeDao.filter(name, acidityRoof, acidityFloor, bodyRoof, bodyFloor, sweetnessRoof, sweetnessFloor,
                taste, country, isLiked, minElevation, maxElevation, process);
    }

    /**
     * Calculates lower bound, will probably be replaced
     * @param i
     * @return
     */
    private float lowerBounds(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 0;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 8;
        }
        return 0;
    }
    private float upperBounds(int i) {
        switch (i) {
            case 0:
                return 10.1F;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 6;
            case 5:
                return 8;
            case 6:
                return 10.1F;
        }
        return 10.1F;
    }
}
