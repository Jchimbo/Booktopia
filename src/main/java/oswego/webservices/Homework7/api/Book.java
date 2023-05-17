package oswego.webservices.Homework7.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.json.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
@Component
public class Book {
    @JsonProperty("title")
    String title;
    @JsonProperty("isbn")
    ArrayList<String> isbn;
    @JsonProperty("cover")
    String cover;
    @JsonIgnore
    private static final String OPEN_LIBRARY_API = "https://openlibrary.org/isbn/";
    @JsonIgnore
    private static final String GOOGLE_API = "https://www.googleapis.com/books/v1/volumes?q=";
    @JsonIgnore
    private  static String API_KEY;



    public Book(String title, ArrayList<String> isbnArr, String cover){
        this. title = title;
        this.isbn = isbnArr;
        this.cover = cover;
    }
//    Needed for Jackson
    public Book(){

    }

    /**
     * Creates two bookSockest to get jsons from Open Library  and google_api and combines relevant information from both in the Book class
     * @param query an isbn
     * @return Book
     * @throws IOException url might not work
     */
    public static Book getBook(String query) throws IOException {

        String bookSocket1 = new BookSocket(new URL(OPEN_LIBRARY_API + query + ".json")).getJsonString();
        String bookSocket2 = new BookSocket(new URL(GOOGLE_API + query + API_KEY)).getJsonString();
        if(bookSocket1 != null ){
            JsonReader reader = getJsonReader(bookSocket1);
            if(bookSocket2 != null){
                JsonReader reader1 = getJsonReader(bookSocket2);
                JsonObject googleBookJson = reader1.readObject();
                JsonObject openLibJson = reader.readObject();
                String title = openLibJson.get("title").toString();
                JsonArray isbnJsonArr = openLibJson.get("isbn_13").asJsonArray();
                ArrayList<String> isbn = convertIsbnArray(isbnJsonArr);
                JsonArray jsonArrayItems = googleBookJson.get("items").asJsonArray();
                String cover;
                JsonObject rootElm=jsonArrayItems.get(0).asJsonObject();
                if (rootElm.get("volumeInfo") == null){
                    return new Book(title, isbn, "\"No cover Link 1\"");
                }else {
                    JsonObject volumeInfo = rootElm.get("volumeInfo").asJsonObject();
                    if (volumeInfo.get("imageLinks") ==null){
                        return new Book(title, isbn, "\"No cover Link 2\"");
                    }else {
                        JsonObject imageLinks = volumeInfo.get("imageLinks").asJsonObject();
                        if(imageLinks.get("thumbnail") ==null){
                            return new Book(title, isbn, "\"No cover Link 3\"");
                        }else {
                            JsonValue thumbnail = imageLinks.get("thumbnail");
                            cover = thumbnail.toString();
                            return new Book(title, isbn, cover);
                        }
                    }
                }
            }else {
                JsonObject openLibJson = reader.readObject();
                String title = openLibJson.get("title").toString();
                JsonArray isbnJsonArr = openLibJson.get("isbn_13").asJsonArray();
                ArrayList<String> isbn = convertIsbnArray(isbnJsonArr);
                return new Book(title, isbn, "\"No cover Link 4\"");
                }
            }
        return null;
        }



    private static ArrayList<String> convertIsbnArray(JsonArray jsonArray){
        ArrayList<String> temp = new ArrayList<>();
        for(JsonValue a: jsonArray){
            temp.add(a.toString());
        }
        return temp;
    }
    private static JsonReader getJsonReader(String jsonString){
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
        return String.format("{\"title\": %s, \"isbn\": %s, \"cover\": %s}", title,isbn,cover);
    }



}
