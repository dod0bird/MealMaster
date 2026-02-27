package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.*;

// this code was inspiried by the code in the JsonSerializationDemo project provided.

@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriteInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data\0illegal:fileName.json");
            writer.open();
            fail("Exception was expected");
        } catch (Exception e) {
            //pass
        }
    }

    @Test
    void testWriteEmptyRecipeBook() {
        RecipeBook rb = new RecipeBook();
        JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecipeBook.json");
        writer.open();
        writer.write(rb);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeBook.json");
        rb = reader.read();
        assertEquals(0, rb.getRecipes().size());
    }

    @Test
    void testWriteSingleRecipeBook() {
        RecipeBook rb = new RecipeBook();
        Recipe ramen = new Recipe("ramen", 10, "japanese", 1);
        ramen.addIngredient(new Ingredient("noodle", 200, "g"));
        ramen.addIngredient(new Ingredient("egg", 1, "unit"));
        rb.addRecipe(ramen);
        
        JsonWriter writer = new JsonWriter("./data/testWriterSingleRecipeBook.json");
        writer.open();
        writer.write(rb);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterSingleRecipeBook.json");
        rb = reader.read();

        assertEquals(1, rb.getRecipes().size());

        Recipe testRecipe = rb.getRecipes().get(0);
        checkRecipe("ramen", 10, "japanese", 1, testRecipe);
        assertEquals(2, testRecipe.getIngredients().size());
        checkIngredient("noodle", 200, "g", testRecipe.getIngredients().get(0));
        checkIngredient("egg", 1, "unit", testRecipe.getIngredients().get(1));
    }

    @Test
    void testWriteMultiRecipeBook() {
        RecipeBook rb = new RecipeBook();
        Recipe miso = new Recipe("miso soup", 20, "japanese", 4);
        miso.addIngredient(new Ingredient("tofu", 150, "g"));
        miso.addIngredient(new Ingredient("miso soup base", 1, "carton"));

        Recipe ramen = new Recipe("ramen", 10, "japanese", 1);
        ramen.addIngredient(new Ingredient("noodle", 200, "g"));
        ramen.addIngredient(new Ingredient("egg", 1, "unit"));
        rb.addRecipe(ramen);
        rb.addRecipe(miso);
        
        JsonWriter writer = new JsonWriter("./data/testWriterMultiRecipeBook.json");
        writer.open();
        writer.write(rb);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterMultiRecipeBook.json");
        rb = reader.read();

        assertEquals(2, rb.getRecipes().size());

        Recipe r1 = rb.getRecipes().get(0);
        checkRecipe("ramen", 10, "japanese", 1, r1);
        assertEquals(2, r1.getIngredients().size());
        checkIngredient("noodle", 200, "g", r1.getIngredients().get(0));
        checkIngredient("egg", 1, "unit", r1.getIngredients().get(1));

        Recipe r2 = rb.getRecipes().get(1);
        checkRecipe("miso soup", 20, "japanese", 4, r2);
        assertEquals(2, r2.getIngredients().size());
        checkIngredient("tofu", 150, "g", r2.getIngredients().get(0));
        checkIngredient("miso soup base", 1, "carton", r2.getIngredients().get(1));
    }
}
