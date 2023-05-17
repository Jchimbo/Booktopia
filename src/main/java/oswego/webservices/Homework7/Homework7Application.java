package oswego.webservices.Homework7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import oswego.webservices.Homework7.api.Account;
import oswego.webservices.Homework7.model.AccountDAO;
import java.util.Objects;

@SpringBootApplication

@RestController
public class Homework7Application {
	Logger logger = LoggerFactory.getLogger(Homework7Application.class);

	@Autowired
		private AccountDAO db;

//ADDS user to db on login
		@GetMapping("/")
		public void user(@AuthenticationPrincipal OAuth2User principal)  {
			String username = Objects.requireNonNull(principal.getAttribute("name")).toString().replaceAll(" ", "_");
			String email = Objects.requireNonNull(principal.getAttribute("email")).toString().replaceAll(" ", "_");
			if(db.findById(username).isEmpty()) {
				db.save(new Account(username, email));
				logger.info(username + " was logged in and added to database by " + principal.getAttribute("email") );
			}
			logger.info(username + " was logged in by " + principal.getAttribute("email") );

		}
	public static void main(String[] args) {
		SpringApplication.run(Homework7Application.class, args);
	}

}
