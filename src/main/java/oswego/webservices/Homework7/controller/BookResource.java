package oswego.webservices.Homework7.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oswego.webservices.Homework7.api.openlib.Book;
import oswego.webservices.Homework7.model.openlib.BookDAO;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/book")
public class BookResource {
    Logger logger = LoggerFactory.getLogger(BookResource.class);

    @Autowired
    BookDAO db;

    //    @GetMapping("/{isbn:[0-9]+[x?[X?]]$}")
    @GetMapping(value = "/{isbn}",produces = "application/json")
    public ResponseEntity<String> getBook(@PathVariable String isbn) throws IOException {
        if (db.existsById(isbn)){
            logger.info("Book with "+ isbn + " was found");
            Book tmp = db.findById(isbn).get();
            tmp.setCover("\"https://covers.openlibrary.org/" + "b/isbn/" + isbn + "-M.jpg\"");
            return new ResponseEntity<>(tmp.toString(), HttpStatusCode.valueOf(200));
        }
            Book book = Book.getBook(isbn);
            if(book!= null){
                book.setIsbn(isbn);
                db.save(book);
                String bs = book.toString();
                logger.info("Book with "+ isbn + " was found");
                return new ResponseEntity<>(bs, HttpStatusCode.valueOf(200));
            }else {
                logger.error("Book with "+ isbn + " was not found");
                Book nf = new Book("\"Not Found\"", new ArrayList<>(), "\"img/NotFound.png\"", "\"description not found\"", "\"author not found\"");
                return new ResponseEntity<>( nf.toString(),HttpStatusCode.valueOf(404));

            }

    }
}
