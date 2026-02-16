package model;

import java.util.*;

// Represents a user's collection of recipes
public class RecipeBook {
    private List<Recipe> recipes; // list of recipes

    // EFFECTS: create a new empty recipe collection
    public RecipeBook() {
        recipes = new ArrayList<Recipe>();
    }
}
