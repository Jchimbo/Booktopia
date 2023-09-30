package site.jeremichimbo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class AccountResourceTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getAccountInfoFOUND() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "Fake Person");
        attr.put("email", "Fake@email.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
//        Creates Fake User
        this.mvc.perform(get("/account")
                .with(oauth2Login().oauth2User(auth2User)));

        this.mvc.perform(get("/account").
                with(oauth2Login().oauth2User(auth2User)))
                        .andExpect(content().
                        string("{\"username\":\"Fake_Person\",\"email\":\"Fake@email.com\"}"));
        }


    @Test
    void deleteAccount() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "Fake Person");
        attr.put("email", "Fake@email.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
//        Creates Fake User
        this.mvc.perform(get("/account")
                .with(oauth2Login().oauth2User(auth2User)));
//        Deletes Fake user
        this.mvc.perform(delete("/account").with(oauth2Login().oauth2User(auth2User))).andExpect(status().isOk());
    }

    @Test
    void deleteFakeAccount() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "Fake Person");
        attr.put("email", "Fake@email.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");

//        Deletes Fake user
        this.mvc.perform(delete("/account").with(oauth2Login().oauth2User(auth2User))).andExpect(status().isNotFound());
    }
}