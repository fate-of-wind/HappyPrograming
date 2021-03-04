package persistence;

import model.Hand;
import model.Player2PastShown;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of Hand to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    //TODO citation:code taken and modified from JsonWriter.java package in JsonSerializationDemo
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    //TODO citation:code taken and modified from JsonWriter.java package in JsonSerializationDemo
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));  //same method given in example(JsonDemo)
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Player2PastShown to file
    //TODO citation:code taken and modified from JsonWriter.java package in JsonSerializationDemo
    public void write(Player2PastShown p) {
        JSONObject json = p.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Hand to file
    public void write(Hand p) {
        JSONObject json = p.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
