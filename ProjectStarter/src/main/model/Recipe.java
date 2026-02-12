package model;

import java.util.*;

// Represents a recipe having a name, a list of ingredients, a estimated cooking
// time (in minutes), a cuisine type, and an estimated cost (in dollars)
public class Recipe {

    private String name; // recipe name
    private List<Ingredient>ingredients; // the required ingredients
    private int cookingTime; // the estimated cooking time
    private String cuisineType; // the cuisine type
    private double cost; // the estimated cost

    public Recipe() {
        other = 4;
    }
}