package com.alphaville.coffeeapplication.views;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaville.coffeeapplication.GridCard;
import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.R;
import com.google.android.gms.ads.mediation.Adapter;

import java.util.ArrayList;

public class RecAdapter extends BaseAdapter {

    private ArrayList<GridCard> gridCardHolder;
    private Context context;

    public RecAdapter(Context context, ArrayList<GridCard> gridCardHolder){
        this.gridCardHolder = gridCardHolder;
        this.context = context;
    }

    @NonNull
    @Override
    public GridCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_history_list_item, parent, false);

        return new RecAdapter.GridCardViewHolder(view);
    }

    @Override
    public int getCount() {
        return gridCardHolder.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.rec_card, null);
        }
        return view;
    }

    public static class GridCardViewHolder extends GridView {

        private final TextView name;
        private final TextView country;
        private final TextView process;
        //private final TextView attr1;
        //private final TextView attr2;
        //private final TextView attr3;
        private final TextView reviewDate;
        private final TextView drinktype; //Something like "cappuccino"
        //private final ImageView image;
        private final RatingBar rating;

        public GridCardViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.review_cp_product_name);
            country = itemView.findViewById(R.id.review_cp_country);
            process = itemView.findViewById(R.id.review_cp_process);
            //attr1 = itemView.findViewById(R.id.review_cp_att1);
            //attr2 = itemView.findViewById(R.id.review_cp_att2);
            //attr3 = itemView.findViewById(R.id.review_cp_att3);
            reviewDate = itemView.findViewById(R.id.review_date);
            drinktype = itemView.findViewById(R.id.review_desc);
            //image = itemView.findViewById(R.id.review_image);
            rating = itemView.findViewById(R.id.review_rating);

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public void setReviewInfo(Review item){
            name.setText(item.getCoffeeProduct().getName());
            country.setText(item.getCoffeeProduct().getCountry());
            process.setText("Process: " + item.getCoffeeProduct().getProcess().toString());
            reviewDate.setText(item.getCreationTimeAsString());
            drinktype.setText("Review for " + item.getDrinkCategory());
            //image.setImageIcon(item.getCoffeeProduct().getImage());
            rating.setRating((float) item.getRating());

        }
    }

}