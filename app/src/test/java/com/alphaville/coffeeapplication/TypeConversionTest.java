package com.alphaville.coffeeapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alphaville.coffeeapplication.Model.enums.Taste;
import com.alphaville.coffeeapplication.Model.TasteJsonConverter;;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class TypeConversionTest {

    @Test
    void testToJsonConversion(){
        ArrayList<Taste> tastes = new ArrayList<>();

        tastes.add(Taste.floral);
        tastes.add(Taste.fruity);
        tastes.add(Taste.green_vegetative);

        // what the json string should look like
        String expected = "[\"floral\",\"fruity\",\"green_vegetative\"]";
        // what it actually looks like
        String json = TasteJsonConverter.fromArrayList(tastes);

        assertEquals(expected, json);
    }
}
