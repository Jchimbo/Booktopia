package oswego.webservices.Homework7.model.tomcat;

import org.springframework.data.repository.CrudRepository;
import oswego.webservices.Homework7.api.tomcat.User;

public interface UserDAO extends CrudRepository<User, String> {
    User[] findIsbnByEmail(String username);
    void deleteByEmailAndIsbn(String username, String isbn);
    boolean existsByEmail(String username);

}
