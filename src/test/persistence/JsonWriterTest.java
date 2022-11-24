package persistence;

import model.Book;
import model.Genre;
import model.Library;
import model.Lists;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Library lib = new Library("My library");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            Library lib = new Library("My Library");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLibrary.json");
            lib = reader.read();
            assertEquals("My Library", lib.getName());
            assertTrue(lib.getBooks().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLibrary() {
        try {
            Library lib = new Library("My Library");
            lib.addBook(new Book("ABC", "Author", 0, Genre.FICTION, Lists.READ));
            lib.addBook(new Book("123", "Author 2", 1, Genre.FANTASY, Lists.WISHLIST));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            lib = reader.read();
            assertEquals("My Library", lib.getName());
            List<Book> books = lib.getBooks();
            assertEquals(2, books.size());
            checkBook("ABC", "Author", 0, Genre.FICTION, books.get(0));
            checkBook("123", "Author 2", 1, Genre.FANTASY, books.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
