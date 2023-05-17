package oswego.webservices.Homework7.api;

import java.io.Serializable;

public class BookListID implements Serializable {
    protected String username;
    protected String isbn;
    public BookListID(){

    }

    public BookListID(String username,String isbn){
        this.username =username;
        this.isbn =isbn;
    }

}
