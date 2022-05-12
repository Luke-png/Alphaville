package com.alphaville.coffeeapplication.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentContainerView;

import com.alphaville.coffeeapplication.views.GridCard;
import com.alphaville.coffeeapplication.R;

import java.util.ArrayList;

public class RecAdapter extends ArrayAdapter<GridCard> {

    private int layout;
    private GridCard gridCard;
    private FragmentContainerView recDetail;
    private View shadow;

    public RecAdapter(@NonNull Context context,@NonNull ArrayList<GridCard> gridCardHolder,int layout,
                      FragmentContainerView recDetail, View shadow){
        super(context,0, gridCardHolder);
        this.layout = layout;
        this.recDetail = recDetail;
        this.shadow = shadow;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup viewGroup) {

        View listitemView = view;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(layout, viewGroup, false);
        }

        gridCard = getItem(position);
        setupGridCard(listitemView);
        TextView cardName = listitemView.findViewById(R.id.gridCardText);


        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recDetail.setVisibility(View.VISIBLE);
                shadow.setVisibility(View.VISIBLE);
            }
        });

        return listitemView;
    }

    /**
     * Used to setup x gridCards
     */
    private void setupGridCard(View listitemView){
        TextView cardName = listitemView.findViewById(R.id.gridCardText);
        ImageView cardImage = listitemView.findViewById(R.id.gridCardImg);
        cardName.setText(gridCard.get_name());
        cardImage.setImageResource(gridCard.getImgid());

    }

}