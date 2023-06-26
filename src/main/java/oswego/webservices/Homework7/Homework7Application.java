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


	public static void main(String[] args) {
		SpringApplication.run(Homework7Application.class, args);
	}

}
