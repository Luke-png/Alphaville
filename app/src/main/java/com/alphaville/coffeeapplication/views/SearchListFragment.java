package com.alphaville.coffeeapplication.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;
import com.alphaville.coffeeapplication.views.adapters.CoffeeProductAdapter;
import com.alphaville.coffeeapplication.views.util.SpacingItemDecorator;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private float tasteSliderValueFrom = 0.0F;
    private float tasteSliderValueTo = 10.0F;
    private float tasteSliderStepSize = 1.0F;

    private AutoCompleteTextView taste_actv;
    private AutoCompleteTextView country_actv;

    private CheckBox liked_checkbox;

    private RangeSlider elevation_slider;

    private AutoCompleteTextView process_actv;


    List<CoffeeProduct> coffeeProducts = new ArrayList<>(); // Get model through ViewModel instead.
    List<Review> coffeReviews = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity()).get(SearchListViewModel.class);
        viewModel.getFilteredList().observe(getViewLifecycleOwner(), coffeeProducts -> adapter.setProducts(coffeeProducts));
        viewModel.getReviewList().observe(getViewLifecycleOwner(), coffeReviews -> adapter.setReviews(coffeReviews));
        View v = inflater.inflate(R.layout.search_list_fragment, container, false);

        rv = v.findViewById(R.id.RV_SearchList);
        fcv = v.findViewById(R.id.rec_DetailView);
        sv = v.findViewById(R.id.searchInSearchTab);
        filter_button = v.findViewById((R.id.filter_button));


        fcv.setVisibility(View.INVISIBLE);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new CoffeeProductAdapter(coffeeProducts, viewModel, fcv, coffeReviews);
        rv.setAdapter(adapter);

        filterDialog = new Dialog(getActivity());
        filterDialog.setContentView(R.layout.filter_dialog);
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

    /**
     * Initiates filter button
     */
    private void initFilterButton() {
        filter_button.setOnClickListener(view -> filterDialog.show());
    }

    /**
     * Initiates filter dialog
     */
    private void initFilterDialog() {
        ImageButton close_button = filterDialog.findViewById(R.id.close_button);
        close_button.setOnClickListener(view -> filterDialog.dismiss());

        ImageButton filter_reset_button = filterDialog.findViewById(R.id.filter_reset_button);
        filter_reset_button.setOnClickListener(view -> resetFilter());
        initTasteSliders();
        initTasteDropDown();
        initCountryDropDown();
        initIsLikedCheckbox();
        initElevationSlider();
        initProcessDropDown();
    }

    /**
     * Initiates sliders for acidity, body and sweetness
     */
    private void initTasteSliders() {


        acid_slider = filterDialog.findViewById(R.id.acid_slider);
        body_slider = filterDialog.findViewById(R.id.body_slider);
        sweet_slider = filterDialog.findViewById(R.id.sweet_slider);


        //Init acid slider
        acid_slider.setValueFrom(tasteSliderValueFrom);
        acid_slider.setValueTo(tasteSliderValueTo);
        acid_slider.setStepSize(tasteSliderStepSize);
        acid_slider.setValues(tasteSliderValueFrom, tasteSliderValueTo);

        //Init body slider
        body_slider.setValueFrom(tasteSliderValueFrom);
        body_slider.setValueTo(tasteSliderValueTo);
        body_slider.setStepSize(tasteSliderStepSize);
        body_slider.setValues(tasteSliderValueFrom, tasteSliderValueTo);

        //Init sweet slider
        sweet_slider.setValueFrom(tasteSliderValueFrom);
        sweet_slider.setValueTo(tasteSliderValueTo);
        sweet_slider.setStepSize(tasteSliderStepSize);
        sweet_slider.setValues(tasteSliderValueFrom, tasteSliderValueTo);


        //Changes label of slider value to match 0-5
        acid_slider.setLabelFormatter(value -> String.valueOf(value));

        //Changes label of slider value to match 0-5
        body_slider.setLabelFormatter(value -> String.valueOf(value));

        //Changes label of slider value to match 0-5
        sweet_slider.setLabelFormatter(value -> String.valueOf(value));

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

    /**
     * Initiates dropdown menu for tastes
     */
    private void initTasteDropDown() {
        taste_actv = filterDialog.findViewById(R.id.taste_actv);

        viewModel.getNoDupesTasteList().observe(getViewLifecycleOwner(), tastes -> {
            ArrayAdapter<String> tasteAdapter = new ArrayAdapter<>(requireContext(), R.layout.filter_list_item,
                    tastes);
            taste_actv.setAdapter(tasteAdapter);
        });

        taste_actv.setOnItemClickListener((adapterView, view, i, l) -> {
            filterSearch();
            adapter.notifyDataSetChanged();
        });
    }

    /**
     * Initiates dropdown menu for countries
     */
    private void initCountryDropDown() {
        country_actv = filterDialog.findViewById(R.id.country_actv);

        viewModel.getNoDupesCountryList().observe(getViewLifecycleOwner(), countries -> {
            ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(requireContext(), R.layout.filter_list_item,
                    countries);
            country_actv.setAdapter(countryAdapter);
        });

        country_actv.setOnItemClickListener((adapterView, view, i, l) -> {
            filterSearch();
            adapter.notifyDataSetChanged();
        });

    }

    /**
     * Initiates checkbox for isLiked boolean
     */
    private void initIsLikedCheckbox() {
        liked_checkbox = filterDialog.findViewById(R.id.liked_checkbox);

        liked_checkbox.setOnClickListener(view -> {
            filterSearch();
            adapter.notifyDataSetChanged();
        });
    }

    /**
     * Initiates elevation slider for filtering
     */
    private void initElevationSlider() {
        elevation_slider = filterDialog.findViewById(R.id.elevation_slider);

        int maxElevation = 11000; // This misses elevations of  190164, 190164, 110000 in database,
        // but they incorrect freak values
        float stepSize = 100.0F;
        elevation_slider.setValueFrom(0);
        elevation_slider.setValueTo(maxElevation);
        elevation_slider.setValues(0F, (float) maxElevation);
        elevation_slider.setStepSize(stepSize);


        elevation_slider.addOnChangeListener((slider, value, fromUser) -> {
            filterSearch();
            adapter.notifyDataSetChanged();
        });

    }

    /**
     * Initiates dropdown menu for processes
     */
    private void initProcessDropDown() {
        process_actv = filterDialog.findViewById(R.id.process_actv);

        viewModel.getNoDupesProcessList().observe(getViewLifecycleOwner(), processes -> {
            ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(requireContext(), R.layout.filter_list_item,
                    processes);
            process_actv.setAdapter(countryAdapter);
        });

        process_actv.setOnItemClickListener((adapterView, view, i, l) -> {
            filterSearch();
            adapter.notifyDataSetChanged();
        });

    }

    /**
     * Resets the filter by setting all values to defaul
     */
    private void resetFilter() {
        acid_slider.setValues(tasteSliderValueFrom, tasteSliderValueTo);
        body_slider.setValues(tasteSliderValueFrom, tasteSliderValueTo);
        sweet_slider.setValues(tasteSliderValueFrom, tasteSliderValueTo);

        taste_actv.setText("");
        country_actv.setText("");
        process_actv.setText("");

        liked_checkbox.setChecked(false);

        elevation_slider.setValues(0F, 11000F);
        filterSearch();
        adapter.notifyDataSetChanged();
    }

    /**
     * Filters the results based on current inputted filter values
     */
    private void filterSearch() {

        int acidLower = (int) (float) Collections.min(acid_slider.getValues());
        int acidUpper = (int) (float) Collections.max(acid_slider.getValues());
        int bodyLower = (int) (float) Collections.min(body_slider.getValues());
        int bodyUpper = (int) (float) Collections.max(body_slider.getValues());
        int sweetLower = (int) (float) Collections.min(sweet_slider.getValues());
        int sweetUpper = (int) (float) Collections.max(sweet_slider.getValues());

        int minElevation = (int) (float) Collections.min(elevation_slider.getValues());
        int maxElevation = (int) (float) Collections.max(elevation_slider.getValues());

        boolean isLiked = liked_checkbox.isChecked();

        //TODO Create filters for process
        viewModel.setFilter(sv.getQuery().toString(), taste_actv.getText().toString(),
                country_actv.getText().toString(), process_actv.getText().toString(), acidUpper, acidLower, bodyUpper,
                bodyLower, sweetUpper, sweetLower, minElevation, maxElevation, isLiked);
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