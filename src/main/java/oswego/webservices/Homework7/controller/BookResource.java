package oswego.webservices.Homework7.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oswego.webservices.Homework7.api.Book;
import java.io.IOException;

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
                logger.info("Book with "+ isbn + " was found");
                return new ResponseEntity<>(bs, HttpStatusCode.valueOf(200));
            }else {
                logger.error("Book with "+ isbn + " was not found");
                return new ResponseEntity<>( HttpStatusCode.valueOf(404));

            }

    }
}
