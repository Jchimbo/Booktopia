package site.jeremichimbo.api.tomcat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import site.jeremichimbo.api.openlib.Book;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
@Entity(name = "user")
@IdClass(BookListID.class)
@Table(name = "book_list")
public class User {
    @Id
    @Column(name = "`user_email`")
    @JsonProperty
    private String email;
    @Id
    @Column(name = "`book_isbn`")
    @JsonProperty
    private String isbn;
    @Transient
    @JsonProperty
    private ArrayList<String> isbn_list;
    @Transient
    @JsonIgnore
    private ArrayList<Book> book_list = new ArrayList<>();
    @Transient
    @JsonIgnore
    private String BOOK_URL;


    public User(String userName)  {
        this.email = userName;
    }
    public User(String userName, String isbn){
        this.email = userName;
        this.isbn = isbn;
    }
    public User(String email, ArrayList<String> isbn_list)  {
        this.email = email;
        this.isbn_list = isbn_list;
    }

    public User() {

    }

    /**
     * Calls BookSocket to make actual booklist from isbnList
     * @return ArrayList<Book>
     */
    public ArrayList<Book> getBook_list() throws MalformedURLException, JsonProcessingException {
        for (String isbnString : isbn_list){
            URL url = new URL(BOOK_URL +isbnString);
            String bookSocket = new BookSocket(url).getJsonString();
            if(bookSocket != null){
                ObjectMapper objectMapper = new ObjectMapper();
                Book book = objectMapper.readValue(bookSocket,Book.class);
                book_list.add(book);
            }
        }
        return book_list;
    }

    public ArrayList<String> getIsbn_list() {
        return isbn_list;
    }

    public String getEmail() {
        return email;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setBookUrl(String BOOK_URL) {
        this.BOOK_URL = BOOK_URL;
    }


}
