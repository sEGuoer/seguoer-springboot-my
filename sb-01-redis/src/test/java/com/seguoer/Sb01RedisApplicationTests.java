package com.seguoer;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class Sb01RedisApplicationTests {
    @Autowired
    MockMvc mvc;

    @Test
    void helloRedis() throws Exception {
        mvc.perform(get("/hello-redis"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("count=")));
    }

    @Test
    void saveObjectToRedis() throws Exception {
        mvc.perform(get("/saveObjectToRedis"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("done!"));
    }

    @Test
    @DisplayName("记得在映像里先save再去get")
    void getObjectFromRedis() throws Exception {
        mvc.perform(get("/getObjectFromRedis"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("name",Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("age",Matchers.is(18)));
    }

}
