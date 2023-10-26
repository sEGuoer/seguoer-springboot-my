package com.seguoer;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class DefaultConfigActuatorTest {
    @Autowired
    MockMvc mvc;
    @Test
    void actuator() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("_links", Matchers.hasKey("health-path")))
                .andExpect(MockMvcResultMatchers.jsonPath("_links", Matchers.hasKey("health")))
                .andExpect(MockMvcResultMatchers.jsonPath("_links.self.href", Matchers.is("http://localhost/actuator")))
                .andExpect(MockMvcResultMatchers.jsonPath("_links.self", Matchers.hasKey("href")))
                .andExpect(MockMvcResultMatchers.jsonPath("_links.self", Matchers.hasValue("http://localhost/actuator")));
    }
    @Test
    void health() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("status", Matchers.is("UP")));

    }
}
