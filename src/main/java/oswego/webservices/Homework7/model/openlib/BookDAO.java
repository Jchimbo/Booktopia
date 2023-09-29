package oswego.webservices.Homework7.model.openlib;

import org.springframework.data.repository.CrudRepository;
import oswego.webservices.Homework7.api.openlib.Book;

import java.util.Optional;

public interface BookDAO extends CrudRepository<Book, String> {
    @Override
    Optional<Book> findById(String isbn);
}
