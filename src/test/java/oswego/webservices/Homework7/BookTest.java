package oswego.webservices.Homework7;

import org.junit.jupiter.api.Test;
import oswego.webservices.Homework7.api.Book;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    Book book = new Book("A", new ArrayList<>(), "a", "des","\"auth\"");
    @Test
    public void getBookFromQuery() throws IOException {
        String title = Book.getBook("0060254920").getTitle();
        assertEquals("Where the Wild Things Are", title.replace("\"", ""));
    }

    @Test
    public void getToString(){
        assertEquals( String.format("{\"title\": %s, \"isbn\": %s, \"cover\": %s, \"description\": %s}", "A","[]","a", "des","\"auth\""), book.toString());
    }


}
