package com.alphaville.coffeeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alphaville.coffeeapplication.model.CoffeeProduct;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoffeeProductAdapter extends RecyclerView.Adapter<CoffeeProductAdapter.ViewHolder> {

    // Instansvariabler + konstruktor
    // Byt ut mot kaffekort senare
    private List<CoffeeProduct> coffeeProducts;

    // Pass in the contact array into the constructor
    public CoffeeProductAdapter(List<CoffeeProduct> coffeeProducts) {
        this.coffeeProducts = coffeeProducts;
    }

    @NonNull
    @Override
    public CoffeeProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout -- byt senare ut NAMEOFCARD mot kortet för en product (XML)
        View contactView = inflater.inflate(R.layout.search_result_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeProductAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        CoffeeProduct product = coffeeProducts.get(position);

        holder.title.setText(product.getName() + "");
        holder.match.setText("match??");
        holder.height.setText(product.getElevation() + "");
        holder.country.setText(product.getCountry() + "");
        holder.process.setText(product.getProcess().toString() + "");

    }

    @Override
    public int getItemCount() {
        return coffeeProducts.size();
    }

    // ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView title;
        public TextView match;
        public TextView height;
        public TextView country;
        public TextView process;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.sr_title);
            match = (TextView) itemView.findViewById(R.id.sr_match);
            height = (TextView) itemView.findViewById(R.id.sr_height);
            country = (TextView) itemView.findViewById(R.id.sr_country);
            process = (TextView) itemView.findViewById(R.id.sr_process);

        }
    }
}
