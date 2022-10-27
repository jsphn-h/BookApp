package ui;

import model.Book;
import model.BookList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BookApp {
    private Scanner input;
    ArrayList listOfLists = new ArrayList(); //Class type: ArrayList

    //EFFECTS: runs the book app
    public BookApp() {
        System.out.println("Welcome to BookApp!\n");
        runApp();
    }

    /*
        MODIFIES: this
        EFFECTS: processes user input
     */
    private void runApp() {
        boolean keepRunning = true;
        String userInput = null;

        init();

        while (keepRunning) {
            displayMenu();
            userInput = input.next();

            if (userInput.equals("4")) {
                keepRunning = false;
            } else {
                processInput(userInput);
            }
        }
        System.out.println("Keep on reading!");
    }

    /*
        MODIFIES: this
        EFFECTS: processes user input
     */
    private void processInput(String userInput) {
        if (userInput.equals("1")) {
            createList(listOfLists);
        } else if (userInput.equals("2")) {
            addToList();
        } else if (userInput.equals("3")) {
            removeFromList();
        } else {
            System.out.println("Invalid input");
        }
    }

    /*
        MODIFIES: this
        EFFECTS: initializes the app
     */
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
        System.out.println("\t5) Exit");
        System.out.println("----- WARNING: you can't add/remove a book if no list has been created -----");
    }

    /*
        MODIFIES: this
        EFFECTS: creates a book list
     */
    private void createList(ArrayList listOfLists) {
        System.out.println("Enter list name: ");
        String listName = input.next();
        Boolean validInput = false;

        while (!validInput) {
            if (validStringInput(listName) == false) {
                listName = input.next();
            } else {
                new BookList(listName);
                System.out.println("The list '" + listName + "' has been created. \n");
                validInput = true;
            }
        }
        listOfLists.add(new BookList(listName)); //Class type: BookList
    }

    /*
        MODIFIES: this
        EFFECTS: adds a book to a specified list
     */
    private void addToList() {
        System.out.println("Enter the title: ");
        String title = input.next();
        while (validStringInput(title) == false) {
            title = input.next();
        }

        System.out.println("Enter the author: ");
        String author = input.next();
        while (validStringInput(author) == false) {
            author = input.next();
        }

        System.out.println("Enter list to be added to: ");
        String listName = input.next();
        while (validStringInput(listName) == false) {
            listName = input.next();
        }

        BookList list = searchList(listName);
        Book book = new Book(title, author);
        list.addBook(list, book);
        System.out.println(list.get(list.size() - 1).getTitle() + " has been added to " + list.getListName());
    }

    /*
        MODIFIES: this
        EFFECTS: removes a book to a specified list
     */
    private void removeFromList() {
        System.out.println("Enter the title: ");
        String title = input.next();
        while (validStringInput(title) == false) {
            title = input.next();
        }

        System.out.println("Enter the author: ");
        String author = input.next();
        while (validStringInput(author) == false) {
            author = input.next();
        }

        System.out.println("Enter list to remove the book from: ");
        String listName = input.next();
        while (validStringInput(listName) == false) {
            listName = input.next();
        }

        BookList list = searchList(listName);
        String message = list.removeBook(list, title, author);
        System.out.println(message);
    }

    /*
        EFFECTS: display the book information of books in the list
     */
    private void displayBooks() {
        System.out.println("List to display: ");
        String listName = input.next();
        while (validStringInput(listName) == false) {
            listName = input.next();
        }

        BookList list = searchList(listName);
        System.out.println(list.displayBooks(list));
    }

    /*
        EFFECTS: searches for list (using listName) in listOfLists
                 returns the list
     */
    private BookList searchList(String listName) {
        int idx = 0;
        for (int i = 0; i < listOfLists.size(); i++) {
            BookList bookList = (BookList) listOfLists.get(i);
            if (listName.equals(bookList.getListName())) {
                System.out.println(bookList.getListName() + " has been found.");
                return (BookList) listOfLists.get(i);
            } else {
                System.out.println("The list doesn't exist.");
            }
        }
        return null;
    }

    /*
        EFFECTS: checks whether the user input is valid
     */
    private boolean validStringInput(String input) {
        if (input.isEmpty() || input == "" || input == " ") {
            System.out.println("Input cannot be empty. Try again: ");
            return false;
        } else {
            return true;
        }
    }
}
