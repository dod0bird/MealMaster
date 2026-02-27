package persistence;

import model.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// this code was inspiried by the code in the JsonSerializationDemo project provided.

// Represents a reader that reads RecipeBook data from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads recipebook from file and returns it
    public RecipeBook read() {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) {
        StringBuilder jsonContent = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> jsonContent.append(s));
        } catch (Exception e) {
            return "";
        }
        return jsonContent.toString();
    }

    // EFFECTS: parses recipebook from JSON object and returns it
    private RecipeBook parseRecipeBook(JSONObject jsonObject) {
        RecipeBook rb = new RecipeBook();
        addRecipes(rb, jsonObject);
        return rb;
    }

    // MODIFIES: rb
    // EFFECTS: parses recipes from JSON object and adds them to recipebook
    private void addRecipes(RecipeBook rb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipes");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(rb, nextRecipe);
        }
    }

    // MODIFIES: rb
    // EFFECTS: parses recipe from JSON object and adds it to recipebook
    private void addRecipe(RecipeBook rb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int time = jsonObject.getInt("time");
        double cost = jsonObject.getDouble("cost");
        String cusine = jsonObject.getString("cuisine");
        Recipe recipe = new Recipe(name, time, cusine, cost);

        JSONArray ingredientJsonArray = jsonObject.getJSONArray("ingredients");
        for (Object json : ingredientJsonArray) {
            JSONObject ingredientObject = (JSONObject) json;
            String ingName = ingredientObject.getString("name");
            double quantity = ingredientObject.getDouble("quantity");
            String unit = ingredientObject.getString("unit");
            Ingredient ingredient = new Ingredient(ingName, quantity, unit);
            recipe.addIngredient(ingredient); 
        }

        rb.addRecipe(recipe);
    }
}
