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
        //binding = SearchListFragmentBinding.inflate(inflater, container, false);

        CoffeeProduct c1 = new CoffeeProduct("namn", "Sweden", 123, Roast.light, Process.dry,
                new ArrayList<>(), 2,2,2, "test", true);
        CoffeeProduct c2 = new CoffeeProduct("namn", "Sweden", 123, Roast.light, Process.dry,
                new ArrayList<>(),2, 2, 2, "test", true);

        coffeeProducts.add(c1);
        coffeeProducts.add(c2);

        viewModel = new ViewModelProvider(requireActivity()).get(SearchListViewModel.class);
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
                //rv.setAdapter(new CoffeeProductAdapter(model.filterList(s)));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //rv.setAdapter(new CoffeeProductAdapter(model.filterList(s)));
                return true;
            }
        });
        return v; //inflater.inflate(R.layout.search_list_fragment, container, false);
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