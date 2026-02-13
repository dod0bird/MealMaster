package model;

import java.util.*;

// Represents a recipe having a name, a list of ingredients, a estimated cooking
// time (in minutes), a cuisine type, and an estimated cost (in dollars)
public class Recipe {
    private String recipeName;             // recipe name
    private List<Ingredient> ingredients;  // the required ingredients
    private int cookingTime;               // the estimated cooking time
    private String cuisineType;            // the cuisine type
    private double cost;                   // the estimated cost

    /* 
     * REQUIRES: recipeName has non-zero length, ingredients list is never null, cookingTime >= 0
     *           cuisineType has non-zero length, cost >= 0
     * EFFECTS: create new Recipe object with given values and empty list of ingredients 
     */
    public Recipe(String name, int cookingTime, String cuisineType, double cost) {
        this.recipeName = name;
        this.ingredients = new ArrayList<Ingredient>();
        this.cookingTime = cookingTime;
        this.cuisineType = cuisineType;
        this.cost = cost;
    }

    public String getRecipeName() {
        return "";
    }

    public List<Ingredient> getIngredients() {
        return null;
    }

    public int getCookingTime() {
        return 0;
    }

    public String getCuisineType() {
        return "";
    }

    public double getCost() {
        return 0.0;
    }

    // REQUIRES: ingredient is not null
    // MODIFIES: this
    // EFFECTS: adds given ingrident to list of ingredients 
    public void addIngredient(Ingredient ingredient) {
        // stub
    }

    // REQUIRES: ingredientName is not null
    // EFFECTS: returns true if this recipe contains the ingredient with given name
    public boolean hasIngredient(String ingredientName) {
        return false;
    }

    // REQUIRES: maxTime > 0
    // EFFECTS: returns true if cooking time < maxTime
    public boolean isWithinCookingTime(int maxTime) {
        return false;
    }

    // REQUIRES: maxCost >= 0.0
    // EFFECTS: returns true if cost < maxCost
    public boolean isWithinCost(double maxCost) {
        return false;
    }
}
