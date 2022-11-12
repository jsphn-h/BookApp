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
    // EFFECTS: adds book list to this library
//    public void addList(BookList list) {
//        lists.add(list);
//    }

    // MODIFIES: this
    // EFFECTS: adds a book to a list
    public void addBook(Book book) {
        books.add(book);
    }

    // EFFECTS: returns an unmodifiable list of book lists
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    // EFFECTS: returns the specified list
//    public BookList findList(String listName) {
//        for (BookList list : books) {
//            if (listName.equals(list.getListName())) {
//                return list;
//            } else {
//                System.out.println("The list doesn't exist.");
//            }
//        }
//        return null;
//    }

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
