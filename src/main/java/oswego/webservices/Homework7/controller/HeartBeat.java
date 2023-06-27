package oswego.webservices.Homework7.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heartbeat")
public class HeartBeat {
    Logger logger = LoggerFactory.getLogger(HeartBeat.class);


    @GetMapping
    public Boolean isAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Boolean auth = !(authentication instanceof AnonymousAuthenticationToken);
        logger.info("User has auth: " + auth);
        return auth;
    }
}
