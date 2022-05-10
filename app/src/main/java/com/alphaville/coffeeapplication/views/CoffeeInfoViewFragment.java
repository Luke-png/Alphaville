package com.alphaville.coffeeapplication.views;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.CoffeeInfoViewFragmentBinding;
import com.alphaville.coffeeapplication.databinding.ReviewDataFragmentBinding;
import com.alphaville.coffeeapplication.viewModels.CoffeeInfoViewModel;

/**
 * Class for handling a detailed view for a CoffeeProduct.
 */
public class CoffeeInfoViewFragment extends Fragment {

    private CoffeeInfoViewFragmentBinding binding;
    private CoffeeInfoViewModel model;

    @Override
    public View onCreateView (LayoutInflater inflater,
                          ViewGroup container,
                          Bundle savedInstanceState) {
        binding = CoffeeInfoViewFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        /*
        setCoffeeAttributes(hight, flavour, country, region, process, rostery, brand);
        setCoffeeInformation(name, info, description);
        setCoffeePicture(image);
        setClockTexts(firstClockText, secondClockText, thirdClockText);
         */

        //listener for the review button
        binding.reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReviewPage();
            }
        });
        return view;

/*
        //listener for the heart button (the boolean value in model needs to reverse)
        binding.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.changeLikeStatus(binding.likeBtn.isChecked());
            }
        });
        */
    }

    private void setCoffeeInformation(String name, String info, String description){
        binding.coffeenameText.setText(name);
        binding.infoText.setText(info);
        binding.descriptionText.setText(description);
    }

    private void setCoffeeAttributes(String hight, String flavour, String country, String region,
                                     String process, String rostery, String brand){
        binding.hightText.setText(hight);
        binding.flavourText.setText(flavour);
        binding.countryText.setText(country);
        binding.regionText.setText(region);
        binding.processText.setText(process);
        binding.rosteryText.setText(rostery);
        binding.brandText.setText(brand);
    }

    //Don't know which setImage-method to use here
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setCoffeePicture(Icon image){
        binding.coffeepicture.setImageIcon(image);
    }

    private void setClockTexts(String first, String second, String third){
        binding.clock1Text.setText(first);
        binding.clock2Text.setText(second);
        binding.clock2Text.setText(third);

    }
    private void openReviewPage(){
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ReviewDataFragment.class, null)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit();
    }
}