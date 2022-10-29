package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        System.out.println("Welcome to BookApp!\n");
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepRunning = true;
        String userInput;

        init();

        while (keepRunning) {
            displayMenu();
            userInput = input.next();

            if (userInput.equals("7")) {
                keepRunning = false;
            } else {
                processInput(userInput);
            }
        }
        System.out.println("Keep on reading!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processInput(String userInput) {
        switch (userInput) {
            case "1":
                createList();
                break;
            case "2":
                addToList();
                break;
            case "3":
                removeFromList();
                break;
            case "4":
                displayBooks();
                break;
            case "5":
                printLists();
                break;
            case "6":
                saveLists();
                break;
            case "7":
                loadList();
                break;
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
        System.out.println("Please select an option below:");
        System.out.println("\t1) Create a list");
        System.out.println("\t2) Add book to list");
        System.out.println("\t3) Remove book from list");
        System.out.println("\t4) Display books");
        System.out.println("\t5) Save lists");
        System.out.println("\t6) Load library");
        System.out.println("\t7) Exit");
        System.out.println("----- WARNING: you can't add/remove a book if no list has been created -----");
    }

    // MODIFIES: this
    // EFFECTS: creates a book list
    private void createList() {
        System.out.println("Enter list name: ");
        String listName = input.next();
        Boolean validInput = false;

        while (!validInput) {
            if (!validStringInput(listName)) {
                listName = input.next();
            } else {
                new BookList(listName);
                System.out.println("The list '" + listName + "' has been created. \n");
                validInput = true;
            }
        }
        library.addList(new BookList(listName)); //Class type: BookList
    }

    // MODIFIES: this
    // EFFECTS: adds a book to a specified list
    private void addToList() {
        String title = readTitle();
        String author = readAuthor();
        Genre genre = readGenre();
        int seriesNum = readSeriesNum();

        System.out.println("Enter list to be added to: ");
        String listName = input.next();
        while (!validStringInput(listName)) {
            listName = input.next();
        }

//        BookList list = searchList(listName);
//        Book book = new Book(title, author, seriesNum, genre);
//        list.addBook(book);
//        System.out.println(title + " has been added to " + list.getListName());
    }

    // MODIFIES: this
    // EFFECTS: removes a book to a specified list
    private void removeFromList() {
        String title = readTitle();
        String author = readAuthor();

        System.out.println("Enter list to remove the book from: ");
        String listName = input.next();
        while (!validStringInput(listName)) {
            listName = input.next();
        }

//        BookList list = searchList(listName);
//        String message = list.removeBook(title, author);
//        System.out.println(message);
    }

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

    // EFFECTS: prompts user to select a genre and returns the selected genre
    private Genre readGenre() {
        System.out.println("Please select a genre for the book: ");
        int option = 1;
        for (Genre g : Genre.values()) {
            System.out.println(option + ": " + g);
            option++;
        }

        int selection = input.nextInt();
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

    // EFFECTS: display the book information of books in the list
    private void displayBooks() {
        System.out.println("display books");
    }

    // EFFECTS: display the list
    private void printLists() {
        List<List> lists = library.getLists();
    }

    // EFFECTS: prompts the user to select a list to display
    private Lists readList() {
        System.out.println("Please select a list to display: ");
        int option = 1;
        for (Lists l : Lists.values()) {
            System.out.println(option + ": " + l);
            option++;
        }

        int selection = input.nextInt();
        return Lists.values()[selection - 1];
    }

    // EFFECTS: searches for list (using listName) in library and returns the list
//    private BookList searchList(String listName) {
//        BookList listFound = library.findList(listName);
//        while (listFound.isNull()) {
//            System.out.println("Please enter an existing list: ");
//            String newName = input.next();
//            listFound = library.findList(newName);
//        }
//        return listFound;
//    }

    // EFFECTS: checks whether the user input is valid
    private boolean validStringInput(String input) {
        if (input.isEmpty() || input.equals(" ")) {
            System.out.println("Input cannot be empty. Try again: ");
            return false;
        } else {
            return true;
        }
    }

    // EFFECTS: saves the lists to file
    private void saveLists() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            System.out.println("Saved" + library.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads library from file
    private void loadList() {
        try {
            library = jsonReader.read();
            System.out.println("Loaded " + library.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
