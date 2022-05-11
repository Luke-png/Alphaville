package com.alphaville.coffeeapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.FragmentHistoryBinding;
import com.alphaville.coffeeapplication.viewModels.HistoryTabViewModel;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;
import com.alphaville.coffeeapplication.views.adapters.HistoryResultAdapter;
import com.alphaville.coffeeapplication.views.util.SpacingItemDecorator;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private HistoryTabViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new ViewModelProvider(requireActivity()).get(HistoryTabViewModel.class);
        model.getSelected().observe(getViewLifecycleOwner(), new Observer<Review>() {
            @Override
            public void onChanged(Review review) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, ReviewInfoTabFragment.class, null)
                        .commit();
            }
        });

        setItemSpacing(15);

        binding.reviewList.setLayoutManager(new LinearLayoutManager(getActivity()));
        /**
         * This should initiate the adapter and recyclerview
         */
        binding.reviewList.setAdapter(new HistoryResultAdapter(model.getReviews(), model));
        /**
         * Listener that should be triggered everytime the user changes anything in the search-field.
         * This is if we want continuous updates while writing.
         */
        binding.searchHistory.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                binding.reviewList.setAdapter(new HistoryResultAdapter(model.searchInReviews(s), model));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                binding.reviewList.setAdapter(new HistoryResultAdapter(model.searchInReviews(s), model));
                return true;
            }
        });
        return view;
    }

    /**
     * Method for setting the space between objects in the history-tab recyclerview
     * @param spacing amount of spacing
     */
    private void setItemSpacing(int spacing){
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(spacing);
        binding.reviewList.addItemDecoration(itemDecorator);
    }
}
