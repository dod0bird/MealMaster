package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeTest {
    private Recipe testRecipe;
    private Ingredient banana;
    private Ingredient flour;
    private Ingredient bakingSoda;
    
    @BeforeEach
    void runBefore() {
        testRecipe = new Recipe("Banana Bread", 75, "American", 4.00);
        banana = new Ingredient("banana", 2, "unit");
        flour = new Ingredient("flour", 2, "cups");
        bakingSoda = new Ingredient("baking soda", 1, "teaspoon");
        Ingredient salt = new Ingredient("salt", 0.25, "teaspoon");
        Ingredient brownSugar = new Ingredient("brown sugar", 0.75, "cups");
        Ingredient butter = new Ingredient("butter", 0.5, "cups");
        Ingredient egg = new Ingredient("egg", 2, "units");
    }

    @Test
    void constructorTest() {
        assertEquals("Banana Bread", testRecipe.getRecipeName());
        assertEquals(0, testRecipe.getIngredients().size());
        assertEquals(75, testRecipe.getCookingTime());
        assertEquals("American", testRecipe.getCuisineType());
        assertEquals(4.00, testRecipe.getCost(), 0);
    }

    @Test
    void getCookingTimeTest() {
        assertEquals(75, testRecipe.getCookingTime());
    }

    @Test
    void getCuisineTypeTest() {
        assertEquals("American", testRecipe.getCuisineType());
    }

    @Test
    void getCostTest() {
        assertEquals(4.00, testRecipe.getCost(), 0);
    }

    @Test
    void addIngredientTest() {
        assertEquals(0, testRecipe.getIngredients().size());
        testRecipe.addIngredient(banana);
        assertEquals(1, testRecipe.getIngredients().size());
        assertEquals(banana, testRecipe.getIngredients().get(0));
        testRecipe.addIngredient(flour);
        assertEquals(2, testRecipe.getIngredients().size());
        assertEquals(flour, testRecipe.getIngredients().get(1));
    }

    @Test
    void hasIngredientTest() {
        assertFalse(testRecipe.hasIngredient("banana"));
        testRecipe.addIngredient(banana);
        assertTrue(testRecipe.hasIngredient("banana"));
        assertFalse(testRecipe.hasIngredient("flour"));
    }

    @Test
    void isWithinCookingTimeTest() {
        assertTrue(testRecipe.isWithinCookingTime(76));
        assertTrue(testRecipe.isWithinCookingTime(75));
        assertFalse(testRecipe.isWithinCookingTime(74));
    }

    @Test
    void isWithinCostTest() {
        assertFalse(testRecipe.isWithinCost(3.99));
        assertTrue(testRecipe.isWithinCost(4.00));
        assertTrue(testRecipe.isWithinCost(6.01));
    }
}
