package com.alphaville.coffeeapplication;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.enums.Process;
import com.alphaville.coffeeapplication.Model.enums.Roast;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Junit tests of the CoffeeSearcher
 */

public class CoffeeSearcher {

                                                // name, country, elevation, roast, process, tastes
                                                CoffeeProduct kaffe2 = new CoffeeProduct("namn", "Sweden", 123, Roast.light, Process.dry,
                                                        new ArrayList<>(), 2,2,2, "test", true);
    CoffeeProduct kaff1 = new CoffeeProduct("namn2", "Sweden", 123, Roast.light, Process.dry,
            new ArrayList<>(),2, 2, 2, "test", true);/

    //List<CoffeeProduct.Taste> tastes1 = Arrays.asList(CoffeeProduct.Taste.floral, CoffeeProduct.Taste.fruity);
    // hej   danmark  345        dark   dry      floral,fruity
    CoffeeProduct kaffe3 = new CoffeeProduct("hej", "danmark", 345, CoffeeProduct.Roast.dark, CoffeeProduct.Process.dry, Arrays.asList(CoffeeProduct.Taste.floral, CoffeeProduct.Taste.fruity), "desc", true);
    // dansk   danmark  500        light   fermented      roasted,fruity
    CoffeeProduct kaffe4 = new CoffeeProduct("dansk", "danmark", 500, CoffeeProduct.Roast.light, CoffeeProduct.Process.fermented, Arrays.asList(CoffeeProduct.Taste.fruity, CoffeeProduct.Taste.sour_fermented), "desc", true);
    // flygare   norge  800        light   dry      fruity
    CoffeeProduct kaffe5 = new CoffeeProduct("flygare", "norge", 800, CoffeeProduct.Roast.light, CoffeeProduct.Process.dry, Arrays.asList(CoffeeProduct.Taste.fruity), "desc", true);
    // "       norge  200        light   dry      fruity
    CoffeeProduct kaffe6 = new CoffeeProduct("ha", "norge", 200, CoffeeProduct.Roast.light, CoffeeProduct.Process.dry, Arrays.asList(CoffeeProduct.Taste.fruity), "desc", true);
    CoffeeProduct kaffe7 = new CoffeeProduct("ha1", "norge", 200, CoffeeProduct.Roast.light, CoffeeProduct.Process.dry, Arrays.asList(CoffeeProduct.Taste.floral, CoffeeProduct.Taste.fruity, CoffeeProduct.Taste.roasted), "desc", true);
    CoffeeProduct kaffe8 = new CoffeeProduct("ha2", "norge", 200, CoffeeProduct.Roast.light, CoffeeProduct.Process.dry, Arrays.asList(CoffeeProduct.Taste.floral, CoffeeProduct.Taste.roasted), "desc", true);

    List<CoffeeProduct> products = Arrays.asList(kaffe1, kaffe2, kaffe3, kaffe4, kaffe5, kaffe6, kaffe7, kaffe8);

    @Test
    void TestSearchProductsString() {

        List<CoffeeProduct> filteredList = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "h"
        );
        List<CoffeeProduct> checker = Arrays.asList(kaffe3, kaffe6, kaffe7, kaffe8);
        List<CoffeeProduct> checker2 = Arrays.asList(kaffe2, kaffe6, kaffe7, kaffe8);
        assertTrue(filteredList.containsAll(checker) && checker.containsAll(filteredList));
        assertFalse(filteredList.containsAll(checker2) && checker2.containsAll(filteredList));

