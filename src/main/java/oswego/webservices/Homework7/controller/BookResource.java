package oswego.webservices.Homework7.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oswego.webservices.Homework7.api.Book;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/book")
public class BookResource {
    Logger logger = LoggerFactory.getLogger(BookResource.class);

    //    @GetMapping("/{isbn:[0-9]+[x?[X?]]$}")
    @GetMapping(value = "/{isbn}",produces = "application/json")
    public ResponseEntity<String> getBook(@PathVariable String isbn) throws IOException {
            Book book = Book.getBook(isbn);
            if(book!= null){
                String bs = book.toString();
                logger.info("Book with "+ isbn + " wass found");
                return new ResponseEntity<>(bs, HttpStatusCode.valueOf(200));
            }else {
                logger.error("Book with "+ isbn + " was not found");
                Book nf = new Book("\"Not Found\"", new ArrayList<>(), "\"img/NotFound.png\"", "\"description not found\"", "\"author not found\"");
                return new ResponseEntity<>( nf.toString(),HttpStatusCode.valueOf(404));

            }

    }
}
