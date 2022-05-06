package com.alphaville.coffeeapplication.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.SearchListFragmentBinding;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;
import com.alphaville.coffeeapplication.views.util.SpacingItemDecorator;
import com.alphaville.coffeeapplication.views.adapters.CoffeeProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchListFragment extends Fragment {

    private RecyclerView rv;
    private CoffeeProductAdapter adapter;
    private FragmentContainerView fcv;
    private SearchListFragmentBinding binding;
    private SearchListViewModel model;
    List<CoffeeProduct> coffeeProducts = new ArrayList<>(); // Get model through ViewModel instead.


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SearchListFragmentBinding.inflate(inflater, container, false);
        model = new SearchListViewModel();
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

        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(15);
        binding.RVSearchList.addItemDecoration(itemDecorator);
        /**
         * Listener that should be triggered everytime the user changes anything in the search-field.
         * This is if we want continuous updates while writing.
         */
        binding.searchInSearchTab.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                //binding.RVSearchList.setAdapter(new CoffeeProductAdapter(model.filterList(s)));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //binding.RVSearchList.setAdapter(new CoffeeProductAdapter(model.filterList(s)));
                return true;
            }
        });
        return v; //inflater.inflate(R.layout.search_list_fragment, container, false);
    }
}