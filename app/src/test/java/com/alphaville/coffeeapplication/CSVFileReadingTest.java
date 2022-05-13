package com.alphaville.coffeeapplication;

import android.content.Context;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;

import com.alphaville.coffeeapplication.Model.CoffeeProductReader;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

public class CSVFileReadingTest
{
    Context context;

    @Before
    public void before()
    {
        System.out.println("runned");
        context = ApplicationProvider.getApplicationContext(); // doesn't work
    }

    @Test
    void test1(){
        try {
            System.out.println(context);
            CoffeeProductReader reader = new CoffeeProductReader(context);

            List<String[]> rows = reader.getRows();

            System.out.println(getRowString(rows.get(1)));


            reader.close();
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }

    String getRowString(String[] row)
    {
        StringBuilder sb = new StringBuilder();

        for(String s : row)
            sb.append(s);

        return sb.toString();
    }

}
