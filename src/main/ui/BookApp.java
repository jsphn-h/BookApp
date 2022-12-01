package ui;

import model.Book;
import model.Genre;
import model.Library;
import model.Lists;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// This class "runs" the application. It has the menu and allows the user to interact with the app
public class BookApp {
    private static final String JSON_STORE = "./data/Library.json";
    private Scanner input;
    private Library library;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs and runs the book app
    public BookApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        library = new Library("Library");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // ----- MENU FUNCTIONS ------
    // EFFECTS: displays the name of the lists
    public static String getLists() {
        String lists = "";
        for (Lists l : Arrays.asList(Lists.values())) {
            lists += (l + ", ");
        }

        return lists;
    }

    // EFFECTS: display the book information of books in the list
    public ArrayList displayAllBooks() {
        Collection<Book> lists = library.getBooks();
        Iterator<Book> iterator = lists.iterator(); // iterator on the immutable list
        ArrayList<Book> books = new ArrayList<>();
        // print the immutable list
        while (iterator.hasNext()) {
            books.add(iterator.next());
        }

        return books;
    }

    public ArrayList<Book> displayBooksInList(String list) {
        Lists selectedList = readList(list);
        Collection<Book> lists = library.getBooks();
        Iterator<Book> iterator = lists.iterator(); // iterator on the immutable list
        ArrayList<Book> books = new ArrayList<>(); // list of books
        // print the immutable list
        while (iterator.hasNext()) {
            Book nextBook = iterator.next();
            if (nextBook.getList().equals(selectedList)) {
                books.add(nextBook);
            }
        }
        return books;
    }

    // MODIFIES: this
    // EFFECTS: adds a book to a specified list
    public String addToList(String title, String author, int seriesNum, String selectedGenre, String listInput) {
        Genre genre = readGenre(selectedGenre);
        Lists list = readList(listInput);
        Book book = new Book(title, author, seriesNum, genre, list);
        library.addBook(book);

        return title + " has been added to " + list.toString();
    }

    // MODIFIES: this
    // EFFECTS: removes a book to a specified list
    public String removeBook(String title, String author) {
        String msg = "Book not in library.";
        boolean bookFound = false;

        ArrayList<Book> newList = new ArrayList<>();
        for (Book b : library.getBooks()) {
            if (!b.getTitle().equals(title)) {
                if (!b.getAuthor().equals(author)) {
                    newList.add(b);
                }
            } else if (b.getTitle().equals(title) && b.getAuthor().equals(author)) {
                bookFound = true;
                msg = title + " has been removed from library.";
            }
        }

        if (bookFound) {
            library.removeBook(newList, title);
        }

        return msg;
    }

    //------ READ USER INPUT FUNCTIONS ------
    // EFFECTS: prompts user to select a list to add book to
    private Lists readList(String list) {
        int selection = 0;
        if (list.equals("Wishlist")) {
            selection = 1;
        } else if (list.equals("Read")) {
            selection = 2;
        } else if (list.equals("Reading")) {
            selection = 3;
        }

        return Lists.values()[selection - 1];
    }

    // EFFECTS: prompts user to select a genre and returns the selected genre
    private Genre readGenre(String genre) {
        int selection = 0;
        if (genre.equals("Fiction")) {
            selection = 1;
        } else if (genre.equals("Romance")) {
            selection = 2;
        } else if (genre.equals("Fantasy")) {
            selection = 3;
        } else if (genre.equals("Mystery")) {
            selection = 4;
        } else if (genre.equals("Thriller")) {
            selection = 5;
        } else if (genre.equals("Action")) {
            selection = 6;
        } else if (genre.equals("Horror")) {
            selection = 7;
        } else if (genre.equals("Sci-Fi")) {
            selection = 8;
        } else if (genre.equals("Nonfiction")) {
            selection = 9;
        }

        return Genre.values()[selection - 1];
    }

    // ----- LIBRARY FUNCTIONS -----
    // EFFECTS: saves the lists to file
    public String saveLibrary() {
        String message = "";
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            message = "Saved" + library.getName() + " to " + JSON_STORE;
        } catch (FileNotFoundException e) {
            message = "Unable to write to file: " + JSON_STORE;
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

        return message;
    }

    // MODIFIES: this
    // EFFECTS: loads library from file
    public String loadLibrary() {
        String message = "";
        try {
            library = jsonReader.read();
            message = "Loaded " + library.getName() + " from " + JSON_STORE;
        } catch (IOException e) {
            message = "Unable to read from file: " + JSON_STORE;
        }
        return message;
    }
}
