package model;

import java.util.*;

// Represents a user's collection of recipes
public class RecipeBook {
    private List<Recipe> recipes; // list of recipes

    // EFFECTS: create a new empty recipe collection
    public RecipeBook() {
        recipes = new ArrayList<Recipe>();
    }

    // REQUIRES: recipe is not null
    // MODIFIES: this
    // EFFECTS: adds recipe to collection
    public void addRecipe(Recipe recipe) {
        // stub
    }

    // EFFECTS: returns list of recipes
    public List<Recipe> getRecipes() {
        return null;
    }

    // REQUIRES: recipeName is not null
    // EFFECTS: returns a recipe with given name, otherwise null if not found
    public Recipe getRecipeByName(String recipeName) {
        return null;
    }

    // REQUIRES: ingredientName is not null
    // EFFECTS: returns list of recipes that contain given ingredient, otherwise
    //          return an empty list if none match
    public List<Recipe> searchByIngredient(String ingredientName) {
        return null;
    }

    // REQUIRES: maxTime >= 0
    // EFFECTS: returns a list of recipes whose cooking time is <= maxTime, otherwise
    //          return an empty list if none match
    public List<Recipe> searchByCookingTime(int maxTime) {
        return null;
    }

    // REQUIRES: cuisine is not null
    // EFFECTS: returns list of recipes that match given cuisine, otherwise
    //          return an empty list if none match
    public List<Recipe> searchByCuisine(String cuisine) {
        return null;
    }
    
    // REQUIRES: maxCost >= 0
    // EFFECTS: returns list of recipes whose cost <= maxTime, otherwise
    //          return an empty list if none match
    public List<Recipe> searchByCost(double maxCost) {
        return null;
    }

    // REQUIRES: dailyTimeLimits is not null, preferredCuisine and preferredIngredient can be null,
    //           budget >= 0
    // EFFECTS: returns a list of recipes that create a weekly schedule that fit within each day's time limit,
    //          does not exceed total budget, and matches the preffered cuisine or ingredient if provided
    public List<Recipe> generateWeeklySchedule(int[] dailyTimeLimits, double budget, String preferredCuisine, String preferredIngredient) {
        return null;
    }

    // REQUIRES: plannedRecipes is not null
    // EFFECTS: returns a list containing all ingredients required
    //          for the given planned recipes 
    public List<Recipe> generateGroceryList(List<Recipe> plannedRecipes) {
        return null;
    }
}
