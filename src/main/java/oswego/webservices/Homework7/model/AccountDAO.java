package oswego.webservices.Homework7.model;

import org.springframework.data.repository.CrudRepository;
import oswego.webservices.Homework7.api.Account;


public interface AccountDAO extends CrudRepository<Account, String> {


}
