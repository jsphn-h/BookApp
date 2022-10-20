package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {
    private Book testBook;

    @BeforeEach
    void runBefore() {
        testBook = new Book("Hunger Games", "Suzanne Collins", "9780439023481", 1);
    }

    @Test
    void testConstructor() {
        assertEquals("Hunger Games", testBook.getTitle());
        assertEquals("Suzanne Collins", testBook.getAuthor());
        assertEquals("9780439023481", testBook.getIsbnNum());
        assertEquals(1, testBook.getSeriesNum());
    }

    @Test
    void testToString() {
        assertTrue(testBook.toString().contains("Title: Hunger Games"));
    }
}