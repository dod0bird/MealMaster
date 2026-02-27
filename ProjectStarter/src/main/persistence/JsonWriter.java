package persistence;

import model.*;
import org.json.JSONObject;

import java.io.*;

// this code was inspiried by the code in the JsonSerializationDemo project provided.

// Represents a writer that writes the JSON representation of recipebook to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: creates a writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination
    // cannot be open for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of recipebook to file
    public void write(RecipeBook recipeBook) {
        JSONObject json = recipeBook.toJson();
        saveToFile(json.toString(TAB));

    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: saves string to file
    public void saveToFile(String file) {
        writer.print(file);
    }
}