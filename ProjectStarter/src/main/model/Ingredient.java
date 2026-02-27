package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents an ingredient having a name, quantity, and unit of measurement
public class Ingredient implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", ingredientName);
        json.put("quantity", ingredientQuantity);
        json.put("unit", ingredientUnit);
        return json;
    }
}
