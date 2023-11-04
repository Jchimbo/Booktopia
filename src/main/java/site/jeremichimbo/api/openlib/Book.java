package site.jeremichimbo.api.openlib;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.json.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import site.jeremichimbo.api.tomcat.BookSocket;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Entity(name="book")
@Table(name="books")
public class Book {
    @JsonInclude
    @JsonProperty("isbn")
    @Id
    String isbn;
    @JsonProperty("title")
    @Column
    String title;
    @JsonInclude
    @JsonProperty("cover")
    @Transient
    String cover;
    @JsonProperty("description")
    @Column(name = "descript")
    String description;
    @JsonProperty("author")
    @Column
    String author;
    @JsonIgnore
    private static final String OPEN_LIBRARY_API = "https://openlibrary.org/";

 public Book(String title, String isbn, String cover, String description, String author) {
        this.title = title;
        this.isbn = isbn;
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
        //        Setup Sockets and Variables
        JsonReader openLibJson = getJsonObject(OPEN_LIBRARY_API + "isbn/" + query + ".json");
        String title;
        String isbnFirst;
        String cover;
        String des;
        String author;
        if (openLibJson == null) {
            return null;
        } else {
            JsonObject openLibJsonObject = openLibJson.readObject();
            title = getTitleFromJson(openLibJsonObject);
            isbnFirst = getISBNFromJson(openLibJsonObject);
            des = getDescriptionFromJson(openLibJsonObject);
            author = getAuthorFromJson(openLibJsonObject);
//       Get Cover
            cover = OPEN_LIBRARY_API.replace("https://", "https://covers.") + "b/isbn/" + query + "-M.jpg";
            return new Book(title, isbnFirst, "\"" + cover + "\"", "\"" + des + "\"", "\"" + author + "\"");

        }

    }

    public static String removeQuotes(String quoted){
        return quoted.replace("\"", "");
    }

    private static String getTitleFromJson(JsonObject jsonObject){
       return jsonObject.get("title").toString();
    }
    private static String getISBNFromJson(JsonObject jsonObject){
        //  Get ISBN array
        if (jsonObject.get("isbn_13") != null){
            JsonArray arr = jsonObject.get("isbn_13").asJsonArray();
            return arr.get(0).toString();
        }else if (jsonObject.get("isbn_10") != null){
            JsonArray arr = jsonObject.get("isbn_10").asJsonArray();
            return arr.get(0).toString();
        }else {
            return  "";
        }
    }
    private static String getDescriptionFromJson(JsonObject jsonObject) throws MalformedURLException {
        if (jsonObject.get("works") != null) {
            String works = jsonObject.get("works").asJsonArray().get(0).asJsonObject().getString("key");
            JsonReader temp = getJsonObject(OPEN_LIBRARY_API + works + ".json");
            try{
                String jsonVal =  temp.readObject().get("description").asJsonObject().get("value").toString();
                return removeQuotes(jsonVal);
            }catch (Exception exception){
                try{
                    String jsonVal =  temp.readObject().get("description").toString();
                    return removeQuotes(jsonVal);
                }catch (Exception ex){
                    return  "No description Found";
                }
            }
        }
        return null;
    }
    private static String getAuthorFromJson(JsonObject jsonObject) throws MalformedURLException {
        if (jsonObject.get("authors") != null) {
            String authorKey = jsonObject.get("authors").asJsonArray().get(0).asJsonObject().getString("key");
            JsonReader temp = getJsonObject(OPEN_LIBRARY_API + authorKey + ".json");
            try{
                return removeQuotes(temp.readObject().get("name").toString());
            }catch (Exception exception){
               return "No author Found";
            }
        }
        return null;
    }

    private static JsonReader getJsonObject(String url) throws MalformedURLException {
        String bookSocket = new BookSocket(new URL(url)).getJsonString();
        if (bookSocket == null) return null;
        return getJsonReader(bookSocket);
    }

    private static JsonReader getJsonReader(String jsonString) {
        StringReader stringReader = new StringReader(jsonString);
        return Json.createReader(stringReader);
    }

    public String getTitle() {
        return title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return String.format("{\"title\": %s, \"isbn\": \"%s\", \"cover\": %s, \"description\": %s, \"author\": %s}", title, isbn, cover, description, author);
    }


}
