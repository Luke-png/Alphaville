package com.alphaville.coffeeapplication.Model;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for reading information off a csv file.
 */
public class CoffeeProductReader
{

    /**
     * Extracts the specified columns for every row in the CSV file specified with a path string.
     * @param filePath location of csv file
     * @param accepted_columns positions of accepted columns
     * @return a list of string arrays, where each array represents a row
     * @throws Exception reading exception when reading file
     */
    public static List<String[]> getRows(String filePath, int[] accepted_columns) throws Exception
    {
        // list of integers representing the columns to be included in the read
        List<Integer> accepted = populate_accepted_list(accepted_columns);

        // object for reading csv file
        CSVReader reader = new CSVReader(new FileReader(filePath));

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
    private static List<Integer> populate_accepted_list(int[] a){
        List<Integer> result = new ArrayList<>();
        for(int i : a)
            result.add(i);

        return result;
    }

}
