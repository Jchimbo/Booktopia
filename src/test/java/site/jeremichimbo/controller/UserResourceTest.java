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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import java.util.Map;
@SpringBootTest
@AutoConfigureMockMvc
class UserResourceTest {
    @Autowired
    private MockMvc mvc;
    @Test
    void getRealUserBookList() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "name last");
        attr.put("email", "email@gmail.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
        this.mvc.perform(post("/booklist/0156012197").with(oauth2Login().oauth2User(auth2User)));
        this.mvc.perform(get("/booklist").with(oauth2Login().oauth2User(auth2User))).andExpect(status().isOk());
    }
    @Test
    void getFakeUserBookList() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "Person Person");
        attr.put("email", "Person@gmail.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
        this.mvc.perform(get("/booklist").with(oauth2Login().oauth2User(auth2User))).andExpect(status().isNotFound());
    }
    @Test
    void addToBookListNoISBN() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "name last");
        attr.put("email", "email@gmail.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
        this.mvc.perform(post("/booklist/ ").with(oauth2Login().oauth2User(auth2User))).andExpect(status().isNotFound());
    }
    @Test
    void addToBookListISBN() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "name last");
        attr.put("email", "email@gmail.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
        this.mvc.perform(post("/booklist/0156012197").with(oauth2Login().oauth2User(auth2User))).andExpect(status().isCreated());
    }

    @Test
    void userNotInDBRemoveBook() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "Person Person");
        attr.put("email", "Person@gmail.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
        this.mvc.perform(delete("/booklist/0156012197").with(oauth2Login().oauth2User(auth2User))).andExpect(status().isNotFound());
    }
    @Test
    void userFoundNoISBNRemoveBook() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "name last");
        attr.put("email", "email@gmail.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
        this.mvc.perform(delete("/booklist/").with(oauth2Login().oauth2User(auth2User))).andExpect(status().isNotFound());
    }

    @Test
    void userFoundAndISBNRemoveBook() throws Exception {
        Map<String,Object> attr = new HashMap<>();
        attr.put("name", "name last");
        attr.put("email", "email@gmail.com");
        OAuth2User auth2User = new DefaultOAuth2User(AuthorityUtils.createAuthorityList("SCOPE_message:read"), attr, "name");
        this.mvc.perform(delete("/booklist/0156012197").with(oauth2Login().oauth2User(auth2User))).andExpect(status().isOk());
    }
}