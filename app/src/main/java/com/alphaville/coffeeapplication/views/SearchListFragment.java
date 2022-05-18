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
                viewModel.setFilter(s, 0, 0, 0, "", "Ethiopia", false, 0, 10000, "");
                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                viewModel.setFilter(s, 0, 0, 0, "", "Ethiopia", false, 0, 10000, "");
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return v;
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
        RangeSlider acid_slider = filterDialog.findViewById(R.id.acid_slider);
        RangeSlider body_slider = filterDialog.findViewById(R.id.body_slider);
        RangeSlider sweet_slider = filterDialog.findViewById(R.id.sweet_slider);

        //Init sliders
        float valueFrom = 0.0F;
        float valueTo = 100.0F;
        int labelDenominator = 20; //
        float stepSize = 20.0F;

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
        acid_slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf(value / labelDenominator);
            }
        });

        //Changes label of slider value to match 0-5
        body_slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf(value / labelDenominator);
            }
        });

        //Changes label of slider value to match 0-5
        sweet_slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf(value / labelDenominator);
            }
        });

        //Acidity listener
        acid_slider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {

            }
        });

        //Body listener
        body_slider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {

            }
        });

        //Sweetness listener
        sweet_slider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {

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