package com.alphaville.coffeeapplication.views;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.enums.Roast;
import com.alphaville.coffeeapplication.Model.enums.Process;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;
import com.alphaville.coffeeapplication.views.util.SpacingItemDecorator;
import com.alphaville.coffeeapplication.views.adapters.CoffeeProductAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchListFragment extends Fragment {

    private RecyclerView rv;
    private CoffeeProductAdapter adapter;
    private FragmentContainerView fcv;
    private SearchListViewModel viewModel;
    private SearchView sv;
    private ImageButton filter_button;
    private Dialog filterDialog;

    private RangeSlider acid_slider;
    private RangeSlider body_slider;
    private RangeSlider sweet_slider;

    // Makes thumbvalues between 0-10
    private int valueDenominator = 10;

    List<CoffeeProduct> coffeeProducts = new ArrayList<>(); // Get model through ViewModel instead.


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity()).get(SearchListViewModel.class);
        viewModel.getFilteredList().observe(getViewLifecycleOwner(), coffeeProducts -> adapter.setProducts(coffeeProducts));

        View v = inflater.inflate(R.layout.search_list_fragment, container, false);

        rv = v.findViewById(R.id.RV_SearchList);
        fcv = v.findViewById(R.id.rec_DetailView);
        sv = v.findViewById(R.id.searchInSearchTab);
        filter_button = v.findViewById((R.id.filter_button));

        fcv.setVisibility(View.INVISIBLE);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new CoffeeProductAdapter(coffeeProducts, viewModel, fcv);
        rv.setAdapter(adapter);

        initItemSpacing(15);
        initFilterDialog();
        initFilterButton();
        filterSearch();


        /*
         * Listener that should be triggered everytime the user changes anything in the search-field.
         * This is if we want continuous updates while writing.
         */
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                filterSearch();
                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterSearch();
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return v;
    }

    private void initFilterButton() {
        filter_button.setOnClickListener(view -> filterDialog.show());
    }

    private void initFilterDialog() {
        filterDialog = new Dialog(getActivity());
        filterDialog.setContentView(R.layout.filter_dialog);

        //Init sliders
        float valueFrom = 0.0F;
        float valueTo = 100.0F;
        float stepSize = 10.0F;

        acid_slider = filterDialog.findViewById(R.id.acid_slider);
        body_slider = filterDialog.findViewById(R.id.body_slider);
        sweet_slider = filterDialog.findViewById(R.id.sweet_slider);


        //Init acid slider
        acid_slider.setValueFrom(valueFrom);
        acid_slider.setValueTo(valueTo);
        acid_slider.setStepSize(stepSize);

        //Init body slider
        body_slider.setValueFrom(valueFrom);
        body_slider.setValueTo(valueTo);
        body_slider.setStepSize(stepSize);

        //Init sweet slider
        sweet_slider.setValueFrom(valueFrom);
        sweet_slider.setValueTo(valueTo);
        sweet_slider.setStepSize(stepSize);


        //Changes label of slider value to match 0-5
        acid_slider.setLabelFormatter(value -> String.valueOf(value / valueDenominator));

        //Changes label of slider value to match 0-5
        body_slider.setLabelFormatter(value -> String.valueOf(value / valueDenominator));

        //Changes label of slider value to match 0-5
        sweet_slider.setLabelFormatter(value -> String.valueOf(value / valueDenominator));

        //Acidity listener
        acid_slider.addOnChangeListener((slider, value, fromUser) -> {
            filterSearch();
            adapter.notifyDataSetChanged();
        });

        //Body listener
        body_slider.addOnChangeListener((slider, value, fromUser) -> {
            filterSearch();
            adapter.notifyDataSetChanged();
        });

        //Sweetness listener
        sweet_slider.addOnChangeListener((slider, value, fromUser) -> {
            filterSearch();
            adapter.notifyDataSetChanged();
        });
    }

    private void filterSearch() {

        int acidLower = (int) (float) Collections.min(acid_slider.getValues()) / valueDenominator;
        int acidUpper = (int) (float) Collections.max(acid_slider.getValues()) / valueDenominator;
        int bodyLower = (int) (float) Collections.min(body_slider.getValues()) / valueDenominator;
        int bodyUpper = (int) (float) Collections.max(body_slider.getValues()) / valueDenominator;
        int sweetLower = (int) (float) Collections.min(sweet_slider.getValues()) / valueDenominator;
        int sweetUpper = (int) (float) Collections.max(sweet_slider.getValues()) / valueDenominator;

        int minElevation = 0;
        int maxElevation = 10000;

        boolean isLiked = false;

        //TODO Create filters for taste, country, process, isliked, elevation.
        viewModel.setFilter(sv.getQuery().toString(), "", "", "",
                acidUpper, acidLower, bodyUpper, bodyLower, sweetUpper, sweetLower, minElevation, maxElevation,
                isLiked);
    }

    /**
     * Adds some distance between the items in a RecyclerView
     *
     * @param spacing amount of spacing
     */
    private void initItemSpacing(int spacing) {
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(spacing);
        rv.addItemDecoration(itemDecorator);
    }
}