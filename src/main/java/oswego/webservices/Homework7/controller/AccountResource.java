package oswego.webservices.Homework7.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import oswego.webservices.Homework7.api.Account;
import oswego.webservices.Homework7.model.AccountDAO;

import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountResource {
    Logger logger = LoggerFactory.getLogger(AccountResource.class);
    @Autowired
    public AccountDAO db;



    /**
     * Once auth is passes account is then checked to make sure it is acting on the account that was granted access
     * @return Response
     */
    @GetMapping( produces = "application/json")
    public ResponseEntity<Account>getAccountInfo( @AuthenticationPrincipal OAuth2User principal){
        String username = Objects.requireNonNull(principal.getAttribute("name")).toString().replaceAll(" ", "_");
        if(db.findById(username).isPresent()){
            logger.info(username + " Was found for " + principal.getAttribute("email") );
            return new ResponseEntity<>(db.findById(username).get(), HttpStatusCode.valueOf(200));
        }else {
            logger.warn(username + " Was not found for " + principal.getAttribute("email"));
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));

        }
    }

    @DeleteMapping(produces ="application/json" )
    public ResponseEntity<Account> deleteAccount( @AuthenticationPrincipal OAuth2User principal){
        String username = Objects.requireNonNull(principal.getAttribute("name")).toString().replaceAll(" ", "_");
        if(db.findById(username).isPresent() ){
            db.delete(new Account(username));
            logger.info(username + " Was removed by " + principal.getAttribute("email") );
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }else {
            logger.error(username + " Was not removed by " + principal.getAttribute("email") );
            return  new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }

    }


}
