package com.alphaville.coffeeapplication;

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

    /**
     * Method for picking out products that match the text-string the user is typing in search-tab.
     * @param s the text-string the user has typed
     * @return a list with matching products
     */
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




    /**
     * Method for comparing a single coffeeproduct with a text-string.
     * @param r the coffeeproduct that will be compared
     * @param s the text-string to match with the coffeeproduct
     * @return true if coffeeproduct matches the text-string, false if not
     */
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
    }

    public void selectItem(CoffeeProduct product) {
        selected.setValue(product);
    }
    public LiveData<CoffeeProduct> getSelected() {
        return selected;
    }

}
