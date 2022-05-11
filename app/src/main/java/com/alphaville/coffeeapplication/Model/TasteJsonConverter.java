package com.alphaville.coffeeapplication.Model;

import androidx.room.TypeConverter;

import com.alphaville.coffeeapplication.Model.enums.Taste;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

/**
 * Static class with methods for turning Taste ArrayList into json and vice versa.
 */
public class TasteJsonConverter
{
    /**
     * Converts ArrayList object into json data string.
     *
     * @param list the list to be converted into json
     * @return the resulting json string
     */
    @TypeConverter
    public static String fromArrayList(ArrayList<Taste> list){
        return new Gson().toJson(list);
    }

    /**
     * Converts json string into ArrayList object.
     *
     * @param value string containing json.
     * @return the list read from the string.
     */
    @TypeConverter
    public static ArrayList<Taste> fromString(String value){
        return new Gson().fromJson(value, new TypeToken<ArrayList<Taste>>(){}.getType());
    }
}
