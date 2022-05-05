package com.alphaville.coffeeapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alphaville.coffeeapplication.Model.CoffeeProduct;

import java.util.ArrayList;
import java.util.List;

public class SearchListFragment extends Fragment {

    private RecyclerView rv;
    private CoffeeProductAdapter adapter;
    private FragmentContainerView fcv;
    List<CoffeeProduct> coffeeProducts = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        coffeeProducts.add(new CoffeeProduct());
        coffeeProducts.add(new CoffeeProduct());

        SearchListViewModel viewModel = new ViewModelProvider(requireActivity()).get(SearchListViewModel.class);
        viewModel.getFilteredList().observe(getViewLifecycleOwner(), new Observer<List<CoffeeProduct>>() {
            @Override
            public void onChanged(@Nullable List<CoffeeProduct> p) {
                coffeeProducts = p;
                // kanske måste notifya adapter / rv, men osäker borde gå utan
            }
        });

        /*
        final Observer<List<CoffeeProduct>> nameObserver = new Observer<List<CoffeeProduct>>() {
            @Override
            public void onChanged(@Nullable List<CoffeeProduct> t) {
                coffeProducts = t;
                // kanske måste notifya adapter / rv, men osäker borde gå utan
            }
        };*/

        View v = inflater.inflate(R.layout.search_list_fragment,container,false);
        //return inflater.inflate(R.layout.fragment_search,container,false);
        // Inflate the layout for this fragment
        rv = (RecyclerView) v.findViewById(R.id.RV_SearchList);
        fcv = (FragmentContainerView) v.findViewById(R.id.FCV_DetailView);
        fcv.setVisibility(View.INVISIBLE);

        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new CoffeeProductAdapter(coffeeProducts, viewModel, fcv);
        rv.setAdapter(adapter);
        return v; //inflater.inflate(R.layout.search_list_fragment, container, false);
    }
}