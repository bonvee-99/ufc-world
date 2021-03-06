package persistence;

import model.UfcWorld;
import org.json.JSONObject;

import java.io.*;

// writer that writes JSON representation of UfcWorld
// inspired by JSONSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS creates a writer that writes data to the destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: creates and opens a new writer with a file of destination destination
    // throws FileNotFoundException if destination cannot be opened to write on
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes a JSON representation of the given UfcWorld to file
    public void write(UfcWorld ufcWorld) {
        JSONObject json = ufcWorld.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String ufcWorldJson) {
        writer.print(ufcWorldJson);
    }

    // MODIFIES: this
    // EFFECTS; closes the writer
    public void close() {
        writer.close();
    }
}
