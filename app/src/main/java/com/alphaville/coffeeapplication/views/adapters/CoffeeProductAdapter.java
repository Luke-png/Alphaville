package com.alphaville.coffeeapplication.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Database.CoffeeDao;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.viewModels.SearchListViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoffeeProductAdapter extends RecyclerView.Adapter<CoffeeProductAdapter.ViewHolder> {

    // Instansvariabler + konstruktor
    // Byt ut mot kaffekort senare
    private List<CoffeeProduct> coffeeProducts;
    private SearchListViewModel vm;
    private FragmentContainerView fcv;


    // Pass in the contact array into the constructor
    public CoffeeProductAdapter(List<CoffeeProduct> coffeeProducts, SearchListViewModel vm, FragmentContainerView fcv) {
        this.coffeeProducts = coffeeProducts;
        this.vm = vm;
        this.fcv = fcv;
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
        holder.like.setChecked(product.isLiked());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.selectItem(coffeeProducts.get(holder.getAdapterPosition()));
                fcv.setVisibility(View.VISIBLE);
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoffeeProduct original = coffeeProducts.get(holder.getAdapterPosition());
                CoffeeProduct updCoffeeProduct = new CoffeeProduct(original, holder.like.isChecked());
                vm.getRepository().update(updCoffeeProduct);
            }
        });

        fcv.findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fcv.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coffeeProducts.size();
    }

    public void setProducts(List<CoffeeProduct> products) {
        this.coffeeProducts = products;
        notifyDataSetChanged();
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
        public ToggleButton like;
        public LinearLayout card;

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
            like = (ToggleButton) itemView.findViewById(R.id.likeBtn3);

            card = (LinearLayout) itemView.findViewById(R.id.LinearItem);
        }
    }
}
