package com.alphaville.coffeeapplication.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CoffeeProductRepository {

    private CoffeeDao coffeeDao;
    private LiveData<List<CoffeeProduct>> allProducts;

    public CoffeeProductRepository(Application application){
        CoffeeDatabase database = CoffeeDatabase.getInstance(application);
        coffeeDao = database.coffeeDao();
        allProducts = coffeeDao.getAllProducts();
    }

    public void insert(CoffeeProduct product){
        CoffeeDatabase.databaseWriteExecutor.execute(()->{
            coffeeDao.insert(product);
        });
    }

    public void update(CoffeeProduct product){
        CoffeeDatabase.databaseWriteExecutor.execute(()->{
            coffeeDao.update(product);
        });
    }

    public LiveData<List<CoffeeProduct>> getAllProducts(CoffeeProduct product){
        return allProducts;
    }
}
