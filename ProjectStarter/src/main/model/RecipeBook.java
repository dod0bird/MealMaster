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
        recipes.add(recipe);
    }

    // EFFECTS: returns list of recipes
    public List<Recipe> getRecipes() {
        return recipes;
    }

    // REQUIRES: recipeName is not null
    // EFFECTS: returns a recipe with given name, otherwise null if not found
    public Recipe getRecipeByName(String recipeName) {
        for (Recipe r : recipes) {
            if (r.getRecipeName().equals(recipeName)) {
                return r;
            }
        }
        return null;
    }

    // REQUIRES: ingredientName is not null
    // EFFECTS: returns list of recipes that contain given ingredient, otherwise
    //          return an empty list if none match
    public List<Recipe> searchByIngredient(String ingredientName) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.hasIngredient(ingredientName)) {
                results.add(r);
            }
        }
        return results;
    }

    // REQUIRES: maxTime >= 0
    // EFFECTS: returns a list of recipes whose cooking time is <= maxTime, otherwise
    //          return an empty list if none match
    public List<Recipe> searchByCookingTime(int maxTime) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.isWithinCookingTime(maxTime)) {
                results.add(r);
            }
        }
        return results;
    }

    // REQUIRES: cuisine is not null
    // EFFECTS: returns list of recipes that match given cuisine, otherwise
    //          return an empty list if none match
    public List<Recipe> searchByCuisine(String cuisine) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.getCuisineType().equals(cuisine)) {
                results.add(r);
            }
        }
        return results;
    }
    
    // REQUIRES: maxCost >= 0
    // EFFECTS: returns list of recipes whose cost <= maxTime, otherwise
    //          return an empty list if none match
    public List<Recipe> searchByCost(double maxCost) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.isWithinCost(maxCost)) {
                results.add(r);
            }
        }
        return results;
    }

    // REQUIRES: dailyTimeLimits is not null, preferredCuisine and preferredIngredient can be null,
    //           budget >= 0
    // EFFECTS: returns a list of recipes that create a weekly schedule that fit within each day's time limit,
    //          does not exceed total budget, and matches the preffered cuisine or ingredient if provided
    public List<Recipe> generateWeeklySchedule(int[] dailyTimeLimits, double budget, String preferredCuisine, String preferredIngredient) {
        List<Recipe> schedule = new ArrayList<>();
        double totalCost = 0;

        for (int dailyTimeLimit: dailyTimeLimits) {
            for (Recipe r: recipes) {
                if (r.isWithinCookingTime(dailyTimeLimit) && totalCost + r.getCost() <= budget) {
                    schedule.add(r);
                    totalCost += r.getCost();
                    break;
                }
            }
        }
        return schedule;
    }

    // REQUIRES: plannedRecipes is not null
    // EFFECTS: returns a list containing all ingredients required
    //          for the given planned recipes 
    public List<Ingredient> generateGroceryList(List<Recipe> plannedRecipes) {
       List<Ingredient> groceryList = new ArrayList<Ingredient>();

       for (Recipe r : plannedRecipes) {
        groceryList.addAll(r.getIngredients());
       }
       return groceryList;
    }
}
