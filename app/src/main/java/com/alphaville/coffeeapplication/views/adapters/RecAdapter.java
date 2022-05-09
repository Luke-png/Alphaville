package com.alphaville.coffeeapplication.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;

import com.alphaville.coffeeapplication.GridCard;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.views.adapters.RecAdapter.GridCardViewHolder;
import com.google.android.gms.ads.mediation.Adapter;

import java.util.ArrayList;

public class RecAdapter extends GridView.Adapter<RecAdapter.GridCardViewHolder> {

    private ArrayList<GridCard> gridCardHolder;
    private Context context;

    public RecAdapter(ArrayList<GridCard> gridCardHolder){
        this.gridCardHolder = gridCardHolder;
    }

    @NonNull
    @Override
    public GridCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_history_list_item, parent, false);

        return new GridCardViewHolder(view);
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

    public static class GridCardViewHolder extends GridView. {



        public GridCardViewHolder(@NonNull View itemView) {
            super(itemView);

        }




    }

}

/*@RequiresApi(api = Build.VERSION_CODES.M)
        public void setReviewInfo(Review item){
            name.setText(item.getCoffeeProduct().getName());
            country.setText(item.getCoffeeProduct().getCountry());
            process.setText("Process: " + item.getCoffeeProduct().getProcess().toString());
            reviewDate.setText(item.getCreationTimeAsString());
            drinktype.setText("Review for " + item.getDrinkCategory());
            //image.setImageIcon(item.getCoffeeProduct().getImage());
            rating.setRating((float) item.getRating());

        }*/

/*@Override
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

    */