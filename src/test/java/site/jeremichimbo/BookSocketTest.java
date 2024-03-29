package site.jeremichimbo;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import site.jeremichimbo.api.tomcat.BookSocket;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookSocketTest {
    String socket;
    URL OPEN_LIBRARY_API = new URL("https://openlibrary.org/isbn/9780451526533");

    @Ignore
    @Test
    public void openLibraryConnectionTest() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) OPEN_LIBRARY_API.openConnection();
        assertEquals(connection.getResponseCode(), 200);

    }
    @Test
    public void openLibraryGetBookTest(){
        socket = new BookSocket(OPEN_LIBRARY_API).getJsonString();
        assertTrue(socket.contains("The adventures of Tom Sawyer"));
    }


    public BookSocketTest() throws MalformedURLException {
    }
}
