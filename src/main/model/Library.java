package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Scanner;

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

    // EFFECTS: returns an unmodifiable list of book lists
    public List<List> getLists() {
        return Collections.unmodifiableList(lists);
    }

    // EFFECTS: returns the specified list
    public BookList findList(String listName) {
        for (BookList list : lists) {
            if (listName.equals(list.getListName())) {
                return list;
            } else {
                System.out.println("The list doesn't exist.");
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("library", name);
        json.put("read", listsToJson());
        json.put("toRead", listsToJson());
        json.put("reading", listsToJson());
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
