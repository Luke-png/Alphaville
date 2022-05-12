package com.alphaville.coffeeapplication;


//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alphaville.coffeeapplication.Model.CoffeeMatcher;
import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Review;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void matchScore1(){
        CoffeeProduct p1 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 1,2,3,"",false);
        Review r1 = new Review(p1, "",10, "","",null);
        List<Review> l = new ArrayList<>();
        l.add(r1);
        assertEquals(1,CoffeeMatcher.getMatchPercentage(p1,l));
    }

    @Test
    public void matchScore2(){
        CoffeeProduct p1 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 1,2,3,"",false);
        CoffeeProduct p2 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 1,2,3,"",false);
        Review r1 = new Review(p1, "",10, "","",null);
        List<Review> l = new ArrayList<>();
        l.add(r1);
        assertEquals(1,CoffeeMatcher.getMatchPercentage(p2,l));
    }

    @Test
    public void matchScore3(){
        CoffeeProduct p1 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 1,2,3,"",false);
        CoffeeProduct p2 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 1,2,3,"",false);
        Review r1 = new Review(p1, "",10, "","",null);
        Review r2 = new Review(p2, "",5, "","",null);
        List<Review> l = new ArrayList<>();
        l.add(r1);
        l.add(r2);
        assertEquals(0.75,CoffeeMatcher.getMatchPercentage(p2,l));
    }

    @Test
    public void sortMatch(){
        CoffeeProduct p1 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 1,2,3,"",false);
        CoffeeProduct p2 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 3,1,5,"",false);
        Review r1 = new Review(p1, "",10, "","",null);
        Review r2 = new Review(p2, "",5, "","",null);
        List<Review> l = new ArrayList<>();
        List<CoffeeProduct> p  = new ArrayList<>();
        p.add(p1);
        p.add(p2);
        l.add(r1);
        l.add(r2);
        assertEquals(0.5,CoffeeMatcher.sortByMatch(p,l).get(1).getMatchScore());
    }

    @Test
    public void sortMatch2(){
        CoffeeProduct p1 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 2,2,3,"",false);
        CoffeeProduct p2 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 3,2,5,"",false);
        CoffeeProduct p3 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 5,3,4,"",false);
        CoffeeProduct p4 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 5,2,5,"",false);
        CoffeeProduct p5 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 4,4,4,"",false);
        CoffeeProduct p6 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 5,2,3,"",false);
        CoffeeProduct p7 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 2,2,5,"",false);
        CoffeeProduct p8 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 5,2,4,"",false);
        CoffeeProduct p9 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 3,2,5,"",false);
        CoffeeProduct p10 = new CoffeeProduct("","",0,null,null, new ArrayList<CoffeeProduct.Taste>() {}, 1,1,1,"",false);
        Review r1 = new Review(p1, "",4, "","",null);
        Review r2 = new Review(p2, "",3, "","",null);
        Review r3 = new Review(p3, "",5, "","",null);
        Review r4 = new Review(p4, "",3, "","",null);
        Review r5 = new Review(p10, "",0, "","",null);
        List<Review> l = new ArrayList<>();
        l.add(r1);
        l.add(r2);
        l.add(r3);
        l.add(r4);
        l.add(r5);
        List<CoffeeProduct> p  = new ArrayList<>();
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        p.add(p6);
        p.add(p7);
        p.add(p8);
        p.add(p9);
        p.add(p10);
        assertEquals(0,CoffeeMatcher.sortByMatch(p,l).get(9).getMatchScore());
    }

 }