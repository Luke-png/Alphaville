package com.alphaville.coffeeapplication.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.alphaville.coffeeapplication.Model.enums.Process;
import com.alphaville.coffeeapplication.Model.enums.Roast;
import com.alphaville.coffeeapplication.Model.enums.Taste;
import com.opencsv.bean.ComplexFieldMapEntry;

import java.util.ArrayList;

/**
 * An object of the CoffeeProduct type represents a type of coffee bean and describes all its properties, along with whether the user likes it or not.
 *
 * Completely immutable to prevent logical errors.
 */
@Entity(tableName = "products")
public class CoffeeProduct
{
    /**
     * Primary key for the products table.
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    /** The owner of the coffee product */
    private final String name;
    /** The name of the country from which the product originates. */
    private final String country;

    /** Elevation above sea level of where the product was made. */
    private final int elevation;

    /** The way this product has been processed. */
    private final String process;

    /**
     * Taste attributes, [0.0, 10.0]
     */
    private final float acidity, body, sweetness;

    /** General description of taste. */
    private final String taste;

    /** Whether the user likes the coffee product. */
    private final boolean isLiked;

    public CoffeeProduct(String name, String country, int elevation, String process, float acidity,
                         float body, float sweetness, String taste, boolean isLiked){
        this.name = name;
        this.country = country;
        this.elevation = elevation;
        this.process = process;
        this.acidity = acidity;
        this.body = body;
        this.sweetness = sweetness;
        this.taste = taste;

        this.isLiked = isLiked;
    }

    /**
     * Constructor used when changing the like-status for a product.
     * @param object the original coffeeproduct-object that will be replaced.
     * @param isLiked the new boolean value for the product.
     */
    public CoffeeProduct(CoffeeProduct object, Boolean isLiked){
        this.name = object.getName();
        this.country = object.getCountry();
        this.elevation = object.getElevation();
        this.process = object.getProcess();
        this.acidity = object.getAcidity();
        this.body = object.getBody();
        this.sweetness = object.getSweetness();
        this.taste = object.getTaste();

        this.isLiked = isLiked;
    }
    // Getters ------------

    public int getId() { return id; }

    public String getName() { return name; }

    public String getCountry() { return country; }

    public int getElevation() { return elevation; }

    public String getProcess() { return process; }

    public boolean isLiked() { return isLiked; }

    public float getAcidity() {
        return acidity;
    }

    public float getBody() {
        return body;
    }

    public float getSweetness() {
        return sweetness;
    }

    // Setter -------------------

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString(){

        return name + " | " +
                country + " | " +
                elevation + " | " +
                process + " | " +
                acidity + " | " +
                body + " | " +
                sweetness + " | " +
                taste + " | " +
                isLiked;
    }

    public String getTaste() {
        return taste;
    }
}
