package com.alphaville.coffeeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoffeeProductAdapter extends RecyclerView.Adapter<CoffeeProductAdapter.ViewHolder> {

    // Instansvariabler + konstruktor
    // Byt ut mot kaffekort senare
    private List<Object> coffeeProducts;

    // Pass in the contact array into the constructor
    public CoffeeProductAdapter(List<Object> coffeeProducts) {
        this.coffeeProducts = coffeeProducts;
    }

    @NonNull
    @Override
    public CoffeeProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout -- byt senare ut NAMEOFCARD mot kortet för en product (XML)
        View contactView = inflater.inflate(R.layout.placeholder_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeProductAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Object product = coffeeProducts.get(position);

        // Set item views based on your views and data model

        // Byt ut mot vad som faktiskt finns på ett kort senare

        TextView textView = holder.testTextView;
        textView.setText(product.toString());

    }

    @Override
    public int getItemCount() {
        return coffeeProducts.size();
    }

    // ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView testTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            testTextView = (TextView) itemView.findViewById(R.id.textView);

        }
    }
}
