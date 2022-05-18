package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.CoffeeSearcher;
import com.alphaville.coffeeapplication.Model.Database.CoffeeProductRepository;
import com.alphaville.coffeeapplication.Model.Review;

import java.util.ArrayList;
import java.util.List;

// Denna viewmodel borde användas av både searchlistan & searchbaren/filter

public class SearchListViewModel extends AndroidViewModel {

    private final MutableLiveData<CoffeeProduct> selected = new MutableLiveData<CoffeeProduct>();
    private MutableLiveData<Filter> filter = new MutableLiveData<>();

    private LiveData<List<CoffeeProduct>> filteredList;
    private CoffeeProductRepository repository;

    public SearchListViewModel(@NonNull Application application) {
        super(application);
        repository = new CoffeeProductRepository(application);
        filteredList = Transformations.switchMap(filter, (input) ->
        {
            if(input == null /*|| (filter.getValue().getString().equals("")
                                    && (filter.getValue().getSweetness() == 0 && filter.getValue().getMaxElevation() == 0)
            )*/) {
                System.out.println("get all products");
                return repository.getAllProducts();
            }
            else {
                System.out.println("filter");
                return repository.filter(input);
            }
        });
    }

    public LiveData<List<CoffeeProduct>> getFilteredList() {
        return filteredList;
    }

    public void setFilter(String query,  int acidity, int body, int sweetness, String taste, String country
            ,boolean isLiked, int minElevation, int maxElevation, String process) {
        filter.setValue(new Filter(query, acidity, body, sweetness, taste, country, isLiked, minElevation, maxElevation, process));
    }

    public void selectItem(CoffeeProduct product) {
        selected.setValue(product);
    }
    public LiveData<CoffeeProduct> getSelected() {
        return selected;
    }

    /**
     * Filter class
     * Contains all the nessecary properties to filter
     */
    public class Filter {
        private String string, taste, country, process;
        // klockor, 0, 1, 2, 3, 4, 5
        private int acidity, body, sweetness, minElevation, maxElevation;
        private boolean isLiked;

        // Constructor
        public Filter(String string, int acidity, int body, int sweetness, String taste, String country,
                      boolean isLiked, int minElevation, int maxElevation, String process){
            this.string = string;
            this.acidity = acidity;
            this.body = body;
            this.sweetness = sweetness;
            this.taste = taste;
            this.country = country;
            this.isLiked = isLiked;
            this.minElevation = minElevation;
            this.maxElevation = maxElevation;
            this.process = process;
        }
        public String getString() {
            return string;
        }

        public int getAcidity() { return acidity; }
        public int getBody() { return body; }
        public int getSweetness() { return sweetness; }
        public String getTaste() { return taste; };
        public String getCountry() { return country; }
        public boolean getIsLiked() { return isLiked; }
        public int getMinElevation() { return minElevation; }
        public int getMaxElevation() { return maxElevation; }
        public String getProcess() { return process; }
    }
}
