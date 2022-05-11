package com.alphaville.coffeeapplication.views;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.databinding.CoffeeInfoViewFragmentBinding;
import com.alphaville.coffeeapplication.viewModels.CoffeeInfoViewModel;
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
        viewModel= new ViewModelProvider(requireActivity()).get(SearchListViewModel.class);

        viewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<CoffeeProduct>() {
            @Override
            public void onChanged(CoffeeProduct coffeeProduct) {
                rebuildCoffeeInfo();
            }
        });

        rebuildCoffeeInfo();

        //listener for the review button
        binding.reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReviewPage(); //What class should do this? Probably not the model.
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
            setCoffeeAttributes(selected.getElevation() + "", selected.getTastes().toString(), selected.getCountry(), "region", selected.getProcess().toString(), selected.getRoast().toString(), "brand");
            setCoffeeInformation(selected.getName(), "Our special mixture", selected.getDescription());
        }
        //setCoffeePicture(image);
        //setClockTexts(firstClockText, secondClockText, thirdClockText);
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
        Intent intent = new Intent(getActivity(), ReviewActivity.class);
        startActivity(intent);
    }

}