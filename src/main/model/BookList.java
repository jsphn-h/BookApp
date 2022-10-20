package model;

public class BookList {
    private String listName;    // the name of the list

    /*
        REQUIRES: listName has non-zero length
        EFFECTS: creates a list with the name set to specified name, with Book objects
     */
    public BookList(String name) {
        listName = name;

    }

    /*
       REQUIRES: title, author
       MODIFIES: this
       EFFECTS: book is added to the list and the title is returned
     */
    public String addBook() {

        return null;
    }

    /*
       REQUIRES: title
       MODIFIES: this
       EFFECTS: book is removed from the list and the title is returned
     */
    public String removeBook() {

        return null;
    }
}
