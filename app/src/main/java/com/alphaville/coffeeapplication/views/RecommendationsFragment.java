package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.FragmentRecommendationsBinding;
import com.alphaville.coffeeapplication.viewModels.RecTabViewModel;
import com.alphaville.coffeeapplication.views.adapters.RecAdapter;

import java.util.ArrayList;
import java.util.List;

//Placeholder class

public class RecommendationsFragment extends Fragment {

    private FragmentRecommendationsBinding binding;
    private RecAdapter adapter;
    private RecTabViewModel viewModel;
    private FragmentContainerView recDetail;
    private View shadow;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRecommendationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(getActivity()).get(RecTabViewModel.class);


        recDetail = (FragmentContainerView) view.findViewById(R.id.rec_DetailView);
        recDetail.setVisibility(View.INVISIBLE);
        shadow = view.findViewById(R.id.shadowLayer);
        shadow.setVisibility(View.INVISIBLE);

        viewModel.getRankedList().observe(getViewLifecycleOwner(), new Observer<List<CoffeeProduct>>() {
            @Override
            public void onChanged(@Nullable List<CoffeeProduct> coffeeProducts) {
                //adapter.setProducts(coffeeProducts);
                //setUpAdapter(new ArrayList<>(coffeeProducts));
            }
        });

        shadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recDetail.setVisibility(View.INVISIBLE);
                shadow.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    //private void setUpAdapter(ArrayList<CoffeeProduct> coffeeProducts){
        /**
         * Creates the adepter with the appropriate data
         */
        //RecAdapter veckansAdapter = new RecAdapter(getActivity(),coffeeProducts,R.layout.rec_card, recDetail,shadow);
        //adapter = new RecAdapter(getActivity(),fillDagens(),R.layout.dagens, recDetail,shadow);

        /**
         * Binds recGrid with the adepter
         */
        //binding.veckansRecGrid.setAdapter(veckansAdapter);
        //binding.dagensRecGrid.setAdapter(adapter);
    //}


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

        return gridArrayList;
    }
}
