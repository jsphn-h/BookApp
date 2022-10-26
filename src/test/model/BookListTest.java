package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class BookListTest {
    private Book testBook, testBook2, testBook3;
    private BookList testList, testList2;

    @BeforeEach
    void runBefore() {
        testBook = new Book("Hunger Games", "Suzanne Collins", 1, "9780439023481");
        testBook2 = new Book("Da Vinci Code", "Dan Brown");
        testBook3 = new Book("The Elite", "Kiera Cass", 2);

        testList = new BookList("To Read");
        testList2 = new BookList("Read");
        testList2.add(testBook2);
        testList2.add(new Book("A Game of Thrones", "George R. R. Martin", 1, "9780553573404"));
    }

    // Test Book
    @Test
    void testBookConstructor() {
        assertEquals("Hunger Games", testBook.getTitle());
        assertEquals("Suzanne Collins", testBook.getAuthor());
        assertEquals(1, testBook.getSeriesNum());
        assertEquals("9780439023481", testBook.getIsbn());
        ;
        assertEquals(0, testBook2.getSeriesNum());
        assertEquals("N/A", testBook2.getIsbn());

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
        testList.addBook(testList, testBook);
    }

    @Test
    void testSearchBook() {
        testList.searchBook(testList, "Hunger Games", "Suzanne Collins");
        testList.searchBook(testList, "A Game of Thrones", "George R. R. Martin");
    }

    @Test
    void testRemoveBook() {
        testList.removeBook(testList2, "Hunger Games", "Suzanne Collins");
        testList.removeBook(testList2, "Da Vinci Code", "Dan Brown");
    }

    @Test
    void testDisplayBooks() {
       testList2.displayBooks(testList2);
    }
}
