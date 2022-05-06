package com.alphaville.coffeeapplication.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.viewModels.ViewModelEngine;

import java.util.ArrayList;
import java.util.List;

// Denna viewmodel borde användas av både searchlistan & searchbaren/filter
//
public class SearchListViewModel extends ViewModelEngine {

    private final MutableLiveData<List<CoffeeProduct>> filteredList = new MutableLiveData<List<CoffeeProduct>>();
    private final MutableLiveData<CoffeeProduct> selected = new MutableLiveData<CoffeeProduct>();

    public LiveData<List<CoffeeProduct>> getFilteredList() {
        return filteredList;
    }

    //Filtreringslogic. Borde skötas någon annanstans. T.ex. hjälpklass till databasen.
    //getFilteredList borde innehålla den filtrerade listan.
    /*
    public List<CoffeeProduct> filterList(String s) {
        List<CoffeeProduct> newList = new ArrayList<>();
        for(int i = 0; i < filteredList.getValue().size(); i++){
            if(validProduct(filteredList.getValue().get(i), s)){
                newList.add(filteredList.getValue().get(i));
            }
        }
        return newList;
        // Ungefär vad som ska ske, dock får man göra om till LiveData
        // filteredList = CoffeeDatabase.getInstance().filter(param1, param2);
    }

    private boolean validProduct(CoffeeProduct r, String s){
        char coffeeLetter, stringLetter;
        for(int i = 0; i < s.length() && i < r.getName().length(); i++){
            coffeeLetter = r.getName().charAt(i);
            stringLetter = s.charAt(i);
            coffeeLetter = Character.toLowerCase(coffeeLetter);
            stringLetter = Character.toLowerCase(stringLetter);
            if(Character.compare(coffeeLetter, stringLetter) != 0){
                return false;
            }
        }
        return true;
    }*/

    public void selectItem(CoffeeProduct product) {
        selected.setValue(product);
    }
    public LiveData<CoffeeProduct> getSelected() {
        return selected;
    }

}
