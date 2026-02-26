package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// this code was inspiried by the code in the JsonSerializationDemo project provided.

// Represents a reader that reads RecipeBook data from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {

    }

    // EFFECTS: reads recipebook from file and returns it
    public RecipeBook read() {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) {
        return "";
    }

    // EFFECTS: parses recipebook from JSON object and returns it
    private RecipeBook parseRecipeBook(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: rb
    // EFFECTS: parses recipes from JSON object and adds them to recipebook
    private void addRecipes(RecipeBook rb, JSONObject jsonObject) {
    }

    // MODIFIES: rb
    // EFFECTS: parses recipe from JSON object and adds it to recipebook
    private void addRecipe(RecipeBook rb, JSONObject jsonObject) {
    }
}
