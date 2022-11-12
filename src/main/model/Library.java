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
    private List<Book> books;

    //EFFECTS: constructs library with a name and empty list of lists
    public Library(String name) {
        this.name = name;
        books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds a book to a list
    public void addBook(Book book) {
        books.add(book);
    }

    // EFFECTS: removes a book from a list
    public void removeBook(String title, String author) {
        List<Book> newList = new ArrayList<>();
        for (Book b : getBooks()) {
            System.out.println(b);
        }
    }

    // EFFECTS: returns an unmodifiable list of book lists
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("library", name);
        json.put("lists", booksToJson());
        return json;
    }

    // EFFECTS: returns lists in this library as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : books) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}
