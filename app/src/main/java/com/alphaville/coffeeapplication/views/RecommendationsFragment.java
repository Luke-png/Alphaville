package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

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


    private RecTabViewModel model;
    private GridView recGrid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        RecAdapter recAdapter = new RecAdapter(getActivity(),fillGridView());
        View view = inflater.inflate(R.layout.fragment_recommendations, container, false);
        recGrid = (GridView) view.findViewById(R.id.recGrid);
        recGrid.setAdapter(recAdapter);


        return view;

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
