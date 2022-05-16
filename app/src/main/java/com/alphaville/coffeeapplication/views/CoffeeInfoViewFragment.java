package com.alphaville.coffeeapplication.views;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.CoffeeInfoViewFragmentBinding;
import com.alphaville.coffeeapplication.databinding.ReviewDataFragmentBinding;

import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;

/**
 * Class for handling a detailed view for a CoffeeProduct.
 */
public class CoffeeInfoViewFragment extends Fragment{

    private CoffeeInfoViewFragmentBinding binding;
    private SearchListViewModel viewModel;
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = CoffeeInfoViewFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel= new ViewModelProvider(getActivity()).get(SearchListViewModel.class);
        /*

         setCoffeeAttributes(hight, flavour, country, region, process, rostery, brand);
         setCoffeeInformation(name, info, description);
         setCoffeePicture(image);
         setClockTexts(firstClockText, secondClockText, thirdClockText);
         */


        /*
         * Observer
        */
        viewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<CoffeeProduct>() {
            @Override
            public void onChanged(CoffeeProduct coffeeProduct) {
                rebuildCoffeeInfo();
            }
        });

        //rebuildCoffeeInfo();

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


    public void rebuildCoffeeInfo(){
        CoffeeProduct selected = viewModel.getSelected().getValue();
        if (selected!=null) {
            // todo change to real attributes
            setCoffeeInformation(selected.getName());
            setCoffeeAttributes(selected.getElevation()+"", selected.getTaste(), selected.getCountry(), selected.getProcess());
        }
        //setCoffeePicture(image);
        //setClockTexts(firstClockText, secondClockText, thirdClockText);
    }

    private void setCoffeeInformation(String name){
        binding.coffeenameText.setText(name);
        binding.infoText.setText("infotext");
        binding.descriptionText.setText("description");
    }

    private void setCoffeeAttributes(String hight, String flavour, String country,
                                     String process){
        binding.hightText.setText(hight);
        binding.flavourText.setText(flavour);
        binding.countryText.setText(country);
        binding.processText.setText(process);
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
        try {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ReviewDataFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }

        catch(NullPointerException e){
            System.out.println("mainActivity missing FragmentManager or something");
        }

    }

}