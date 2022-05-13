package com.alphaville.coffeeapplication.Model;

import android.content.Context;
import android.util.Log;

import com.alphaville.coffeeapplication.R;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for reading CoffeeProduct objects off a csv file.
 */
public class CoffeeProductReader
{
    /**
     * Integers representing the columns (attributes) of interest in the csv file.
     */
    private final List<Integer> accepted;

    /** Reader object for reading csv file. */
    private final CSVReader reader;

    /**
     * @param context application context
     */
    public CoffeeProductReader(Context context) throws Exception
    {
        int[] accepted_columns = { 2, 3, 9, 10, 15, 19, 20, 21, 22, 23, 24, 25, 26, 28, 31};
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

        boolean columnheaders = true;

        for(String[] row : rows){
            if(columnheaders){ // to skip row with only column headers
                columnheaders = false;
                continue;
            }
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
        String owner = row[0];
        String country = row[1];
        int elevation;
        try {
            elevation = getNumber(filter('.', row[2])); // parses first number stored
        } catch(Exception e)
        {
            elevation = 0;
        }
        String process = row[5];

        float aroma =       parseFloat(row[6]);
        float flavor =      parseFloat(row[7]);
        float aftertaste =  parseFloat(row[8]);
        float acidity =     parseFloat(row[9]);
        float body =        parseFloat(row[10]);
        float balance =     parseFloat(row[11]);
        float uniformity =  parseFloat(row[12]);
        float sweetness =   parseFloat(row[13]);
        float moisture =    parseFloat(row[14]);

        // todo add maybe?
        //String region = row[3];
        //int harvest_year = getNumber(filter('.', row[4]));

        return new CoffeeProduct(owner, country, elevation, process, aroma, flavor, aftertaste, acidity,
                                 body, balance, uniformity, sweetness, moisture, false);
    }

    /**
     * Parses float, parse exception returns 0
     * @param str to parse
     * @return parsed float
     */
    private float parseFloat(String str){
        try{
            return Float.parseFloat(str);
        } catch (Exception e){
            return 0;
        }
    }

    /**
     * Gets the first integer found in string.
     * @param s string to parse integer from
     * @return parsed integer
     */
    private static int getNumber(String s) {

        String[] n = s.split(""); //array of strings
        StringBuffer f = new StringBuffer(); // buffer to store numbers

        for (int i = 0; i < n.length; i++) {
            if((n[i].matches("[0-9]+"))) {// validating numbers
                f.append(n[i]); //appending
            }else {
                //parsing to int and returning value
                return Integer.parseInt(f.toString());
            }
        }
        return 0;
    }

    /**
     * Filters all instances of given character from string.
     * @param f filter char
     * @param str string to filter
     * @return filtered string
     */
    private static String filter(char f, String str)
    {
        StringBuilder sb = new StringBuilder();

        for(char c : str.toCharArray()){
            if(c != f)
                sb.append(c);
        }

        return sb.toString();
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
