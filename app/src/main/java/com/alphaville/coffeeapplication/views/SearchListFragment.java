package com.alphaville.coffeeapplication.views;

import android.app.Dialog;
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
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.SeekBar;

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
    private ImageButton filter_button;
    private Dialog filterDialog;
    List<CoffeeProduct> coffeeProducts = new ArrayList<>(); // Get model through ViewModel instead.


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //binding = SearchListFragmentBinding.inflate(inflater, container, false);
/*
        CoffeeProduct c1 = new CoffeeProduct("namn", "Sweden", 123, Roast.light, Process.dry,
                new ArrayList<>(), 2,2,2, "test", true);
        CoffeeProduct c2 = new CoffeeProduct("namn2", "Sweden", 123, Roast.light, Process.dry,
                new ArrayList<>(),2, 2, 2, "test", true);

        coffeeProducts.add(c1);
        coffeeProducts.add(c2);
*/
        viewModel= new ViewModelProvider(getActivity()).get(SearchListViewModel.class);
        viewModel.getFilteredList().observe(getViewLifecycleOwner(), new Observer<List<CoffeeProduct>>() {
            @Override
            public void onChanged(@Nullable List<CoffeeProduct> coffeeProducts) {
                adapter.setProducts(coffeeProducts);
            }
        });

        View v = inflater.inflate(R.layout.search_list_fragment,container,false);
        //return inflater.inflate(R.layout.fragment_search,container,false);
        // Inflate the layout for this fragment
        rv = (RecyclerView) v.findViewById(R.id.RV_SearchList);
        fcv = (FragmentContainerView) v.findViewById(R.id.rec_DetailView);
        sv = (SearchView) v.findViewById(R.id.searchInSearchTab);
        filter_button = v.findViewById((R.id.filter_button));

        fcv.setVisibility(View.INVISIBLE);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new CoffeeProductAdapter(coffeeProducts, viewModel, fcv);
        rv.setAdapter(adapter);

        initItemSpacing(15);
        initFilterDialog();
        initFilterButton();


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

    private void initFilterButton() {
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialog.show();
            }
        });
    }

    private void initFilterDialog() {
        filterDialog = new Dialog(getActivity());
        filterDialog.setContentView(R.layout.filter_dialog);
        SeekBar acid_slider = filterDialog.findViewById(R.id.acid_slider);
        SeekBar body_slider = filterDialog.findViewById(R.id.body_slider);
        SeekBar sweet_slider = filter_button.findViewById(R.id.sweet_slider);

        //Acidity listener
        acid_slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Body listener
        body_slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Sweetness listener
        sweet_slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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