        List<CoffeeProduct> filteredList2 = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, ""
        );
        List<CoffeeProduct> checker3 = Arrays.asList(kaffe1, kaffe2, kaffe3, kaffe4, kaffe5, kaffe6, kaffe7, kaffe8);
        List<CoffeeProduct> checker4 = Arrays.asList(kaffe2, kaffe6, kaffe7, kaffe8);
        assertTrue(filteredList.containsAll(checker) && checker.containsAll(filteredList));
        assertFalse(filteredList.containsAll(checker2) && checker2.containsAll(filteredList));
    }
    @Test // Arrays.asList(CoffeeProduct.Roast.dark), Arrays.asList(CoffeeProduct.Process.dry), Arrays.asList(CoffeeProduct.Taste.fruity)
    void TestSearchProductsFilterCountry() {
        List<CoffeeProduct> filteredList = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", Arrays.asList("Sweden", "danmark"), 0, 0, null, null, null);
        List<CoffeeProduct> checker = Arrays.asList(kaffe1, kaffe2, kaffe3, kaffe4);
        assertTrue(filteredList.containsAll(checker) && checker.containsAll(filteredList));

        List<CoffeeProduct> filteredList1 = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", Arrays.asList("danmark", ""), 0, 0, null, null, null);
        List<CoffeeProduct> checker1 = Arrays.asList(kaffe3, kaffe4);
        assertTrue(filteredList1.containsAll(checker1) && checker1.containsAll(filteredList1));

        List<CoffeeProduct> filteredList2 = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", Arrays.asList(""), 0, 0, null, null, null);
        List<CoffeeProduct> checker2 = Arrays.asList();
        assertTrue(filteredList2.containsAll(checker2) && checker2.containsAll(filteredList2));
    }
    @Test
    void TestSearchProductsFilterElevation() {
        List<CoffeeProduct> filteredList = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", null, 0, 0, null, null, null);
        List<CoffeeProduct> checker = Arrays.asList(kaffe1, kaffe2, kaffe3, kaffe4, kaffe5, kaffe6, kaffe7, kaffe8);
        assertTrue(filteredList.containsAll(checker) && checker.containsAll(filteredList));

        List<CoffeeProduct> filteredList1 = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", null, 0, 400, null, null, null);
        List<CoffeeProduct> checker1 = Arrays.asList(kaffe1, kaffe2, kaffe3, kaffe6, kaffe7, kaffe8);
        assertTrue(filteredList1.containsAll(checker1) && checker1.containsAll(filteredList1));

        List<CoffeeProduct> filteredList2 = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", null, 345, 800, null, null, null);
        List<CoffeeProduct> checker2 = Arrays.asList(kaffe3, kaffe4, kaffe5);
        assertTrue(filteredList2.containsAll(checker2) && checker2.containsAll(filteredList2));
    }
    @Test
    void TestSearchProductsFilterRoasts() {
        List<CoffeeProduct> filteredList = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", null, 0, 0, Arrays.asList(CoffeeProduct.Roast.light), null, null);
        List<CoffeeProduct> checker = Arrays.asList(kaffe1, kaffe2, kaffe4, kaffe5, kaffe6, kaffe7, kaffe8);
        assertTrue(filteredList.containsAll(checker) && checker.containsAll(filteredList));

        List<CoffeeProduct> filteredList1 = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", null, 0, 0, Arrays.asList(CoffeeProduct.Roast.light, CoffeeProduct.Roast.dark), null, null);
        List<CoffeeProduct> checker1 = Arrays.asList(kaffe1, kaffe2, kaffe3, kaffe4, kaffe5, kaffe6, kaffe7, kaffe8);
        assertTrue(filteredList1.containsAll(checker1) && checker1.containsAll(filteredList1));
    }
    @Test
    void TestSearchProductsFilterProcesses() {
        List<CoffeeProduct> filteredList = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", null, 0, 0, null, Arrays.asList(CoffeeProduct.Process.dry), null);
        List<CoffeeProduct> checker = Arrays.asList(kaffe1, kaffe2, kaffe3, kaffe5, kaffe6, kaffe7, kaffe8);
        assertTrue(filteredList.containsAll(checker) && checker.containsAll(filteredList));

        List<CoffeeProduct> filteredList1 = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", null, 0, 0, null, Arrays.asList(CoffeeProduct.Process.dry, CoffeeProduct.Process.fermented, CoffeeProduct.Process.honey), null);
        List<CoffeeProduct> checker1 = Arrays.asList(kaffe1, kaffe2, kaffe3, kaffe4, kaffe5, kaffe6, kaffe7, kaffe8);
        assertTrue(filteredList1.containsAll(checker1) && checker1.containsAll(filteredList1));
    }
    @Test
    void TestSearchProductsFilterTastes() {
        List<CoffeeProduct> filteredList = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", null, 0, 0, null, null, Arrays.asList(CoffeeProduct.Taste.fruity));
        List<CoffeeProduct> checker = Arrays.asList(kaffe3, kaffe4, kaffe5, kaffe6, kaffe7);
        assertTrue(filteredList.containsAll(checker) && checker.containsAll(filteredList));

        List<CoffeeProduct> filteredList1 = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", null, 0, 0, null, null, Arrays.asList(CoffeeProduct.Taste.sour_fermented, CoffeeProduct.Taste.roasted));
        List<CoffeeProduct> checker1 = Arrays.asList(kaffe4, kaffe7, kaffe8);
        assertTrue(filteredList1.containsAll(checker1) && checker1.containsAll(filteredList1));
    }
    @Test
    void TestSearchProductsFilterCombinations() {
        List<CoffeeProduct> filteredList = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "", Arrays.asList("norge", "danmark"), 300, 1000, null, null, Arrays.asList(CoffeeProduct.Taste.fruity));
        List<CoffeeProduct> checker = Arrays.asList(kaffe3, kaffe4, kaffe5);
        assertTrue(filteredList.containsAll(checker) && checker.containsAll(filteredList));

        List<CoffeeProduct> filteredList1 = com.alphaville.coffeeapplication.Model.CoffeeSearcher.searchProducts(
                products, "h", Arrays.asList("norge", "danmark"), 100, 300, Arrays.asList(CoffeeProduct.Roast.light), null, Arrays.asList(CoffeeProduct.Taste.fruity, CoffeeProduct.Taste.roasted));
        List<CoffeeProduct> checker1 = Arrays.asList(kaffe6, kaffe7, kaffe8);
        assertTrue(filteredList1.containsAll(checker1) && checker1.containsAll(filteredList1));
    }
}
