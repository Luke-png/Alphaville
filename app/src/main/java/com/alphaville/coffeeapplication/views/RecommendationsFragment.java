package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.FragmentRecommendationsBinding;
import com.alphaville.coffeeapplication.viewModels.HistoryTabViewModel;
import com.alphaville.coffeeapplication.viewModels.RecTabViewModel;
import com.alphaville.coffeeapplication.views.adapters.RecAdapter;

import java.util.ArrayList;
import java.util.List;

//Placeholder class

public class RecommendationsFragment extends Fragment {

    private FragmentRecommendationsBinding binding;
    private RecTabViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRecommendationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(RecTabViewModel.class);

        /**
         * Creates the adepter with the appropriate data
         */
        RecAdapter veckansAdapter = new RecAdapter(getActivity(),fillVeckans(),R.layout.rec_card);
        RecAdapter dagensAdapter = new RecAdapter(getActivity(),fillDagens(),R.layout.dagens);

        /**
         * Binds recGrid with the adepter
         */
        binding.veckansRecGrid.setAdapter(veckansAdapter);
        binding.dagensRecGrid.setAdapter(dagensAdapter);


        return view;
    }


    /**
     * Hardcoded test data
     */
    private ArrayList<GridCard> fillVeckans(){
        ArrayList<GridCard> gridArrayList = new ArrayList();
        gridArrayList.add(new GridCard("DSA", R.drawable.ic_filled_heart));
        gridArrayList.add(new GridCard("JAVA", R.drawable.ic_filled_heart));
        gridArrayList.add(new GridCard("C++", R.drawable.ic_filled_heart));
        gridArrayList.add(new GridCard("Python", R.drawable.ic_filled_heart));

        //gridArrayList = viewModel.getRectVecka().getValue();

        return gridArrayList;
    }

    private ArrayList<GridCard> fillDagens(){
        ArrayList<GridCard> gridArrayList = new ArrayList<GridCard>();
        gridArrayList.add(new GridCard("DSA", R.drawable.ic_filled_heart));

        //gridArrayList = viewModel.getRectVecka().getValue();

        viewModel.getRectDag();

        return gridArrayList;
    }

}
