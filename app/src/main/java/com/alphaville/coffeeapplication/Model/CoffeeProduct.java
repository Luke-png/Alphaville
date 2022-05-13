package com.alphaville.coffeeapplication.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.alphaville.coffeeapplication.Model.enums.Process;
import com.alphaville.coffeeapplication.Model.enums.Roast;
import com.alphaville.coffeeapplication.Model.enums.Taste;

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

    /** The name of the coffee product */
    private final String owner;
    /** The name of the country from which the product originates. */
    private final String country;

    /** Elevation above sea level of where the product was made. */
    private final int elevation;
    //** The method by which the coffee bean is roasted. */
    //private final Roast roast;
    /** The way this product has been processed. */
    private final String process;

    ///** A list of all taste profiles that this product matches. */
    //@TypeConverters(TasteJsonConverter.class) // converter for conversion to/from json string which is stored in db
    //private final ArrayList<Taste> tastes;

    /**
     * Taste attributes, 0.0-10.0
     */
    private final float aroma, flavor, aftertaste, acidity, body, balance, uniformity, sweetness, moisture;

    /** Whether the user likes the coffee product. */
    private final boolean isLiked;

    public CoffeeProduct(String owner, String country, int elevation, String process, float aroma, float flavor, float aftertaste,
                         float acidity, float body, float balance, float uniformity, float sweetness, float moisture, boolean isLiked){
        this.owner = owner;
        this. country = country;
        this.elevation = elevation;
        this.process = process;
        this.aroma = aroma;
        this.flavor = flavor;
        this.aftertaste = aftertaste;
        this.acidity = acidity;
        this.body = body;
        this.balance = balance;
        this.uniformity = uniformity;
        this.sweetness = sweetness;
        this.moisture = moisture;

        this.isLiked = isLiked;
    }

    // Getters ------------

    public int getId() { return id; }

    public String getOwner() { return owner; }

    public String getCountry() { return country; }

    public int getElevation() { return elevation; }
    public String getProcess() { return process; }

    public boolean isLiked() { return isLiked; }

    public float getAroma() {
        return aroma;
    }

    public float getFlavor() {
        return flavor;
    }

    public float getAftertaste() {
        return aftertaste;
    }

    public float getAcidity() {
        return acidity;
    }

    public float getBody() {
        return body;
    }

    public float getBalance() {
        return balance;
    }

    public float getUniformity() {
        return uniformity;
    }

    public float getSweetness() {
        return sweetness;
    }

    public float getMoisture() {
        return moisture;
    }

    // Setter -------------------

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("owner: " + owner + " | ");
        sb.append("country: " + country + " | ");
        sb.append("elevation: " + elevation + " | ");
        sb.append("process: " + process + " | ");
        sb.append("aroma: " + aroma + " | ");
        sb.append("flavor: " + flavor + " | ");
        sb.append("aftertaste: " + aftertaste + " | ");
        sb.append("acidity: " + acidity + " | ");
        sb.append("body: " + body + " | ");
        sb.append("balance: " + balance + " | ");
        sb.append("uniformity: " + uniformity + " | ");
        sb.append("sweetness: " + sweetness + " | ");
        sb.append("moisture: " + moisture + " | ");
        sb.append("liked: " + isLiked);

        return sb.toString();
    }
}
