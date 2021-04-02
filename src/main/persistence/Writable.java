package persistence;

import org.json.JSONObject;
//a Writable interface

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
