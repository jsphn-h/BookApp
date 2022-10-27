package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookListTest {
    private Book testBook, testBook2, testBook3;
    private BookList testList, testList2;

    @BeforeEach
    void runBefore() {
        testBook = new Book("Hunger Games", "Suzanne Collins", Genre.SCIFI,1);
        testBook2 = new Book("Da Vinci Code", "Dan Brown", Genre.MYSTERY);
        testBook3 = new Book("The Elite", "Kiera Cass", Genre.ROMANCE, 2);

        testList = new BookList("To Read");
        testList.add(testBook);

        testList2 = new BookList("Read");
        testList2.add(testBook2);
        testList2.add(new Book("A Game of Thrones", "George R. R. Martin", Genre.FANTASY, 1));
    }

    // Test Book
    @Test
    void testBookConstructor() {
        assertEquals("Hunger Games", testBook.getTitle());
        assertEquals("Suzanne Collins", testBook.getAuthor());
        assertEquals(1, testBook.getSeriesNum());
        assertEquals(Genre.SCIFI, testBook.getGenre());

        assertEquals(0, testBook2.getSeriesNum());
        assertEquals(Genre.MYSTERY, testBook2.getGenre());

        assertEquals(2, testBook3.getSeriesNum());
    }

    @Test
    void testBookToString() {
        assertTrue(testBook.toString().contains("Title: Hunger Games"));
    }


    //Test BookList
    @Test
    void testListConstructor() {
        assertEquals("To Read", testList.getListName());
        assertEquals("Read", testList2.getListName());
    }

    @Test
    void testAddBook() {
        assertEquals("Hunger Games", testList.addBook(testBook));
    }

    @Test
    void testSearchBook() {
        assertEquals(0, testList.searchBook("Hunger Games", "Suzanne Collins"));
        assertEquals(-1, testList.searchBook("A Game of Thrones", "George R. R. Martin"));
    }

    @Test
    void testRemoveBook() {
        String message;
        message = testList.removeBook("Hunger Games", "Suzanne Collins");
        assertEquals("Hunger Games is not in this list.", message);
        message = testList.removeBook("Da Vinci Code", "Dan Brown");
        assertEquals("Da Vinci Code has been removed from the list.", message);
    }

    @Test
    void testDisplayBooks() {
       assertTrue(testList.displayBooks().contains("Title: Hunger Games"));
    }
}
