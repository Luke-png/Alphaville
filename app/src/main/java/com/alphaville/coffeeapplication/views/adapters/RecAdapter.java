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

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.viewModels.RecTabViewModel;
import com.alphaville.coffeeapplication.views.GridCard;
import com.alphaville.coffeeapplication.R;

import org.w3c.dom.Text;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecAdapter extends ArrayAdapter<CoffeeProduct> {

    private int layout;
    private CoffeeProduct gridCard;
    private ArrayList<CoffeeProduct> coffeeProducts;
    private FragmentContainerView recDetail;
    private RecTabViewModel recTabViewModel;
    private View shadow;
    private boolean week = false;

    public RecAdapter(@NonNull Context context,@NonNull ArrayList<CoffeeProduct> coffeeProducts,int layout,
                      FragmentContainerView recDetail, View shadow, boolean week, RecTabViewModel recTabViewModel){
        super(context,0, coffeeProducts);
        this.recTabViewModel = recTabViewModel;
        this.layout = layout;
        this.recDetail = recDetail;
        this.shadow = shadow;
        this.week = week;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup viewGroup) {

        View listitemView = view;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(layout, viewGroup, false);
        }

        if (week){
            gridCard = getItem(position);
        }else{
            gridCard = getItem(0);
        }
        setupGridCard(listitemView);
        TextView cardName = listitemView.findViewById(R.id.gridCardText);


        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recTabViewModel.selectItem(gridCard);
                recDetail.setVisibility(View.VISIBLE);
                shadow.setVisibility(View.VISIBLE);
            }
        });

        return listitemView;
    }

    public void setProducts(List<CoffeeProduct> products) {
        this.coffeeProducts = new ArrayList<>(products);
        notifyDataSetChanged();
    }

    private void setTasteClockImages (float sweetness, float acidity, float body, ImageView a, ImageView b, ImageView c){
        setTasteClockImageSpecific(sweetness, a, 0, 100);
        setTasteClockImageSpecific(acidity, b, 0, 10);
        setTasteClockImageSpecific(body, c, 0, 10);

    }
    public static void setTasteClockImageSpecific (float value, ImageView image, int min, int max){

        if(image == null){return;}

        double delta = (max-min)/10;

        if(value<delta){image.setImageResource(R.drawable.taste_clock0);}

        if(value>=delta && value <= 3*delta){image.setImageResource(R.drawable.taste_clock20);}

        if(value > 3*delta && value <= 5*delta){image.setImageResource(R.drawable.taste_clock40);}

        if(value > 5*delta && value <= 7*delta){image.setImageResource(R.drawable.taste_clock60);}

        if(value > 7*delta && value <= 9*delta){image.setImageResource(R.drawable.taste_clock80);}

        if(value > 9*delta){image.setImageResource(R.drawable.taste_clock100);}

    }

    /**
     * Used to setup x gridCards
     */
    private void setupGridCard(View listitemView){
        TextView cardName = listitemView.findViewById(R.id.gridCardText);
        TextView match = listitemView.findViewById(R.id.match);
        TextView country = listitemView.findViewById(R.id.country);
        ImageView cardImage = listitemView.findViewById(R.id.gridCardImg);
        ImageView clock1 = listitemView.findViewById(R.id.imageView);
        ImageView clock2 = listitemView.findViewById(R.id.imageView2);
        ImageView clock3 = listitemView.findViewById(R.id.imageView3);

        setTasteClockImages(gridCard.getSweetness(),gridCard.getAcidity(), gridCard.getBody(), clock1, clock2, clock3);

        cardName.setText(gridCard.getName());
        country.setText(gridCard.getCountry());
        //match.setText(gridCard.get());
        //cardImage.setImageResource(gridCard.getImg());

    }

}