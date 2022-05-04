package com.alphaville.coffeeapplication.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * An object of the CoffeeProduct type represents a type of coffee bean and describes all its properties, along with whether the user likes it or not.
 *
 * Completely immutable to prevent logical errors.
 */
public class CoffeeProduct
{
    /** Possible ways for a coffee product to be processed. */
    public enum Process { dry, wet, fermented, honey }
    /** Possible tastes for a coffee product. */
    public enum Taste { floral, fruity, sour_fermented, green_vegetative, roasted, spices, nutty_cocoa, sweet, other }
    /** Possible roasts for each coffee product. */
    public enum Roast { light, dark }

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
    private final List<Taste> tastes;

    /** Short description of the product. */
    private final String description;

    /** Whether the user likes the coffee product. */
    private final boolean isLiked;

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

    // Getters ------------

    public String getName() { return name; }

    public String getCountry() { return country; }

    public int getElevation() { return elevation; }

    public Roast getRoast() { return roast; }

    public Process getProcess() { return process; }

    public List<Taste> getTastes() { return tastes; }

    public String getDescription() { return description; }

    public boolean isLiked() { return isLiked; }


}
