package site.jeremichimbo.model.tomcat;

import org.springframework.data.repository.CrudRepository;
import site.jeremichimbo.api.tomcat.User;

public interface UserDAO extends CrudRepository<User, String> {
    User[] findIsbnByEmail(String username);
    void deleteByEmailAndIsbn(String username, String isbn);
    boolean existsByEmail(String username);

}
