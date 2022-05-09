package com.alphaville.coffeeapplication.Model;

import android.text.format.DateFormat;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;

/**
 * The Review class is a data class representing a review of a CoffeeProduct
 */
@Entity(tableName = "reviews"
        /*foreignKeys = {@ForeignKey(
        entity = CoffeeProduct.class,
        parentColumns = "id",
        childColumns = "coffeeProduct"
        //,onDelete = ForeignKey.CASCADE //Do we need?
        )}*/)
public class Review {


    @PrimaryKey(autoGenerate = true)
    private int reviewId;

    @Embedded
    private final CoffeeProduct coffeeProduct;
    /**
     * Free form text review of coffeeProduct
     */
    private final String textReview;
    /**
     * The rating of the review
     */
    private final double rating;
    /**
     * The location where the coffee was drank
     */
    private final String location;
    /**
     * The type of drink the coffee was consumed as e.g. Latte, Cappuccino etc.
     */
    private final String drinkCategory;
    /**
     * The time that the review was created
     */
    private final long creationTime;

    //TODO possibly add taste clock attributes


    public Review(CoffeeProduct coffeeProduct, String textReview, double rating, String location, String drinkCategory, long creationTime) {
        this.coffeeProduct = coffeeProduct;
        this.textReview = textReview;
        this.rating = rating;
        this.location = location;
        this.drinkCategory = drinkCategory;
        this.creationTime = creationTime;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public CoffeeProduct getCoffeeProduct() {
        return coffeeProduct;
    }

    public String getTextReview() {
        return textReview;
    }

    public double getRating() {
        return rating;
    }

    public String getLocation() {
        return location;
    }

    public String getDrinkCategory() {
        return drinkCategory;
    }

    public long getCreationTime(){
        return creationTime;
    }
    public String getCreationTimeAsString() {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(getCreationTime());
        return DateFormat.format("yyyy-MM-dd", cal).toString();
        //return creationTime;
    }


}