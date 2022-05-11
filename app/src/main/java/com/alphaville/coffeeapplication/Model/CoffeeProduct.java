package com.alphaville.coffeeapplication.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

/**
 * An object of the CoffeeProduct type represents a type of coffee bean and describes all its properties, along with whether the user likes it or not.
 *
 * Completely immutable to prevent logical errors.
 */
@Entity
public class CoffeeProduct
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    /** Possible ways for a coffee product to be processed. */
    public enum Process { dry, wet, fermented, honey }
    /** Possible roasts for each coffee product. */
    public enum Roast { light, dark }

    /** A list of all taste profiles that this product matches. */
    @TypeConverters(TasteJsonConverter.class) // converter for conversion to json
    private List<Taste> tastes;

    /** The name of the coffee product */
    private String name;
    /** The name of the country from which the product originates. */
    private String country;

    /** Elevation above sea level of the product's roastery. */
    private int elevation;
    /** The method by which the coffee bean is roasted. */
    private Roast roast;
    /** The way this product has been processed. */
    private Process process;

    /** Short description of the product. */
    private String description;

    /** Whether the user likes the coffee product. */
    private boolean isLiked;

    public CoffeeProduct(String name, String country, int elevation, Roast roast, Process process, List<Taste> tastes, String description, boolean isLiked){
        this.name = name;
        this.roast = roast;
        this. country = country;

        this.elevation = elevation;
        this.process = process;
        this.tastes = new ArrayList<>(tastes);

        this.description = description;
        this.isLiked = isLiked;
    }
    // Test constructor
    public CoffeeProduct() {
        this.name = "namn";
        this.roast = Roast.light;
        this.country = "Sweden";

        this.elevation = 123;
        this.process = Process.dry;
        this.tastes = new ArrayList<>();

        this.description = "test";
        this.isLiked = true;
    }
    // Getters ------------

    public String getName() { return name; }

    public String getCountry() { return country; }

    public int getElevation() { return elevation; }

    public Roast getRoast() { return roast; }

    public Process getProcess() { return process; }

    public List<Taste> getTastes() { return tastes; }

    public String getDescription() { return description; }

    public boolean isLiked() { return isLiked; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public void setRoast(Roast roast) {
        this.roast = roast;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public void setTastes(List<Taste> tastes) {
        this.tastes = tastes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
