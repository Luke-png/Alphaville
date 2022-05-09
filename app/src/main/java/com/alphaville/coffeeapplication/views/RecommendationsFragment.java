package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alphaville.coffeeapplication.GridCard;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.FragmentRecommendationsBinding;
import com.alphaville.coffeeapplication.viewModels.RecTabViewModel;
import com.alphaville.coffeeapplication.views.adapters.HistoryResultAdapter;
import com.alphaville.coffeeapplication.views.adapters.RecAdapter;

import java.util.ArrayList;

//Placeholder class

public class RecommendationsFragment extends Fragment {


    private FragmentRecommendationsBinding binding;
    private RecTabViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentRecommendationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        model = new RecTabViewModel();

        //View view = inflater.inflate(R.layout.fragment_recommendations, container, false);
        GridView gridView = view.findViewById(R.id.recGrid);

        //RecAdapter recAdapter = new RecAdapter(this, fillGridView());
        //gridView.setAdapter(recAdapter);
        binding.recGrid.setAdapter(new RecAdapter(fillGridView()));


        return view;

        binding.recGrid.setLayoutManager(new LinearLayoutManager(getActivity()));

        /**
         * This should initiate the adapter and recyclerview
         */
        binding.recGrid.setAdapter(new RecAdapter(fillGridView()));
        //GridView recGrid = this.findViewById(R.id.recGrid);


        //RecAdapter adapter = new RecAdapter(this, fillGridView());
        //recGrid.setAdapter(adapter);

        return inflater.inflate(R.layout.fragment_recommendations,container,false);
    }

    private ArrayList<GridCard> fillGridView(){
        ArrayList<GridCard> gridArrayList = new ArrayList<GridCard>();
        gridArrayList.add(new GridCard("DSA", R.drawable.ic_filled_heart));
        gridArrayList.add(new GridCard("JAVA", R.drawable.ic_filled_heart));
        gridArrayList.add(new GridCard("C++", R.drawable.ic_filled_heart));
        gridArrayList.add(new GridCard("Python", R.drawable.ic_filled_heart));
        gridArrayList.add(new GridCard("Javascript", R.drawable.ic_filled_heart));
        gridArrayList.add(new GridCard("DSA", R.drawable.ic_filled_heart));
        return gridArrayList;
    }

}
