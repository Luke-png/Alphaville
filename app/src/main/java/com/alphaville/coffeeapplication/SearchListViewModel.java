package com.alphaville.coffeeapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.alphaville.coffeeapplication.model.CoffeeProduct;

import java.util.List;

// Denna viewmodel borde användas av både searchlistan & searchbaren/filter
//
public class SearchListViewModel extends ViewModel {

    private final MutableLiveData<List<CoffeeProduct>> filteredList = new MutableLiveData<List<CoffeeProduct>>();

    public LiveData<List<CoffeeProduct>> getFilteredList() {
        return filteredList;
    }

    public void filterList(String param1, String param2) {
        // Ungefär vad som ska ske, dock får man göra om till LiveData
        // filteredList = CoffeeDatabase.getInstance().filter(param1, param2);
    }

}
