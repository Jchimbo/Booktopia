package oswego.webservices.Homework7.model.tomcat;

import org.springframework.data.repository.CrudRepository;
import oswego.webservices.Homework7.api.tomcat.Account;


public interface AccountDAO extends CrudRepository<Account, String> {


}
