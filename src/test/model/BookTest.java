package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {
    private Book testBook, testBook2, testBook3;

    @BeforeEach
    void runBefore() {
        testBook = new Book("Hunger Games", "Suzanne Collins", 1, Genre.SCIFI);
        testBook2 = new Book("The Passenger", "Cormac McCarthy", 0, Genre.FICTION);
        testBook3 = new Book("The Elite", "Kiera Cass", 2, Genre.ROMANCE);
    }

    @Test
    void testConstructor() {
        assertEquals("Hunger Games", testBook.getTitle());
        assertEquals("Suzanne Collins", testBook.getAuthor());
        assertEquals(1, testBook.getSeriesNum());
        assertEquals(Genre.SCIFI, testBook.getGenre());
;
        assertEquals(0, testBook2.getSeriesNum());
        assertEquals(Genre.FICTION, testBook2.getGenre());

        assertEquals(2, testBook3.getSeriesNum());
    }

    @Test
    void testToString() {
        assertTrue(testBook.toString().contains("Title: Hunger Games"));
    }
}