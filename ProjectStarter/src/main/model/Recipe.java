package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a recipe having a name, a list of ingredients, a estimated cooking
// time (in minutes), a cuisine type, and an estimated cost (in dollars)
public class Recipe implements Writable {
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
        return recipeName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public double getCost() {
        return cost;
    }

    // REQUIRES: ingredient is not null
    // MODIFIES: this
    // EFFECTS: adds given ingrident to list of ingredients 
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    // REQUIRES: ingredientName is not null
    // EFFECTS: returns true if this recipe contains the ingredient with given name
    public boolean hasIngredient(String ingredientName) {
        for (Ingredient i : ingredients) {
            if (i.getIngredientName().equals(ingredientName)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: maxTime > 0
    // EFFECTS: returns true if cooking time <= maxTime
    public boolean isWithinCookingTime(int maxTime) {
        return cookingTime <= maxTime;
    }

    // REQUIRES: maxCost >= 0.0
    // EFFECTS: returns true if cost <= maxCost
    public boolean isWithinCost(double maxCost) {
        return cost <= maxCost;
    }

    // EFFECTS: returns this recipe as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", recipeName);
        json.put("time", cookingTime);
        json.put("cuisine", cuisineType);
        json.put("cost", cost);
        json.put("ingredients", ingredientsToJson());
        return json;
    }

    // EFFECTS: returns ingredients in this recipe as a JSON array
    public JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredient i : ingredients) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }
}
