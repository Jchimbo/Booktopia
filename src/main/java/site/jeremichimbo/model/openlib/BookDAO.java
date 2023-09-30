package site.jeremichimbo.model.openlib;

import org.springframework.data.repository.CrudRepository;
import site.jeremichimbo.api.openlib.Book;

import java.util.Optional;

public interface BookDAO extends CrudRepository<Book, String> {
    @Override
    Optional<Book> findById(String isbn);
}
