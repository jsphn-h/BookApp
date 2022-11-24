import model.Book;
import model.Genre;
import model.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.BookApp;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BookAppTest {
    private BookApp bookApp = new BookApp();
    private Book testBook = new Book("The Hunger Games", "Suzanne Collins", 1, Genre.SCIFI, Lists.READ);

    public BookAppTest() throws FileNotFoundException {
        bookApp.loadLibrary();
    }

    @Test
    void testGetLists() {
        assertEquals("WISHLIST, READ, READING, ", bookApp.getLists());
    }

    @Test
    void testDisplayAllBooks() {
        assertTrue(bookApp.displayAllBooks().get(0).toString().contains("The Great Gatsby"));
    }

    @Test
    void testDisplayBooksInList() {
        assertTrue(bookApp.displayBooksInList("Read").toString().contains("Twilight"));
    }

    @Test
    void testAddToList() {
        assertFalse(bookApp.displayBooksInList("Read").toString().contains("The Hunger Games"));
        bookApp.addToList("The Hunger Games", "Suzanne Collins", 1, "Sci-Fi", "Read");
        assertTrue(bookApp.displayBooksInList("Read").toString().contains("The Hunger Games"));
    }

    @Test
    void testRemoveBook() {
        assertEquals("The Hunger Games has been removed from library", bookApp.removeBook("The Hunger Games", "Suzanne Collins"));
        assertFalse(bookApp.displayAllBooks().toString().contains("The Hunger Games"));
    }

}
