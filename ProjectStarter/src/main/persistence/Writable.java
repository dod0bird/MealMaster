package persistence;

import org.json.JSONObject;

// this code was inspiried by the code in the JsonSerializationDemo project provided.

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
