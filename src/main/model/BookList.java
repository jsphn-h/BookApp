package model;

import java.util.ArrayList;

public class BookList extends ArrayList<Book> {
    private final String listName;    // the name of the list

    /*
        REQUIRES: listName has non-zero length
        EFFECTS: creates a list with the name set to specified name, with Book objects
     */
    public BookList(String name) {
        listName = name;
        ArrayList<Book> listName = new ArrayList();
    }

    /*
       REQUIRES: book object
       MODIFIES: this
       EFFECTS: creates and adds a book to the list
     */
    public String addBook(ArrayList<Book> list, Book book) {
        list.add(book);
        return book.getTitle();
    }

    /*
       REQUIRES: list, title, author
       MODIFIES: this
       EFFECTS: searches the list for the book using the title and author ( searchBook() )
                returns a message: if book is removed successfully or book doesn't exist in list
     */
    public String removeBook(ArrayList<Book> list, String title, String author) {
        int idx = searchBook(list, title, author);
        String message;

        if (idx != -1) {
            list.remove(idx);
            message = title + " has been removed from the list.";
        } else {
            message = title + " is not in this list.";
        }
        return message;
    }

    /*
        REQUIRES: list, title, author
        EFFECTS: searches the list using the title,
                 checks if the author matches to see if it's the book we're looking for
                 if it is, then return the index of book in the list
     */
    public int searchBook(ArrayList<Book> list, String title, String author) {
        int idx = 0; //index of the book
        for (Book book: list) {
            if (title.equals(book.getTitle())) {
                if (author.equals(book.getAuthor())) {
                    idx = list.indexOf(book);
                    return idx;
                }
            } else {
                idx = -1;
            }
        }
        return idx;
    }

    public String getListName() {
        return listName;
    }

    /*
        REQUIRES: list, book title
        EFFECTS: outputs the information of all the books in the list
     */
    public String displayBooks(ArrayList<Book> list) {
        String bookInfo = getListName() + "\n\n";
        for (Book book : list) {
            bookInfo += book.toString() + "\n\n";
        }
        return bookInfo;
    }
}
