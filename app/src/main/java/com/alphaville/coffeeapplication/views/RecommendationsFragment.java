package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alphaville.coffeeapplication.GridCard;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.RecAdapter;

import java.util.ArrayList;

//Placeholder class

public class RecommendationsFragment extends Fragment {
    @Nullable

    GridView coursesGV;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //changeColor();

        // coursesGV = findViewById(R.id.idGVcourses);

       // ArrayList<GridCard> courseModelArrayList = new ArrayList<GridCard>();
       // courseModelArrayList.add(new GridCard("DSA", R.drawable.ic_filled_heart));
        //courseModelArrayList.add(new GridCard(("JAVA", R.drawable.ic_filled_heart));
        //courseModelArrayList.add(new GridCard(("C++", R.drawable.ic_filled_heart));
        //courseModelArrayList.add(new GridCard(("Python", R.drawable.ic_filled_heart));
        //courseModelArrayList.add(new GridCard(("Javascript", R.drawable.ic_filled_heart));
        //courseModelArrayList.add(new GridCard(("DSA", R.drawable.ic_filled_heart));

        //RecAdapter adapter = new RecAdapter(this, courseModelArrayList);
       // coursesGV.setAdapter(adapter);

        return inflater.inflate(R.layout.fragment_recommendations,container,false);
    }


    //private void changeColor(){

    //}

}
