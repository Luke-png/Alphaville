package com.alphaville.coffeeapplication.Model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Singleton class containing the Data Access Objects for every entity. Also handles asynchronous tasks from the repositories.
 */
@Database(entities = {Review.class, CoffeeProduct.class}, version = 2)
public abstract class CoffeeDatabase extends RoomDatabase {

    private static CoffeeDatabase instance;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * @return the Data Access Object for CoffeeProducts.
     */
    public abstract CoffeeDao coffeeDao();

    /**
     * @return the Data Access Object for Reviews.
     */
    public abstract ReviewDao reviewDao();

    /**
     * Gets the CoffeeDatabase instance in the given context. If a CoffeeDatabase does not exist in the given context yet, then it creates it.
     * @param context where database is created
     * @return database instance
     */
    public static CoffeeDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (CoffeeDatabase.class){
                instance= Room.databaseBuilder(context.getApplicationContext(),
                        CoffeeDatabase.class,"coffee_database").fallbackToDestructiveMigration().build();
            }
        }
        return instance;
    }

    // todo pre-populate database with coffee product data on creation

}
