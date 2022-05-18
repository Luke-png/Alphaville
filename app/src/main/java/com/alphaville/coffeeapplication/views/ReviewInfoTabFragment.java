package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.FragmentReviewInfoTabBinding;
import com.alphaville.coffeeapplication.viewModels.HistoryTabViewModel;

public class ReviewInfoTabFragment extends Fragment {

    private FragmentReviewInfoTabBinding binding;
    private HistoryTabViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentReviewInfoTabBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        model = new ViewModelProvider(getActivity()).get(HistoryTabViewModel.class);
        closingView();

        model.getSelected().observe(getViewLifecycleOwner(), new Observer<Review>() {
            @Override
            public void onChanged(Review review) {
                System.out.println("setting values");
                initValues(review);
            }
        });
        return view;
    }

    /**
     * Method for setting all values in the view
     * @param review the object that will be displayed
     */
    private void initValues(Review review) {
        binding.nameText.setText(review.getCoffeeProduct().getName());
        binding.reviewText.setText(review.getTextReview());
        binding.locationText.setText(review.getLocation());
        binding.ratingBar2.setRating((float) review.getRating());
        binding.countryText2.setText(review.getCoffeeProduct().getCountry());
        binding.processText2.setText(review.getCoffeeProduct().getProcess().toString());
        //binding.userImage.setImageDrawable(review.getCoffeeProduct().getPicture());
        binding.attributeText1.setText("Sweetness");
        binding.attributeText2.setText("Acidity");
        binding.attributeText3.setText("Body");
        //binding.imageAttr1.setImageDrawable();
        //binding.imageAttr2.setImageDrawable();
        //binding.imageAttr3.setImageDrawable();
        CoffeeInfoViewFragment.setTasteClockImageSpecific(review.getCoffeeProduct().getSweetness(),binding.imageAttr1, 0, 100);
        CoffeeInfoViewFragment.setTasteClockImageSpecific(review.getCoffeeProduct().getAcidity(),binding.imageAttr2, 0, 10);
        CoffeeInfoViewFragment.setTasteClockImageSpecific(review.getCoffeeProduct().getBody(),binding.imageAttr3, 0, 10);
    }

    /**
     * This method notifies HistoryFragment when the close button has been pressed
     */
    private void closingView(){
        binding.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle ping = new Bundle();
                ping.putBoolean("closeBtn", true);
                getActivity().getSupportFragmentManager().setFragmentResult("requestkey", ping);
            }
        });
    }
}