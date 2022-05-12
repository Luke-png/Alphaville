package com.alphaville.coffeeapplication.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.alphaville.coffeeapplication.Model.Database.TasteJsonConverter;
import com.alphaville.coffeeapplication.Model.enums.Process;
import com.alphaville.coffeeapplication.Model.enums.Roast;
import com.alphaville.coffeeapplication.Model.enums.Taste;

import java.util.ArrayList;
import java.util.List;

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

    /** The name of the coffee product */
    private final String name;
    /** The name of the country from which the product originates. */
    private final String country;

    /** Elevation above sea level of the product's roastery. */
    private final int elevation;
    /** The method by which the coffee bean is roasted. */
    private final Roast roast;
    /** The way this product has been processed. */
    private final Process process;

    /** A list of all taste profiles that this product matches. */
    @TypeConverters(TasteJsonConverter.class) // converter for conversion to/from json string which is stored in db
    private final ArrayList<Taste> tastes;

    /** Sweetness rating. */
    private final int sweetness;
    /** Fullness rating. */
    private final int fullness;
    /** Bitterness rating. */
    private final int bitterness;

    /** Short description of the product. */
    private final String description;

    /** Whether the user likes the coffee product. */
    private final boolean isLiked;

   /* public CoffeeProduct(String name, String country, int elevation, Roast roast, Process process,
                         List<Taste> tastes, int sweetness, int fullness,
                         int bitterness, String description, boolean isLiked){

        this.name = name;
        this.roast = roast;
        this. country = country;

        this.elevation = elevation;
        this.process = process;
        this.tastes = new ArrayList<>(tastes);

        this.sweetness = sweetness;
        this.fullness = fullness;
        this.bitterness = bitterness;

        this.description = description;
        this.isLiked = isLiked;
    }*/

    public CoffeeProduct(String name, String country, int elevation, Roast roast, Process process, ArrayList<Taste> tastes, int sweetness, int fullness, int bitterness, String description, boolean isLiked) {
        this.name = name;
        this.country = country;
        this.elevation = elevation;
        this.roast = roast;
        this.process = process;
        this.tastes = tastes;
        this.sweetness = sweetness;
        this.fullness = fullness;
        this.bitterness = bitterness;
        this.description = description;
        this.isLiked = isLiked;
    }
    // Getters ------------

    public String getName() { return name; }

    public String getCountry() { return country; }

    public int getElevation() { return elevation; }

    public Roast getRoast() { return roast; }

    public Process getProcess() { return process; }

    public ArrayList<Taste> getTastes() { return tastes; }

    public String getDescription() { return description; }

    public boolean isLiked() { return isLiked; }


    public int getSweetness() {
        return sweetness;
    }

    public int getFullness() {
        return fullness;
    }

    public int getId() { return id; }

    // Setter -------------------

    public void setId(int id) {
        this.id = id;
    }


    public int getBitterness() {
        return bitterness;
    }
}
