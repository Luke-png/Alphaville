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
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;
import com.alphaville.coffeeapplication.views.adapters.RecAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Placeholder class

public class RecommendationsFragment extends Fragment {

    private FragmentRecommendationsBinding binding;
    private RecAdapter adapterDay;
    private RecAdapter adapterWeek;
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

        viewModel.getRankedList().observe(getViewLifecycleOwner(), coffeeProducts -> setUpAdapter(new ArrayList<>(coffeeProducts)));

        viewModel.getRankedList().observe(getViewLifecycleOwner(), new Observer<List<CoffeeProduct>>() {
            @Override
            public void onChanged(@Nullable List<CoffeeProduct> coffeeProducts) {
                //adapter.setProducts(coffeeProducts);
                if (coffeeProducts == null){
                    setUpAdapter(new ArrayList<>(viewModel.getRankedList().getValue()));
                }else{
                    setUpAdapter(new ArrayList<>(coffeeProducts));
                }
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

    private void setUpAdapter(ArrayList<CoffeeProduct> coffeeProducts){
        /**
         * Creates the adepter with the appropriate data
         */
        adapterWeek = new RecAdapter(getActivity(),fillWeek(coffeeProducts),R.layout.rec_card, recDetail,shadow,true, viewModel);
        adapterDay = new RecAdapter(getActivity(),fillDay(coffeeProducts),R.layout.dagens, recDetail,shadow,false, viewModel);

        /**
         * Binds recGrid with the adepter
         */
        binding.veckansRecGrid.setAdapter(adapterWeek);
        binding.dagensRecGrid.setAdapter(adapterDay);
    }


    private ArrayList<CoffeeProduct> fillWeek(ArrayList<CoffeeProduct> coffeeProducts){
        ArrayList<CoffeeProduct> coffee = new ArrayList<>(coffeeProducts);
        Collections.shuffle(coffee);

        return coffee;
    }

    private ArrayList<CoffeeProduct> fillDay(ArrayList<CoffeeProduct> coffeeProducts){
        ArrayList<CoffeeProduct> coffee = new ArrayList<>(coffeeProducts);

        return coffee;
    }

}
