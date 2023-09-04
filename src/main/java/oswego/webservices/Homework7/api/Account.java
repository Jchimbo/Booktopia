package oswego.webservices.Homework7.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "account")
@Table(name= "users")
public class Account {

    @Column(name = "`user_name`")
    @JsonProperty
    private  String username;

    @Id
    @Column(name="`user_email`")
    @JsonProperty
    private String email;
    public Account(String email) {
        this.email = email;
    }
    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Account() {

    }

    public  String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public String toString() {
        return String.format("{\"Username\":\"%s\" , \"Email\": \"%s\"}%n", username, email);
    }

}

