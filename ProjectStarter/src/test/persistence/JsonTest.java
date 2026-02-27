package persistence;

import model.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import static org.junit.jupiter.api.Assertions.assertEquals;


// this code was inspiried by the code in the JsonSerializationDemo project provided.

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    
    // EFFECTS: check that recipe fields match expected values 
    protected void checkRecipe(String name, int time, String cuisine, double cost, Recipe recipe) {
        assertEquals(name, recipe.getRecipeName());
        assertEquals(time, recipe.getCookingTime());
        assertEquals(cuisine, recipe.getCuisineType());
        assertEquals(cost, recipe.getCost());
    }

    // EFFECTS: check that recipe fields match expected values 
    protected void checkIngredient(String name, double quantity, String unit, Ingredient ingredient) {
        assertEquals(name, ingredient.getIngredientName());
        assertEquals(quantity, ingredient.getIngredientQuantity());
        assertEquals(unit, ingredient.getIngredientUnit());
    }
}