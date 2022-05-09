package com.alphaville.coffeeapplication.views.adapters;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alphaville.coffeeapplication.GridCard;
import com.alphaville.coffeeapplication.R;

import java.util.ArrayList;

public class RecAdapter extends ArrayAdapter<GridCard> {


    public RecAdapter(@NonNull Context context,@NonNull ArrayList<GridCard> gridCardHolder){
        super(context,0, gridCardHolder);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup viewGroup) {

        View listitemView = view;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.rec_card, viewGroup, false);
        }
        GridCard courseModel = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.gridCardText);
        ImageView courseIV = listitemView.findViewById(R.id.gridCardImg);
        courseTV.setText(courseModel.getCourse_name());
        courseIV.setImageResource(courseModel.getImgid());
        return listitemView;
    }

}