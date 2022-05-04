package com.alphaville.coffeeapplication.views;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.R;
import com.alphaville.coffeeapplication.databinding.ReviewHistoryListItemBinding;

import java.util.List;

/**
 * class responsible for filling the RecyclerView inside the history-tab
 */
public class HistoryResultAdapter extends RecyclerView.Adapter<HistoryResultAdapter.ReviewCardViewHolder> {

    /**
     * List of user reviews to fill the history-tab
     */
    private List<Review> reviewList;

    /**
     * class-constructor
     * @param reviewList the list of reviews that fills the recyclerview
     */
    public HistoryResultAdapter(List<Review> reviewList){
        this.reviewList = reviewList;
    }

    /**
     * Tells ViewHoldern which xml-file to refer towards
     */
    @NonNull
    @Override
    public ReviewCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_history_list_item, parent, false);

        return new ReviewCardViewHolder(view);
    }
    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBindViewHolder(@NonNull ReviewCardViewHolder holder, int position) {
        holder.setReviewInfo(reviewList.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    /**
     * ViewHolder class for setting all the fields with the right information on the review cards.
     */
    public static class ReviewCardViewHolder extends RecyclerView.ViewHolder {

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

        public ReviewCardViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.review_cp_product_name);
            country = itemView.findViewById(R.id.review_cp_country);
            process = itemView.findViewById(R.id.review_cp_product_name);
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
            process.setText(item.getCoffeeProduct().getProcess().toString());
            reviewDate.setText((CharSequence) item.getCreationTime());
            drinktype.setText("Review for " + item.getDrinkCategory());
            //image.setImageIcon(item.getCoffeeProduct().getImage());
            rating.setRating((float) item.getRating());

        }
    }
}