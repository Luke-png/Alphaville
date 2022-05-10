package com.alphaville.coffeeapplication.views;

import static com.alphaville.coffeeapplication.Model.CoffeeProduct.Process.dry;
import static com.alphaville.coffeeapplication.Model.CoffeeProduct.Roast.light;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.alphaville.coffeeapplication.databinding.ReviewDataFragmentBinding;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.ReviewDataFragmentBinding;

import com.alphaville.coffeeapplication.viewModels.ReviewDataViewModel;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * ReviewDataFragment is the fragment for inputting and saving a text review
 */
public class ReviewDataFragment extends Fragment {

    private ReviewDataFragmentBinding binding;
    private ReviewDataViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ReviewDataFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ReviewDataViewModel();

        initInputBox();
        initLocationBox();
        initSaveButton();
        initProductText();

    return view;
    }

    @Override
    public void onResume() {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);
        super.onResume();
    }

    @Override
    public void onPause() {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        super.onPause();
    }

    /**
     * Initiates the text of which coffeeproduct is being reviewed
     */
    private void initProductText() {binding.currentProduct.setText("Reviewing "+viewModel.getActiveProduct().getName());
    }

    /**
     * Initiates the input box for inputting location of where the coffe drink was bought
     */
    private void initLocationBox() {binding.locationBox.setHint("Enter location");
    }

    /**
     * Initiates the input box for inputting text review
     */
    public void initInputBox() {
        binding.inputBox.setHint("Enter your review");
    }

    /**
     * Initiates save button for text review
     */
    public void initSaveButton() {
        binding.textSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("pressing save-button");
                double rating = binding.ratingBar.getRating();
                String reviewText = binding.inputBox.getText().toString();
                String location = binding.locationBox.getText().toString();
                //Test object
                CoffeeProduct cp = new CoffeeProduct("placeHolder", "testCountry",
                        99999, light, dry, new ArrayList<>(), "testDesc", true);

                viewModel.createReview(cp, binding.inputBox.getText().toString(),
                        binding.ratingBar.getRating(), binding.locationBox.getText().toString(),
                        "testCategory", new Timestamp(System.currentTimeMillis())
                        );
            }
        });
    }

}
