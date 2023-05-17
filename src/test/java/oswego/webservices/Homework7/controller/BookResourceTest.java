package oswego.webservices.Homework7.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
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
        this.mvc.perform(get("/book/0060254920")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getFakeBookStatus404() throws Exception {
        this.mvc.perform(get("/book/XXXXXXXX")).andDo(print()).andExpect(status().isNotFound());
    }
}