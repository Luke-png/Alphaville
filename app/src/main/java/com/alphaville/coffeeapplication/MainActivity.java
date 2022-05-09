package com.alphaville.coffeeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.alphaville.coffeeapplication.views.HistoryFragment;
import com.alphaville.coffeeapplication.views.RecommendationsFragment;
import com.alphaville.coffeeapplication.views.SearchListFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView, SearchListFragment.class, null)
                    .commit();
        }
*/

        //add the navbar and connect the listener navlistener
        NavigationBarView barView = findViewById(R.id.bottom_navigation);
        barView.setOnItemSelectedListener(navlistener);

    }

    //create the navbar with appropriate buttons
    private NavigationBarView.OnItemSelectedListener navlistener =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_recommendations:
                            selectedFragment = new RecommendationsFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchListFragment();
                            break;
                        case R.id.nav_history:
                            selectedFragment = new HistoryFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };


}