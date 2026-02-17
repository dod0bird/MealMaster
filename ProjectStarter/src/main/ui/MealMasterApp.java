package ui;

import model.*;
import java.util.*;

// this ui code was inspiried by the code in the TellerApp project provided.

public class MealMasterApp {
    private RecipeBook recipeBook;
    private Scanner input;

    // EFFECTS: runs the Meal Master application
    public MealMasterApp() {
        startNewApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input and runs the application
    private void startNewApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }
    

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddRecipeaAndIngredient();
        } else if (command.equals("v")) {
            doViewRecipes();
        } else if (command.equals("s")) {
            doSearchRecipes();
        } else if (command.equals("m")) {
            doGroceryList();
        } else {
            System.out.println("Selection not valid...");
        }

    }

    // MODIFIES: this
    // EFFECTS: initializes recipes and scanner
    private void init() {
        input = new Scanner(System.in);
        recipeBook = new RecipeBook();
        input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: displays the menu options to the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add recipe");
        System.out.println("\tv -> view recipes");
        System.out.println("\ts -> search recipes");
        System.out.println("\tm -> make grocery list");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a recipe to the collection
    private void doAddRecipeaAndIngredient() {
        System.out.println("Enter recipe name: ");
        String name = input.next();
        System.out.println("Enter cooking time (minutes): ");
        int time = input.nextInt();
        System.out.println("Enter cuisine type: ");
        String cuisine = input.next();
        System.out.println("Enter estimated cost ($): ");
        double cost = input.nextDouble();

        Recipe recipe = new Recipe(name, time, cuisine, cost);

        System.out.println("Add ingredients (type 'done' when finished):");
        while(true) {
            System.out.print("Ingredient name: ");
            String ingName = input.next();
            if (ingName.equalsIgnoreCase("Done")) {
                break;
            }
            System.out.print("Quantity: ");
            double qty = input.nextDouble();
            System.out.print("Units: ");
            String unit = input.next();

            Ingredient ingredient = new Ingredient(ingName, qty, unit);
            recipe.addIngredient(ingredient);
        }

        recipeBook.addRecipe(recipe);
        System.out.println("Recipe added successfully!");
    }

    // EFFECTS: displays all recipes in the collection
    private void doViewRecipes() {
        List<Recipe> recipes = recipeBook.getRecipes();
        if (recipes.isEmpty()) {
            System.out.println("No recipes available." );
            return;
        }

        System.out.println("Recipes:");
        for (Recipe r : recipes) {
            System.out.println("| name: " + r.getRecipeName() + " | cuisine: " + r.getCuisineType() + " | cooking time: " + r.getCookingTime() + " | min cost: $" + r.getCost());
        }
    }

    // EFFECTS: search recipes by ingredient, cuisine, cookingtime, or cost
    private void doSearchRecipes() {
        System.out.println("\nSearch by:");
        System.out.println("\ti -> ingredient");
        System.out.println("\tc -> cuisine");
        System.out.println("\tt -> max cooking time");
        System.out.println("\tb -> max cost");

        String choice = input.next().toLowerCase();
        List<Recipe> results;

        if (choice.equals("i")) {
            System.out.print("Enter ingredient name: ");
            String ing = input.next();
            results = recipeBook.searchByIngredient(ing);

        } else if (choice.equals("c")) {
            System.out.print("Enter cuisine type: ");
            String cuisine = input.next();
            results = recipeBook.searchByCuisine(cuisine);
  
        } else if (choice.equals("t")) {
            System.out.print("Enter maximum cooking time: ");
            int time = input.nextInt();
            results = recipeBook.searchByCookingTime(time);
 
        } else if (choice.equals("b")) {
            System.out.print("Enter maximum budget: ");
            double cost = input.nextDouble();
            results = recipeBook.searchByCost(cost);
   
        } else {
            System.out.println("Selection not valid...");
            return;
        }

        if (results.isEmpty()) {
            System.out.println("No recipes found.");
        } else {
            System.out.println("\nSearch results: ");
            for (Recipe r : results) {
                System.out.println(" " + r.getRecipeName() + " " + r.getCuisineType() + " " + r.getCookingTime() + " min " + r.getCost());
            }
        }
    }

    // EFFECTS: generates a grocery list from all recipes in recipeBook
    private void doGroceryList() {
        List<Ingredient> groceryList = recipeBook.generateGroceryList(recipeBook.getRecipes());
        if (groceryList.isEmpty()) {
            System.out.println("No ingredients found." );
            return;
        }

        System.out.println("\nGrovery list: ");
        for (Ingredient i : groceryList) {
            System.out.println(" " + i.getIngredientQuantity() + " " + i.getIngredientUnit() + " " + i.getIngredientName());
        }
    }
}
