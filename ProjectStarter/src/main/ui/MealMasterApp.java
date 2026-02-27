package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// this ui code was inspiried by the code in the TellerApp project provided.
// the JSON code was inspiried JsonSerializationDemo project provided.

// Represents the meal master application
@ExcludeFromJacocoGeneratedReport
public class MealMasterApp {
    private static final String JSON_STORE = "./data/mealMaster.json";
    private RecipeBook recipeBook;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: runs the Meal Master application
    public MealMasterApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        } else if (command.equals("g")) {
            doGenerateWeeklySchedule();
        } else if (command.equals("w")) {
            doSaveRecipeBook();
        } else if (command.equals("l")) {
            doLoadRecipeBook();
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
        System.out.println("\tg -> generate weekly schedule");
        System.out.println("\tw -> save recipe book to file");
        System.out.println("\tl -> load recipe book from file");
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
        while (true) {
            System.out.print("Ingredient name: ");
            String ingName = input.next();
            if (ingName.equalsIgnoreCase("Done")) {
                break;
            }
            System.out.print("Quantity: ");
            double qty = input.nextDouble();
            System.out.print("Units: ");
            recipe.addIngredient((new Ingredient(ingName, qty, input.next())));
        }
        recipeBook.addRecipe(recipe);
        System.out.println("Recipe added successfully!");
    }

    // EFFECTS: displays all recipes in the collection
    private void doViewRecipes() {
        List<Recipe> recipes = recipeBook.getRecipes();
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
            return;
        }

        System.out.println("Recipes:");
        for (Recipe r : recipes) {
            System.out.println("| name: " + r.getRecipeName() + " | cuisine: " + r.getCuisineType() 
                    + " | cooking time: " + r.getCookingTime() + " | min cost: $" + r.getCost());
        }
    }

    // EFFECTS: search recipes by ingredient, cuisine, cookingtime, or cost
    private void doSearchRecipes() {
        System.out.println("\nSearch by: \ni - ingredient \nc - cuisine \nt - max time \nsb - max-cost");
        List<Recipe> results = getSearchResults(input.next());
        if (results == null) {
            System.out.println("Selection not valid...");
        } else if (results.isEmpty()) {
            System.out.println("No recipes found.");
        } else {
            System.out.println("\nSearch results: ");
            for (Recipe r : results) {
                System.out.println("| name: " + r.getRecipeName() + " | cuisine: " + r.getCuisineType() 
                        + " | cooking time: " + r.getCookingTime() + " | min cost: $" + r.getCost());
            }
        }
    }

    // EFFECTS: processes input for doSearchRecipes
    private List<Recipe> getSearchResults(String choice) {
        if (choice.equals("i")) {
            System.out.print("Enter ingredient name: ");
            return recipeBook.searchByIngredient(input.next());
        } else if (choice.equals("c")) {
            System.out.print("Enter cuisine type: ");
            return recipeBook.searchByCuisine(input.next());
        } else if (choice.equals("t")) {
            System.out.print("Enter maximum cooking time: ");
            return recipeBook.searchByCookingTime(input.nextInt());
        } else if (choice.equals("b")) {
            System.out.print("Enter maximum budget: ");
            return recipeBook.searchByCost(input.nextDouble());
        }
        return null;
    }

    // EFFECTS: generates a grocery list from all recipes in recipeBook
    private void doGroceryList() {
        List<Ingredient> groceryList = recipeBook.generateGroceryList(recipeBook.getRecipes());
        if (groceryList.isEmpty()) {
            System.out.println("No ingredients found.");
            return;
        }

        System.out.println("\nGrocery list: ");

        for (Ingredient i : groceryList) {
            System.out.println("- " + i.getIngredientQuantity() + " " + i.getIngredientUnit() 
                    + " " + i.getIngredientName());
        }
    }

    // EFFECTS: generates a weekly schedule from provided time limit and budget
    private void doGenerateWeeklySchedule() {
        int[] timeLimits = new int[7];

        System.out.println("Enter daily time limits (minutes) for the week");
        for (int i = 0; i < 7; i++) {
            System.out.println("Day " + (i + 1) + ": ");
            timeLimits[i] = input.nextInt();
        }

        System.out.println("Enter maximum weekly budget: ");
        double budget = input.nextDouble();

        List<Recipe> schedule = recipeBook.generateWeeklySchedule(timeLimits, budget);

        System.out.println("\nWeekly Schedule: ");

        int day = 1;
        for (Recipe r : schedule) {
            System.out.println("Day " + day + ": " + r.getRecipeName());
            day++;
        }
    }

    // EFFECTS: saves recipebook to file
    private void doSaveRecipeBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipeBook);
            jsonWriter.close();
            System.out.println("Saved recipe book to file: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads recipebook from file
    private void doLoadRecipeBook() {
        try {
            recipeBook = jsonReader.read();
            System.out.println("Loaded recipe book from :" + JSON_STORE);
        } catch (Exception e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
