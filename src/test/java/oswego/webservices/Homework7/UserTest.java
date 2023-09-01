package oswego.webservices.Homework7;

import org.junit.jupiter.api.Test;
import oswego.webservices.Homework7.api.User;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class UserTest {
    ArrayList<String> isbn_list = new ArrayList<>();
    String user_name = "testUser";

    private String bookUrl = "http://localhost:8080/book/";
//TODO:    Test needs to be refactored to run only after app startup
//    @Ignore
//    @Test
//    public void getBookListTest() throws MalformedURLException, JsonProcessingException {
//        isbn_list.add("0060254920");
//        isbn_list.add("0156012197");
//        User user = new User(user_name,isbn_list);
//        user.setBookUrl(bookUrl);
//        ArrayList<Book> bookArrayList = user.getBook_list();
////        May return true if connection is refused if machine is not running app
//            assertEquals( false, bookArrayList.isEmpty());
//
//
//    }

    @Test
    public void testUsernameISBNConstructorISBN(){
        User user = new User(user_name, "0060254920");
        assertEquals("0060254920",user.getIsbn());
    }
    @Test
    public void testUsernameISBNConstructorUsername(){
        User user = new User(user_name, "0060254920");
        assertEquals(user_name,user.getEmail());
    }


}
