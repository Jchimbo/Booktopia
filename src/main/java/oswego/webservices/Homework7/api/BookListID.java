package oswego.webservices.Homework7.api;

import java.io.Serializable;

public class BookListID implements Serializable {
    protected String email;
    protected String isbn;
    public BookListID(){

    }

    public BookListID(String email, String isbn){
        this.email = email;
        this.isbn =isbn;
    }

}
