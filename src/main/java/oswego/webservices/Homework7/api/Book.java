package oswego.webservices.Homework7.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.json.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class Book {

    @JsonProperty("title")
    String title;
    @JsonProperty("isbn")
    ArrayList<String> isbn;
    @JsonProperty("cover")
    String cover;
    @JsonProperty("description")
    String description;
    @JsonProperty("author")
    String author;
    @JsonIgnore
    private static final String OPEN_LIBRARY_API = "https://openlibrary.org/";
    @JsonIgnore
    private static final String GOOGLE_API = "https://www.googleapis.com/books/v1/volumes?q=";
    @JsonIgnore
    private static String API_KEY;


    public Book(String title, ArrayList<String> isbnArr, String cover, String description, String author) {
        this.title = title;
        this.isbn = isbnArr;
        this.cover = cover;
        this.description = description;
        this.author = author;
    }

    //    Needed for Jackson
    public Book() {

    }

    /**
     * Creates two bookSockest to get jsons from Open Library  and google_api and combines relevant information from both in the Book class
     *
     * @param query an isbn
     * @return Book
     * @throws IOException url might not work
     */
    public static Book getBook(String query) throws IOException {
//        TODO: Google book API Will become a backup
        JsonReader googleBookJson = getJsonObject(GOOGLE_API + query + API_KEY);
        //        Setup Sockets and Variables
        JsonReader openLibJson = getJsonObject(OPEN_LIBRARY_API + "isbn/" + query + ".json");
        String title ="";
        ArrayList<String> isbn =new ArrayList<>();
        String cover;
        String des = "";
        String author = "";
        if (openLibJson == null) {
            return null;
        } else {
            JsonObject openLibJsonObject = openLibJson.readObject();
            title =openLibJsonObject.get("title").toString();
            //  Get ISBN array
            JsonArray isbnJsonArr = openLibJsonObject.get("isbn_13").asJsonArray();
            isbn = convertArray(isbnJsonArr);
//        Get Description from Socket
            if (openLibJsonObject.get("works") != null) {
                String works = openLibJsonObject.get("works").asJsonArray().get(0).asJsonObject().getString("key");
                Object temp = Objects.requireNonNullElse(getJsonObject(OPEN_LIBRARY_API + works + ".json"), "Not found");
                des = ((JsonReader) temp).readObject().get("description").toString().replace("\"", "");
            }
            //        Get Author from Socket
            if (openLibJsonObject.get("authors") != null) {
                author = openLibJsonObject.get("authors").asJsonArray().get(0).asJsonObject().getString("key");
                Object temp = Objects.requireNonNullElse(getJsonObject(OPEN_LIBRARY_API + author + ".json"), "Not found");
                author = ((JsonReader) temp).readObject().get("name").toString().replace("\"", "");
            }
//       Get Cover
            cover = OPEN_LIBRARY_API.replace("https://", "https://covers.") + "b/isbn/" + query + "-M.jpg";
            return new Book(title, isbn, "\"" + cover + "\"", "\"" + des + "\"", "\"" + author + "\"");

        }

    }

    private static JsonReader getJsonObject(String url) throws MalformedURLException {
        String bookSocket = new BookSocket(new URL(url)).getJsonString();
        if (bookSocket == null) return null;
        return getJsonReader(bookSocket);
    }

    private static ArrayList<String> convertArray(JsonArray jsonArray) {
        ArrayList<String> temp = new ArrayList<>();
        for (JsonValue a : jsonArray) {
            temp.add(a.toString());
        }
        return temp;
    }

    private static JsonReader getJsonReader(String jsonString) {
        StringReader stringReader = new StringReader(jsonString);
        return Json.createReader(stringReader);
    }

    public String getTitle() {
        return title;
    }

    @Value("${google.api.key}")
    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    @Override
    public String toString() {
        return String.format("{\"title\": %s, \"isbn\": %s, \"cover\": %s, \"description\": %s, \"author\": %s}", title, isbn, cover, description, author);
    }


}
