package persistence;

import model.Genre;
import model.Book;
import model.BookList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Library;
import org.json.*;

// Represents a reader that reads library from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads library from file and returns it
    // throws IOException if an error occurs when reading data from file
    public Library read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Library parseLibrary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Library lb = new Library(name);
        addLists(lb, jsonObject);
        return lb;
    }

    // MODIFIES: this
    // EFFECTS: parses lists from JSON object and adds them to library
    private void addLists(Library lb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("lists");

        for (Object json : jsonArray) {
            JSONObject nextList = (JSONObject) json;
            addLists(lb, nextList);
        }
    }
}
