package oswego.webservices.Homework7;

import org.junit.jupiter.api.Test;
import oswego.webservices.Homework7.api.tomcat.Account;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AccountTest {
    String username = "testUSer";
    String email = "email";
    Account account = new Account(username,email);
    @Test
    public void usernameGetter(){
        assertEquals(username,account.getUsername());
    }
    @Test
    public void emailGetter(){
        assertEquals(email,account.getEmail());
    }

    @Test
    public void toStringTest(){
        assertEquals(String.format("{\"Username\":\"%s\" , \"Email\": \"%s\"}%n", username, email),account.toString());
    }
}
