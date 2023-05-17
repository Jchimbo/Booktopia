package oswego.webservices.Homework7.model;

import org.springframework.data.repository.CrudRepository;
import oswego.webservices.Homework7.api.User;

public interface UserDAO extends CrudRepository<User, String> {
    User[] findIsbnByUsername(String username);
    void deleteByusernameAndIsbn(String username, String isbn);
    boolean existsByUsername(String username);

}
