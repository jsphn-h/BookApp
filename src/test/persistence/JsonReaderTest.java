package persistence;

import model.Book;
import model.Genre;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library lib = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            Library lib = reader.read();
            assertEquals("My Library", lib.getName());
            assertTrue(lib.getBooks().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        try {
            Library lib = reader.read();
            assertEquals("My Library", lib.getName());
            List<Book> books = lib.getBooks();
            assertEquals(2, books.size());
            checkBook("ABC", "Author", 0, Genre.FICTION, books.get(0));
            checkBook("123", "Author 2", 1, Genre.FANTASY, books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
