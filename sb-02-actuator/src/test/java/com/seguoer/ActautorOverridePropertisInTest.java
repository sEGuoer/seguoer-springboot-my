package com.seguoer;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(properties = "management.endpoint.health.show-details=never")
@AutoConfigureMockMvc
public class ActautorOverridePropertisInTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("测试配置了 `management.endpoint.health.show-details=never` 之后的 /actuator/health 页面")
    void actuatorHealthNeverShowDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("status", Matchers.is("UP")))
                .andExpect(MockMvcResultMatchers.jsonPath("components.diskSpace").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("components.ping").doesNotExist())
        ;
    }
}
