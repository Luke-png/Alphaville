package com.alphaville.coffeeapplication.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.model.CoffeeProduct;

import java.util.List;

/**
 * Array adapter for the coffee product list view in the search tab.
 */
public class SearchResultAdapter extends ArrayAdapter<CoffeeProduct>
{
    private final int resource;
    private final Context context;

    public SearchResultAdapter(Context context, int resource, List<CoffeeProduct> products)
    {
        super(context, resource, products);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(resource, parent, false);

        // gets all appropriate views for the list item
        TextView title      = convertView.findViewById(R.id.sr_title);
        TextView height     = convertView.findViewById(R.id.sr_height);
        TextView country    = convertView.findViewById(R.id.sr_country);
        TextView process    = convertView.findViewById(R.id.sr_process);
        TextView match      = convertView.findViewById(R.id.sr_match);
        ImageView image     = convertView.findViewById(R.id.sr_image);
        ImageButton liked   = convertView.findViewById(R.id.sr_liked_button);

        // current position's coffee product
        CoffeeProduct product = getItem(position);

        // sets values for each view of the product in the current position
        title.setText(product.getName());
        height.setText(context.getString(R.string.sr_height, product.getElevation()));
        country.setText(context.getString(R.string.sr_country, product.getCountry()));
        match.setText(context.getString(R.string.sr_match, 123)); // todo get match percentage from algorithm

        String processString;
        switch(product.getProcess()){
            case dry:
                processString = "Soltorkad";
                break;
            case wet:
                processString = "Blötlagd";
                break;
            case fermented:
                processString = "Fermenterad";
                break;
            case honey:
                processString = "Honey";
                break;
            default:
                processString = "Okänt";
                break;
        }

        process.setText(context.getString(R.string.sr_process, processString));


        // todo set search result image as well
        // image.setImage

        liked.setImageResource(product.isLiked() ? R.drawable.filled_heart : R.drawable.empty_heart);

        liked.setOnClickListener(view -> {
            // todo link to liking functionality in backend
        });

        convertView.setOnClickListener(view -> {
            // todo open detail view
        });

        return convertView;
    }
}
