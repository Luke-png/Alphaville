package com.alphaville.coffeeapplication.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaville.coffeeapplication.Model.Review;
import com.alphaville.coffeeapplication.R;

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

    @NonNull
    @Override
    //TODO: change layout.activity_main to layout.'right reference to xml'
    public ReviewCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);

        return new ReviewCardViewHolder(view);
    }

    @Override
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

        private final TextView textReview;
        private final RatingBar rating;

        //TODO: change R.id references to the correct ones (ie something R.id.textReview...)
        public ReviewCardViewHolder(@NonNull View itemView) {
            super(itemView);
            textReview = itemView.findViewById(R.id.clock1Text);
            rating = itemView.findViewById(R.id.clock2Text);

        }

        public void setReviewInfo(Review item){
            textReview.setText(item.getTextReview());
            rating.setRating((float) item.getRating());
        }
    }
}
