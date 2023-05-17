package oswego.webservices.Homework7;

import org.junit.jupiter.api.Test;
import oswego.webservices.Homework7.api.BookSocket;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookSocketTest {
    String socket;
    URL OPEN_LIBRARY_API = new URL("https://openlibrary.org/isbn/9780451526533");
    URL GOOGLE_API = new URL("https://www.googleapis.com/books/v1/volumes?q="+"9780451526533" +"&key=AIzaSyAUj62lGbMQGZYEOWc6vdLCDvcjpSDCl9I");


    @Test
    public void openLibraryConnectionTest() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) OPEN_LIBRARY_API.openConnection();
        assertEquals(connection.getResponseCode(), 200);

    }
    @Test
    public void googleBooksConnectionTest() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) GOOGLE_API.openConnection();
        assertEquals(connection.getResponseCode(), 200);

    }
    @Test
    public void openLibraryGetBookTest(){
        socket = new BookSocket(OPEN_LIBRARY_API).getJsonString();
        assertTrue(socket.contains("The adventures of Tom Sawyer"));
    }
    @Test
    public void googleBooksGetBookTest(){
         socket = new BookSocket(GOOGLE_API).getJsonString();
        assertTrue(socket.contains("The Adventures of Tom Sawyer"));
    }

    public BookSocketTest() throws MalformedURLException {
    }
}
