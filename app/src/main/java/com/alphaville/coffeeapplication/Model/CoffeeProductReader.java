package com.alphaville.coffeeapplication.Model;

import android.content.Context;

import com.alphaville.coffeeapplication.R;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for reading CoffeeProduct objects off a csv file.
 */
public class CoffeeProductReader
{


    private static final int[] accepted_columns = { 0, 1, 2, 3, 4, 9, 10, 11, 16, 19, 20, 21, 22, 23, 24, 25, 26 ,27, 29, 32};

    private final List<Integer> accepted; // corresponding list

    /** Reader object for reading csv file. */
    private final CSVReader reader;

    /**
     * @param context application context
     */
    public CoffeeProductReader(Context context) throws Exception
    {
        accepted = populate_accepted_list(accepted_columns);

        // reads file from resources
        InputStream inputStream = context.getResources().openRawResource(R.raw.product_data);
        File file = File.createTempFile("pre", "suf");
        copyFile(inputStream, new FileOutputStream(file));

        // reads information from file
        reader = new CSVReader(new FileReader(file));
    }

    /**
     * Closes CSV reader
     * @throws Exception exception during closing operation on csv reader
     */
    public void close() throws Exception
    {
        reader.close();
    }

    /**
     * Gets all CoffeeProduct rows from csv file and then converts rows into coffee product objects.
     * @return list of coffeeproducts from csv file
     * @throws Exception file reading errors
     */
    public List<CoffeeProduct> getCoffeeProducts() throws Exception
    {
        List<String[]> rows = getRows(); // gets rows
        List<CoffeeProduct> products = new ArrayList<>(); // result

        for(String[] row : rows){
            products.add(getCoffeeProduct(row)); // converts each row to object
        }

        return products;
    }

    /**
     * Converts row in the form of a String array into a CoffeeProduct object
     * @param row array
     * @return interpreted CoffeeProduct object
     */
    public CoffeeProduct getCoffeeProduct(String[] row)
    {
        int id = Integer.parseInt(row[0]);
        // species?
        // owner?
        String country = row[3];
        // company?
        int elevation = Integer.parseInt(row[5]);
        // region?
        // harvest year?
        // variety?
        String process = row[9];
        // aroma, flavor, aftertaste, acidity, body, balance, uniformity, sweetness, moisture?

        CoffeeProduct product = new CoffeeProduct("", country, elevation, null,
                        null, new ArrayList<>(), "", false);

        return product;
    }


    /**
     * Extracts the specified columns for every row in the CSV file specified with a path string.
     * @return a list of string arrays, where each array represents a row
     * @throws Exception reading exception when reading file
     */
    public List<String[]> getRows() throws Exception
    {
        // to be returned
        List<String[]> rows = new ArrayList<>();

        // loops through every row in file
        String[] nextLine;
        while((nextLine = reader.readNext()) != null)
        {
            String[] row = new String[accepted.size()]; // number of items in row should be number of accepted columns
            for(int i = 0, col = 0; i < nextLine.length; i++)
            {
                if(!accepted.contains(i)) // if current columns isn't accepted, move on to next
                    continue;
                row[col] = nextLine[i];
                col++; // only increment col if a column has been added to row
            }

            rows.add(row);
        }

        return rows;
    }

    /**
     * Gets corresponding list of integer array.
     * @param a array to be turned into list
     * @return list
     */
    private List<Integer> populate_accepted_list(int[] a){
        List<Integer> result = new ArrayList<>();
        for(int i : a)
            result.add(i);

        return result;
    }

    /**
     * Necessary method for reading csv file from resources.
     */
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

}
