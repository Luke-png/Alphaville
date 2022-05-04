package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alphaville.coffeeapplication.GridCard;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.RecAdapter;
import com.alphaville.coffeeapplication.databinding.ReviewDataFragmentBinding;
import com.alphaville.coffeeapplication.viewModels.ReviewDataViewModel;

import java.util.ArrayList;

//Placeholder class

public class RecommendationsFragment extends Fragment {
    @Nullable



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        GridView recGrid = this.findViewById(R.id.recGrid);


        RecAdapter adapter = new RecAdapter(this, fillGridView());
        recGrid.setAdapter(adapter);

        return inflater.inflate(R.layout.fragment_recommendations,container,false);
    }

    private ArrayList fillGridView(){
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
