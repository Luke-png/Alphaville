package com.alphaville.coffeeapplication.views;

import static com.alphaville.coffeeapplication.Model.enums.Process.dry;
import static com.alphaville.coffeeapplication.Model.enums.Roast.light;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
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

        //initRatingbar();
        initInputBox();
        initLocationBox();
        initSaveButton();
        initProductText();


    return view;
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

   /* public void initRatingbar(View view){
        ratingBar = view.findViewById(R.id.ratingBar);
   }*/

    /**
     * Initiates save button for text review
     */
    //TODO Remove test coffeeProduct
    public void initSaveButton() {
        binding.textSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("pressed");

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
