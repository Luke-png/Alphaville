package com.alphaville.coffeeapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private RecyclerView rv;
    private CoffeeProductAdapter adapter;
    List<Object> coffeProducts = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SearchListViewModel model = new ViewModelProvider(requireActivity()).get(SearchListViewModel.class);
        final Observer<List<Object>> nameObserver = new Observer<List<Object>>() {
            @Override
            public void onChanged(@Nullable final List<Object> t) {
                coffeProducts = t;
                // kanske måste notifya adapter / rv, men osäker borde gå utan
            }
        };

        View v = LayoutInflater.from(getContext()).inflate(R.layout.search_list_fragment,container,false);

        // Inflate the layout for this fragment
        rv = (RecyclerView) v.findViewById(R.id.RV_SearchList);

        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new CoffeeProductAdapter(coffeProducts);
        rv.setAdapter(adapter);
        return v; //inflater.inflate(R.layout.search_list_fragment, container, false);
    }
}