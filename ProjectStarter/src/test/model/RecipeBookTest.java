package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class RecipeBookTest {
    private RecipeBook testRecipeBook;
    private Recipe bananaBread;
    private Recipe pasta;
    private Recipe salad;

    @BeforeEach
    void runBefore() {
        testRecipeBook = new RecipeBook();

        bananaBread = new Recipe("banana bread", 75, "american", 4.00);
        Ingredient banana = new Ingredient("banana", 2, "units");
        bananaBread.addIngredient(banana);

        pasta = new Recipe("pasta", 30, "italian", 7.00);
        Ingredient tomatoSauce = new Ingredient("tomato sauce", 1, "can");
        pasta.addIngredient(tomatoSauce);

        salad = new Recipe("salad", 15, "Greek", 3.00);
        Ingredient lettuce = new Ingredient("leccture", 1, "head");
        salad.addIngredient(lettuce);
    }

    @Test 
    void constructorTest() {
        assertEquals(0, testRecipeBook.getRecipes().size());
    }

    @Test
    void testAddRecipe() {
        assertEquals(0, testRecipeBook.getRecipes().size());
        testRecipeBook.addRecipe(bananaBread);
        assertEquals(1, testRecipeBook.getRecipes().size());
        assertEquals(bananaBread, testRecipeBook.getRecipes().get(0));
    }

    @Test
    void getRecipeTest() {
        testRecipeBook.addRecipe(bananaBread);
        assertEquals(bananaBread, testRecipeBook.getRecipes());
    }

    @Test 
    void getRecipeByNameTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);
        assertEquals(pasta, testRecipeBook.getRecipeByName("pasta"));

        assertNull(testRecipeBook.getRecipeByName("pizza"));
    }

    @Test
    void searchByIngredientTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);

        assertEquals(1, testRecipeBook.searchByIngredient("banana").size());
        assertEquals(bananaBread, testRecipeBook.searchByIngredient("banana").get(0));
    }

    @Test
    void searchByIngredientTestNoMatch() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);

        assertEquals(0, testRecipeBook.searchByIngredient("tomato").size());
    }

    @Test 
    void searchByCookingTimeTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);

        assertEquals(1, testRecipeBook.searchByCookingTime(30).size());
        assertEquals(pasta, testRecipeBook.searchByCookingTime(30).get(0));
        assertFalse(testRecipeBook.searchByCookingTime(30).contains(bananaBread));
    }
    
    @Test 
    void searchByCookingTimeNoneFoundTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);

        assertEquals(0, testRecipeBook.searchByCookingTime(29).size());
    }

    @Test 
    void searchByCuisineTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);

        assertEquals(1, testRecipeBook.searchByCuisine("italian").size());
        assertEquals(pasta, testRecipeBook.searchByCuisine("italian").get(0));
    }

    @Test 
    void searchByCuisineNoneFoundTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);

        assertEquals(0, testRecipeBook.searchByCuisine("Chinese").size());
    }

    @Test
    void searchByCostTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);

        assertEquals(1, testRecipeBook.searchByCost(4.00).size());
        assertEquals(bananaBread, testRecipeBook.searchByCost(4.00).get(0));
    }

    @Test
    void searchByCostNoneFoundTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);

        assertEquals(0, testRecipeBook.searchByCost(3.99).size());
    }

    @Test
    void generateWeeklyScheduleTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);
        testRecipeBook.addRecipe(salad);

        int[] timelimits = {30, 20};

        assertEquals(2, testRecipeBook.generateWeeklySchedule(timelimits, 10.00, null, null));

        double totalCost = 0;
        for (Recipe r : testRecipeBook.generateWeeklySchedule(timelimits, 10.00, null, null)) {
            totalCost += r.getCost();
        }

        assertTrue(totalCost <= 10.00);
    }

    @Test
    void generateGroceryListOneRecipeTest() {
        testRecipeBook.addRecipe(bananaBread);

        List<Recipe> plannedRecipes = new ArrayList<Recipe>();
        plannedRecipes.add(bananaBread);

        assertEquals(1, testRecipeBook.generateGroceryList(plannedRecipes).size());
        assertEquals("banana", testRecipeBook.generateGroceryList(plannedRecipes).get(0));
    } 

    @Test
    void generateGroceryListTwoRecipeTest() {
        testRecipeBook.addRecipe(bananaBread);
        testRecipeBook.addRecipe(pasta);

        List<Recipe> plannedRecipes = new ArrayList<Recipe>();
        plannedRecipes.add(bananaBread);
        plannedRecipes.add(pasta);

        assertEquals(2, testRecipeBook.generateGroceryList(plannedRecipes).size());
        assertEquals("banana", testRecipeBook.generateGroceryList(plannedRecipes).get(0));
        assertEquals("tomato sauce", testRecipeBook.generateGroceryList(plannedRecipes).get(1));
    } 
    
}
