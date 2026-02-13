package model;

// Represents an ingredient having a name, quantity, and unit of measurement
public class Ingredient {
    private String ingredientName;     // ingredient name
    private double ingredientQuantity; // ingredient quantity
    private String ingredientUnit;      // ingredient unit

    /*
    * REQUIRES: name has non-zero length, quantity > 0, unit has non-zero length
    * EFFECTS: create a new ingredient with specified name and quantity
    */
    public Ingredient(String name, double quantity, String units) {
        this.ingredientName = name;
        this.ingredientQuantity = quantity;
        this.ingredientUnit = units;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getIngredientUnit() {
        return ingredientUnit;
    }

}
