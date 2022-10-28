package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

//This class creates reading lists that contains books
public class BookList extends ArrayList<Book> implements Writeable {
    private final String listName;    // the name of the list
    private ArrayList<Book> books;

    // REQUIRES: listName has non-zero length
    //  EFFECTS: creates a list with the name set to specified name, with Book objects
    public BookList(String name) {
        listName = name;
        books = new ArrayList<>();
    }

    // REQUIRES: book object
    // MODIFIES: this
    // EFFECTS: creates and adds a book to the list
    public String addBook(Book book) {
        books.add(book);
        return book.getTitle();
    }

    // REQUIRES: title, author
    // MODIFIES: this
    // EFFECTS: searches the list for the book using the title and author ( searchBook() )
    //          returns a message: if book is removed successfully or book doesn't exist in list
    public String removeBook(String title, String author) {
        int idx = searchBook(title, author);
        String message;

        if (idx != -1) {
            books.remove(idx);
            message = title + " has been removed from the list.";
        } else {
            message = title + " is not in this list.";
        }
        return message;
    }

    // REQUIRES: list, title, author
    // EFFECTS: searches the list using the title,
    //          checks if the author matches to see if it's the book we're looking for
    //          if it is, then return the index of book in the list
    public int searchBook(String title, String author) {
        int idx = 0; //index of the book
        for (Book book: books) {
            if (title.equals(book.getTitle())) {
                if (author.equals(book.getAuthor())) {
                    idx = books.indexOf(book);
                    return idx;
                }
            } else {
                idx = -1;
            }
        }
        return idx;
    }

    // EFFECTS: outputs the information of all the books in the list
    public String displayBooks() {
        String bookInfo = getListName() + "\n\n";
        for (Book book : books) {
            bookInfo += book.toString() + "\n\n";
        }
        return bookInfo;
    }

    public String getListName() {
        return listName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("list", listName);
        return json;
    }

    //EFFECTS: returns books in this list as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : books) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }
}
