package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.*;

// this code was inspiried by the code in the JsonSerializationDemo project provided.

@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        RecipeBook rb = reader.read();
        assertNull(rb);
    }

    @Test
    void testReaderEmptyRecipeBook() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecipeBook.json");
        RecipeBook rb = reader.read();
        assertEquals(0, rb.getRecipes().size());
    }

    @Test
    void testReaderSingleRecipeBook() {
        JsonReader reader = new JsonReader("./data/testReaderSingleRecipeBook.json");
        RecipeBook rb = reader.read();
        assertEquals(1, rb.getRecipes().size());

        Recipe r = rb.getRecipes().get(0);
        checkRecipe("pasta", 30, "italian", 13, r);

        assertEquals(2, r.getIngredients().size());
        checkIngredient("spaghetti", 200, "g", r.getIngredients().get(0));
        checkIngredient("tomato sauce", 1, "can", r.getIngredients().get(1));
    }

    @Test
    void testReaderMultiRecipeBook() {
        JsonReader reader = new JsonReader("./data/testReaderMultiRecipeBook.json");
        RecipeBook rb = reader.read();
        assertEquals(2, rb.getRecipes().size());

        Recipe r = rb.getRecipes().get(0);
        checkRecipe("pasta", 30, "italian", 13, r);

        Recipe r2 = rb.getRecipes().get(1);
        checkRecipe("fried rice", 20, "chinese", 5, r2);

        assertEquals(2, r.getIngredients().size());
        checkIngredient("spaghetti", 200, "g", r.getIngredients().get(0));
        checkIngredient("tomato sauce", 1, "can", r.getIngredients().get(1));

        assertEquals(2, r2.getIngredients().size());
        checkIngredient("rice", 1, "cup", r2.getIngredients().get(0));
        checkIngredient("egg", 2, "units", r2.getIngredients().get(1));
    }
}
