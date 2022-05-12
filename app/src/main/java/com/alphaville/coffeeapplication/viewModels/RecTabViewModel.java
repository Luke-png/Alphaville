package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alphaville.coffeeapplication.Model.Database.RecRepository;
import com.alphaville.coffeeapplication.Model.Database.ReviewRepository;
import com.alphaville.coffeeapplication.Model.Review;

import java.util.ArrayList;
import java.util.List;

public class RecTabViewModel extends AndroidViewModel {

    private RecRepository repository;
    private List <LiveData<List<Review>>> allRec;
    private LiveData<List<Review>> dag;
    private LiveData<List<Review>> vecka;


    public RecTabViewModel(@NonNull Application application) {
        super(application);
        //repository = new RecRepository(application);
        //allRec.add(repository.getAllReviews());
        //dag = allRec.get(0);
        //vecka = allRec.get(1);

    }

    public LiveData <List<Review>> getRectVecka(){
        return vecka;
    }
    public LiveData <List<Review>> getRectDag(){
        return dag;
    }

}