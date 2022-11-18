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

//        System.out.println("Welcome to BookApp!");
//        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
//    private void runApp() {
//        boolean keepRunning = true;
//        String userInput;
//
//        init();
//
//        while (keepRunning) {
//            displayMenu();
//            userInput = input.next();
//
//            if (userInput.equals("8")) {
//                keepRunning = false;
//            } else {
//                processInput(userInput);
//            }
//        }
//        System.out.println("Keep on reading!");
//    }

    // MODIFIES: this
    // EFFECTS: processes user input
//    private void processInput(String userInput) {
//        switch (userInput) {
//            case "1": getLists();
//                break;
//            case "2": displayAllBooks();
//                break;
//            case "3": displayBooksInList();
//                break;
//            case "4": addToList();
//                break;
//            case "5": removeBook();
//                break;
//            case "6": saveLibrary();
//                break;
//            case "7": loadLibrary();
//                break;
//            default:
//                System.out.println("Invalid input");
//                break;
//        }
//    }

    private void processDisplayInput(String userInput) {
        switch (userInput) {

            default:
                System.out.println("Invalid input");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the app
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu with action choices to user
    public void displayMenu() {
        System.out.println();
        System.out.println("Please select an option below:");
        System.out.println("\t1) Display lists");
        System.out.println("\t2) Display all books");
        System.out.println("\t3) Display books from list");
        System.out.println("\t4) Add book to list");
        System.out.println("\t5) Remove book from list");
        System.out.println("\t6) Save library");
        System.out.println("\t7) Load library");
        System.out.println("\t8) Exit");
        System.out.println("----- Please load the library to see your books -----");
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
//        System.out.println("Please select a list to display: ");
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
    public void addToList(String title, String author, int seriesNum, String selectedGenre, String listInput) {
//        String title = readTitle();
//        String author = readAuthor();
//        int seriesNum = readSeriesNum();

        Genre genre = readGenre(selectedGenre);

//        System.out.println("Select list to add book to: ");
        Lists list = readList(listInput);
        Book book = new Book(title, author, seriesNum, genre, list);
        library.addBook(book);
        System.out.println(title + " has been added to " + list.toString());
    }

    // MODIFIES: this
    // EFFECTS: removes a book to a specified list
    public void removeBook() {
        String title = readTitle();
        String author = readAuthor();

        ArrayList<Book> newList = new ArrayList<>();
        System.out.println("LOOP\n");
        int idx = 0;
        for (Book b : library.getBooks()) {
            if (!b.getTitle().equals(title)) {
                if (!b.getAuthor().equals(author)) {
                    newList.add(b);
                }
            }
        }
        library.removeBook(newList);
    }

    //------ READ USER INPUT FUNCTIONS ------
    //EFFECTS: prompts user to enter the title of the book and returns it
    private String readTitle() {
        System.out.println("Enter the title: ");
        String title = input.next();
        while (!validStringInput(title)) {
            title = input.next();
        }
        return title;
    }

    //EFFECTS: prompts user to enter the author and returns it
    private String readAuthor() {
        System.out.println("Enter the author: ");
        String author = input.next();
        while (!validStringInput(author)) {
            author = input.next();
        }
        return author;
    }

    // EFFECTS: prompts user to select a list to add book to
    private Lists readList(String list) {
//        int option = 1;
//        for (Lists l : Lists.values()) {
//            System.out.println(option + ": " + l);
//            option++;
//        }
        int selection = 0;
        if (list.equals("Wishlist")) {
            selection = 1;
        } else if (list.equals("Read")) {
            selection = 2;
        } else if (list.equals("Reading")) {
            selection = 3;
        }

//        int selection = input.nextInt();
        return Lists.values()[selection - 1];
    }

    // EFFECTS: prompts user to select a genre and returns the selected genre
    private Genre readGenre(String genre) {
//        int option = 1;
//        for (Genre g : Genre.values()) {
//            System.out.println(option + ": " + g);
//            option++;
//        }

//        int selection = input.nextInt();
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
        System.out.println(Genre.values()[selection - 1]);
        return Genre.values()[selection - 1];
    }

    // EFFECTS: prompts user to enter which book it is in the series and returns it
    private int readSeriesNum() {
        System.out.println("Book x in series (0 if it's not part of a series): ");
        int seriesNum = input.nextInt();
        while (seriesNum < 0) {
            seriesNum = input.nextInt();
        }
        return seriesNum;
    }

    // EFFECTS: checks whether the user input is valid
    private boolean validStringInput(String input) {
        if (input.isEmpty() || input.equals(" ")) {
            System.out.println("Input cannot be empty. Try again: ");
            return false;
        } else {
            return true;
        }
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
//            System.out.println("Saved" + library.getName() + " to " + JSON_STORE);
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
//            System.out.println("Loaded " + library.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            message = "Unable to read from file: " + JSON_STORE;
//            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return message;
    }
}
