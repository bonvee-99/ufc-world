package persistence;

import org.json.JSONObject;

// inspired by JSONSerializationDemo
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
