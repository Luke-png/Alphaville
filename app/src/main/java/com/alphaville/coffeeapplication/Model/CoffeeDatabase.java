package com.alphaville.coffeeapplication.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Review.class, CoffeeProduct.class}, version = 1)
public abstract class CoffeeDatabase extends RoomDatabase {

    private static CoffeeDatabase instance;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract ReviewDao reviewDao();

    public static CoffeeDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (CoffeeDatabase.class){
                instance= Room.databaseBuilder(context.getApplicationContext(),
                        CoffeeDatabase.class,"coffee_database").build();
            }
        }
        return instance;
    }
}
