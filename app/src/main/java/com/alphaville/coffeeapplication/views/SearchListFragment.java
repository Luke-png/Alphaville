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
import com.alphaville.coffeeapplication.Model.enums.Roast;
import com.alphaville.coffeeapplication.Model.enums.Process;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;
import com.alphaville.coffeeapplication.views.util.SpacingItemDecorator;
import com.alphaville.coffeeapplication.views.adapters.CoffeeProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchListFragment extends Fragment {

    private RecyclerView rv;
    private CoffeeProductAdapter adapter;
    private FragmentContainerView fcv;
    private SearchListViewModel viewModel;
    private SearchView sv;
    List<CoffeeProduct> coffeeProducts = new ArrayList<>(); // Get model through ViewModel instead.


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity()).get(SearchListViewModel.class);
        viewModel.setFilter("",0,0, 0, "", "Peru", false, 0, 10000, "");
        viewModel.getFilteredList().observe(getViewLifecycleOwner(), new Observer<List<CoffeeProduct>>() {
            @Override
            public void onChanged(@Nullable List<CoffeeProduct> coffeeProducts) {
                adapter.setProducts(coffeeProducts);
            }
        });

        View v = inflater.inflate(R.layout.search_list_fragment,container,false);

        rv = (RecyclerView) v.findViewById(R.id.RV_SearchList);
        fcv = (FragmentContainerView) v.findViewById(R.id.rec_DetailView);
        sv = (SearchView) v.findViewById(R.id.searchInSearchTab);

        fcv.setVisibility(View.INVISIBLE);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new CoffeeProductAdapter(coffeeProducts, viewModel, fcv);
        rv.setAdapter(adapter);

        initItemSpacing(15);

        /**
         * Listener that should be triggered everytime the user changes anything in the search-field.
         * This is if we want continuous updates while writing.
         */
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                viewModel.setFilter(s, 0, 0, 0, "", "", false, 0, 10000, "");
                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                viewModel.setFilter(s, 0, 0, 0, "", "", false, 0, 10000, "");
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return v;
    }

    /**
     * Adds some distance between the items in a RecyclerView
     * @param spacing amount of spacing
     */
    private void initItemSpacing(int spacing) {
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(spacing);
        rv.addItemDecoration(itemDecorator);
    }
}