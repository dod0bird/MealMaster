package model;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class IngredientTest {
    private Ingredient testIngredient1;

    @BeforeEach
    void runBefore() {
        testIngredient1 = new Ingredient("banana", 1, "unit");
    }

    @Test
    void constructorTest() {
        assertEquals("banana", testIngredient1.getIngredientName());
        assertEquals(1, testIngredient1.getIngredientQuantity());
        assertEquals("unit", testIngredient1.getIngredientUnit());
    }

    @Test
    void getIngredientNameTest() {
        assertEquals("banana", testIngredient1.getIngredientName());
    }

    @Test
    void getIngredientQuantityTest() {
        assertEquals(1, testIngredient1.getIngredientQuantity());
    }

    @Test
    void getIngredientUnit() {
        assertEquals("unit", testIngredient1.getIngredientUnit());
    }

}
