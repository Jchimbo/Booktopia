package oswego.webservices.Homework7.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookResourceTest {
    @Autowired
    private MockMvc mvc;


    @Test
    void getRealBookStatus200() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "Fake Person");
        attr.put("email", "Fake@email.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
//        Creates Fake User
        this.mvc.perform(get("/book/0060254920").with(oauth2Login().oauth2User(auth2User))).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getFakeBookStatus404() throws Exception {
        this.mvc.perform(get("/book/XXXXXXXX")).andDo(print()).andExpect(status().isNotFound());
    }
}