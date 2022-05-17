package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Database.CoffeeProductRepository;
import com.alphaville.coffeeapplication.Model.Review;

import java.util.ArrayList;
import java.util.List;

// Denna viewmodel borde användas av både searchlistan & searchbaren/filter
// Om problem: ta tillbaka ViewModelEngine
public class SearchListViewModel extends AndroidViewModel {

    private LiveData<List<CoffeeProduct>> filteredList;
    private final MutableLiveData<CoffeeProduct> selected = new MutableLiveData<CoffeeProduct>();
    private CoffeeProductRepository repository;

    public SearchListViewModel(@NonNull Application application) {
        super(application);
        repository = new CoffeeProductRepository(application);
        filteredList = repository.getAllProducts();
    }

    public LiveData<List<CoffeeProduct>> getFilteredList() {
        return filteredList;
    }
    public void selectItem(CoffeeProduct product) {
        selected.setValue(product);
    }
    public LiveData<CoffeeProduct> getSelected() {
        return selected;
    }

}
