package site.jeremichimbo.model.tomcat;

import org.springframework.data.repository.CrudRepository;
import site.jeremichimbo.api.tomcat.Account;


public interface AccountDAO extends CrudRepository<Account, String> {


}
