package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.alphaville.coffeeapplication.Model.ModelFacade;

/**
 * Abstract ViewModel-class that generates a single instance of the model for all its subclasses.
 */
public abstract class ViewModelEngine extends AndroidViewModel {

    /**
     * Single instance of model
     */
    private static final ModelFacade model = new ModelFacade();

    public ViewModelEngine(Application application) {
        super(application);
    }

    protected final ModelFacade getModel() {
        return model;
    }
}
