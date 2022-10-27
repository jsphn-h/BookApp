package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a library that contains lists of books
public class Library implements Writeable {
    private String name;
    private List<BookList> lists;

    //EFFECTS: constructs library with a name and empty list of lists
    public Library(String name) {
        this.name = name;
        lists = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds book list to this library
    public void addList(BookList list) {
        lists.add(list);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("lists", listsToJson());
        return json;
    }

    // EFFECTS: returns lists in this library as a JSON array
    private JSONArray listsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (BookList l : lists) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }
}
