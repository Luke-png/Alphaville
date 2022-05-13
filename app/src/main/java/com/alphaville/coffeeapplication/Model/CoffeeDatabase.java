package com.alphaville.coffeeapplication.Model;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alphaville.coffeeapplication.R;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Singleton class containing the Data Access Objects for every entity. Also handles asynchronous tasks from the repositories.
 */
@Database(entities = {Review.class, CoffeeProduct.class}, version = 1)
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
                        CoffeeDatabase.class,"coffee_database")
                        .addCallback(new Callback() // makes sure database gets populated on creation
                        {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                // on creation, pre-populate with data
                                databaseWriteExecutor.execute(() -> CoffeeDatabase.getInstance(context).prepopulate(context));
                            }
                        })
                        .fallbackToDestructiveMigration().build();
            }
        }
        return instance;
    }

    /**
     * Reads CoffeeProducts from CSV file in resources and inserts them into database.
     * @param context context through which to reach resources
     */
    private void prepopulate(Context context) {
        try {
            // creates reader for data
            CoffeeProductReader reader = new CoffeeProductReader(context);

            // inserts all products read into database
            for(CoffeeProduct product : reader.getCoffeeProducts())
                coffeeDao().insert(product);

            reader.close();
        }
        catch (Exception e){
            Log.d("database", "error in pre-population stage of database: " + e.toString());
        }
    }
}
