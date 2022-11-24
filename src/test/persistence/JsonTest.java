package persistence;

import model.Book;
import model.Genre;
import model.Lists;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String title, String author, int seriesNum, Genre genre, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(seriesNum, book.getSeriesNum());
        assertEquals(genre, book.getGenre());
    }
}