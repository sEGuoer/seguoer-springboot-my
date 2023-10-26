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

    @Test
    @DisplayName("测试配置了 `management.endpoint.health.show-details=always` 之后的 /actuator/health 页面")
    void actuatorHealthShowDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("UP"))
                .andExpect(MockMvcResultMatchers.jsonPath("components.diskSpace.status",Matchers.startsWith("UP")))
                .andExpect(MockMvcResultMatchers.jsonPath("components.ping.status",Matchers.endsWith("UP")))
        ;
    }

    @Test
    @DisplayName("测试增加了自定义 CustomHealthIndicator 之后的 /actuator/health 页面内容")
    void actuatorCustomHealthIndicator() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andExpect(MockMvcResultMatchers.jsonPath("status", Matchers.oneOf("UP", "DOWN")))
                .andExpect(MockMvcResultMatchers.jsonPath("components.custom.status", Matchers.oneOf("UP", "DOWN")))
                .andExpect(MockMvcResultMatchers.jsonPath("components.custom.details.message", Matchers.oneOf("is even", "is odd")))
                .andExpect(MockMvcResultMatchers.jsonPath("components.diskSpace.status").value("UP"))
                .andExpect(MockMvcResultMatchers.jsonPath("components.ping.status").value("UP"))
        ;
    }
}
