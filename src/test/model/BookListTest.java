package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookListTest {
    private BookLists testList;

    @BeforeEach
    void runBefore() {
        testList = new BookLists("To Read");
    }

    @Test
    void testConstructor() {
        assertEquals("To Read", testList.getListName());
    }

    @Test
    void addBook(testList, Book("Da Vinci Code", "Dan Brown")) {

    }
}
