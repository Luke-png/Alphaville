package com.alphaville.coffeeapplication.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alphaville.coffeeapplication.GridCard;
import com.alphaville.coffeeapplication.R;
import com.google.android.gms.ads.mediation.Adapter;

import java.util.ArrayList;

public class RecAdapter extends BaseAdapter {

    private ArrayList<GridCard> gridCardHolder;
    private Context context;
    private LayoutInflater inflter;

    public RecAdapter(Context context, ArrayList<GridCard> gridCardHolder){
        this.context = context;
        this.gridCardHolder = gridCardHolder;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflter == null){
            inflter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null){

            view = inflter.inflate(R.layout.rec_card,null);

        }

        view = inflter.inflate(R.layout.fragment_recommendations, null); // inflate the layout
        ImageView imageView = view.findViewById(R.id.gridCardImg);
        TextView textField = view.findViewById(R.id.gridCardText);

        //imageView.setImageResource(gridCardHolder);
        textField.setText(gridCardHolder.get(i).getCourse_name());


        return view;
    }

}