package com.alphaville.coffeeapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

// Denna viewmodel borde användas av både searchlistan & searchbaren/filter
//
public class SearchListViewModel extends ViewModel {

    // Gör senare om till en lista av kaffeproduktobjekt, denna lista ska sorteras baseras
    // på sök / filter
    private final MutableLiveData<List<Object>> filteredList = new MutableLiveData<List<Object>>();

    public LiveData<List<Object>> getFilteredList() {
        return filteredList;
    }
    // Fixa via modellen att filteredList uppdateras baserat på filter & sök
}
